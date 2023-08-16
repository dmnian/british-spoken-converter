package com.davsoft.time.converter.domain;

public enum BritishTimeSpeechEnum {
    HALF("half"), QUARTER("quarter"), NOON("noon"), MIDNIGHT("midnight"),
    PAST(" past "), TO(" to "), OH(" oh "), OCLOCK(" o'clock");

    private final String value;

    BritishTimeSpeechEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}