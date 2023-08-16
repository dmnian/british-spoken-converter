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


    public static String getConvertedSpeech(String time) {
        final int[] hoursAndMinutesArray = parseHoursAndMinutes(time);

        final int hourNumber = hoursAndMinutesArray[0];
        final int minutesNumber = hoursAndMinutesArray[1];

        validateMinutesAndHours(minutesNumber, hourNumber);

        return convert(minutesNumber, hourNumber);
    }

    private static int[] parseHoursAndMinutes(String time) {
        final String[] hoursAndMinutesArray = time.split(":");

        if (hoursAndMinutesArray.length != 2) {
            throw new TimeFormatException("Wrong time format!");
        }

        final String hour = hoursAndMinutesArray[0];
        final String minutes = hoursAndMinutesArray[1];

        return new int[]{Integer.parseInt(hour), Integer.parseInt(minutes)};
    }

    private static void validateMinutesAndHours(int minutesNumber, int hourNumber) {
        if (minutesNumber >= 60 || minutesNumber < 0) {
            throw new TimeFormatException("Minutes should be in range between 0 and 59");
        }

        if (hourNumber > 12 || hourNumber < 0) {
            throw new TimeFormatException("hours should be in range between 0 and 12");
        }
    }

    private static String convert(int minutesNumber, int hourNumber) {
        if (minutesNumber == 0 && hourNumber == 12) {
            return NOON;
        } else if (minutesNumber == 0 && hourNumber == 0) {
            return MIDNIGHT;
        } else if (minutesNumber == 0) {
            return firstTwentyNumbersInText[hourNumber] + " o'clock";
        }

        final var sb = new StringBuilder();

        if (minutesNumber % 5 == 0) {
            if (minutesNumber > 30) {
                final int minutesTo = 60 - minutesNumber;
                final int hourTo = hourNumber + 1;

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
}