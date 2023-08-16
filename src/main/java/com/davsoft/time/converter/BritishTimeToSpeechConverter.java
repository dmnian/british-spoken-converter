package com.davsoft.time.converter;

import com.davsoft.time.converter.domain.Time;
import com.davsoft.time.converter.exception.TimeFormatException;

import static com.davsoft.time.converter.domain.BritishTimeSpeechEnum.*;

public class BritishTimeToSpeechConverter implements TimeToSpeechConverter {

    private final static String[] firstTwentyNumbersInText = {"", "one", "two", "three",
            "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};

    private final static String[] firstFiveTens = {"", "ten", "twenty", "thirty", "forty", "fifty"};

    public String getConvertedSpeech(int minutes, int hours) {
        validateMinutesAndHours(minutes, hours);

        return convert(minutes, hours);
    }

    public String getConvertedSpeech(Time time) {
        return getConvertedSpeech(time.getMinutes(), time.getHours());
    }

    private void validateMinutesAndHours(int minutesNumber, int hourNumber) {
        if (minutesNumber >= 60 || minutesNumber < 0) {
            throw new TimeFormatException("Minutes should be in range between 0 and 59");
        }

        if (hourNumber > 12 || hourNumber < 0) {
            throw new TimeFormatException("Hours should be in range between 0 and 12");
        }
    }

    private String convert(int minutesNumber, int hourNumber) {
        if (minutesNumber == 0 && hourNumber == 12) {
            return NOON.getValue();
        } else if (minutesNumber == 0 && hourNumber == 0) {
            return MIDNIGHT.getValue();
        } else if (minutesNumber == 0) {
            return firstTwentyNumbersInText[hourNumber] + OCLOCK.getValue();
        }

        final var sb = new StringBuilder();

        if (minutesNumber % 5 == 0) {
            if (minutesNumber > 30) {
                final int minutesTo = 60 - minutesNumber;
                final int hourTo = hourNumber + 1;

                if (minutesTo == 15) {
                    sb.append(QUARTER.getValue());
                } else if (minutesTo > 20) {
                    sb.append(firstTwentyNumbersInText[minutesTo / 10] + " " + firstTwentyNumbersInText[minutesTo % 10]);
                } else {
                    sb.append(firstTwentyNumbersInText[minutesTo]);
                }

                sb.append(TO.getValue() + firstTwentyNumbersInText[hourTo]);
            } else {
                if (minutesNumber == 15) {
                    sb.append(QUARTER.getValue());
                } else if (minutesNumber == 30) {
                    sb.append(HALF.getValue());
                } else if (minutesNumber > 20) {
                    sb.append(firstFiveTens[minutesNumber / 10] + " " + firstTwentyNumbersInText[minutesNumber % 10]);
                } else {
                    sb.append(firstTwentyNumbersInText[minutesNumber]);
                }
                sb.append(PAST.getValue() + firstTwentyNumbersInText[hourNumber]);
            }
        } else {
            sb.append(firstTwentyNumbersInText[hourNumber]);

            if (minutesNumber < 10) {
                sb.append(OH.getValue() + firstTwentyNumbersInText[minutesNumber]);
            } else if (minutesNumber > 20) {
                sb.append(" " + firstFiveTens[minutesNumber / 10] + " " + firstTwentyNumbersInText[minutesNumber % 10]);
            } else {
                sb.append(" " + firstTwentyNumbersInText[minutesNumber]);
            }
        }

        return sb.toString();
    }
}