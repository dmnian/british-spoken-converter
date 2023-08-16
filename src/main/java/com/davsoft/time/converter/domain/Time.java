package com.davsoft.time.converter.domain;

import com.davsoft.time.converter.exception.TimeFormatException;

public class Time {
    private final int hours;
    private final int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public static Time buildTimeFromText(String time) {
        final String[] hoursAndMinutesArray = time.split(":");

        if (hoursAndMinutesArray.length != 2) {
            throw new TimeFormatException("Incorrect time format!");
        }

        final String hour = hoursAndMinutesArray[0];
        final String minutes = hoursAndMinutesArray[1];

        try {
            final int hourNumber = Integer.parseInt(hour);
            final int minutesNumber = Integer.parseInt(minutes);

            return new Time(hourNumber, minutesNumber);
        } catch (NumberFormatException e) {
            throw new TimeFormatException("Incorrect time format, hours and minutes should be a number");
        }
    }
}
