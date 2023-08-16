package com.davsoft.time.converter;

public enum TimeSpeechEnum {
    HALF("half"), QUARTER("quarter"), NOON("noon"), MIDNIGHT("midnight"),
    PAST(" past "), TO(" to "), OH(" oh "), OCLOCK(" o'clock");

    private final String value;

    TimeSpeechEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}