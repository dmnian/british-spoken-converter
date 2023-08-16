package com.davsoft.time.converter;

public class BritishTimeToSpeechConverter {

    private static final String HALF = "half";
    private static final String QUARTER = "quarter";
    private static final String NOON = "noon";
    private static final String MIDNIGHT = "midnight";

    private final static String[] firstTwentyNumbersInText = {"", "one", "two", "three",
            "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};

    private final static String[] firstFiveTens = {"", "ten", "twenty", "thirty", "forty", "fifty"};


    public static String convert(String time) {
        if (time.equals("12:00")) {
            return NOON;
        } else if (time.equals("00:00")) {
            return MIDNIGHT;
        }

        StringBuilder sb = new StringBuilder();

        String[] hoursAndMinutesArray = time.split(":");

        if(hoursAndMinutesArray.length != 2){
            throw new TimeFormatException("Wrong time format!");
        }

        String hour = hoursAndMinutesArray[0];
        String minutes = hoursAndMinutesArray[1];

        int minutesNumber = Integer.parseInt(minutes);
        int hourNumber = Integer.parseInt(hour);

        validateMinutesAndHours(minutesNumber, hourNumber);

        if (minutesNumber == 0) {
            return firstTwentyNumbersInText[hourNumber] + " o'clock";
        }

        if (minutesNumber % 5 == 0) {

            if (minutesNumber > 30) {
                int minutesTo = 60 - minutesNumber;
                int hourTo = hourNumber + 1;

                if (minutesTo == 15) {
                    sb.append(QUARTER);
                } else if (minutesTo > 20) {
                    sb.append(firstTwentyNumbersInText[minutesTo / 10] + " " + firstTwentyNumbersInText[minutesTo % 10]);
                } else {
                    sb.append(firstTwentyNumbersInText[minutesTo]);
                }

                sb.append(" to " + firstTwentyNumbersInText[hourTo]);

            } else {
                if (minutesNumber == 15) {
                    sb.append(QUARTER);
                } else if (minutesNumber == 30) {
                    sb.append(HALF);
                } else if (minutesNumber > 20) {
                    sb.append(firstFiveTens[minutesNumber / 10] + " " + firstTwentyNumbersInText[minutesNumber % 10]);
                } else {
                    sb.append(firstTwentyNumbersInText[minutesNumber]);
                }
                sb.append(" past " + firstTwentyNumbersInText[hourNumber]);
            }
        } else {
            sb.append(firstTwentyNumbersInText[hourNumber]);

            if (minutesNumber < 10) {
                sb.append(" oh " + firstTwentyNumbersInText[minutesNumber]);
            } else if (minutesNumber > 20) {
                sb.append(" " + firstFiveTens[minutesNumber / 10] + " " + firstTwentyNumbersInText[minutesNumber % 10]);
            } else {
                sb.append(" " + firstTwentyNumbersInText[minutesNumber]);
            }
        }


        return sb.toString();
    }

    private static void validateMinutesAndHours(int minutesNumber, int hourNumber) {
        if (minutesNumber >= 60 || minutesNumber < 0) {
            throw new TimeFormatException("Minutes should be in range between 0 and 59");
        }

        if (hourNumber > 12 || hourNumber < 0) {
            throw new TimeFormatException("hours should be in range between 0 and 12");
        }
    }
}