package com.davsoft.time.converter;

public enum TimeSpeechEnum {
        HALF("half"), QUARTER("quarter"), NOON("noon"), MIDNIGHT("midnight");

        private final String value;

        TimeSpeechEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }