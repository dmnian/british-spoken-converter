package com.davsoft.time.converter;

import com.davsoft.time.converter.domain.Time;

public interface TimeToSpeechConverter {
    String getConvertedSpeech(int minutes, int hours);
    String getConvertedSpeech(Time time);
}
