package com.davsoft.time.converter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.*;
import static org.junit.jupiter.api.Assertions.*;

class BritishTimeToSpeechConverterTest {

    final Map<String, String> mapOfInputsAndExpectedOutputs = ofEntries(
            entry("1:00", "one o'clock"),
            entry("1:07", "one oh seven"),
            entry("1:17", "one seventeen"),
            entry("2:05", "five past two"),
            entry("3:10", "ten past three"),
            entry("4:15", "quarter past four"),
            entry("5:20", "twenty past five"),
            entry("6:25", "twenty five past six"),
            entry("6:32", "six thirty two"),
            entry("7:30", "half past seven"),
            entry("7:35", "two five to eight"),
            entry("8:40", "twenty to nine"),
            entry("9:45", "quarter to ten"),
            entry("10:50", "ten to eleven"),
            entry("11:55", "five to twelve"),
            entry("00:00", "midnight"),
            entry("12:00", "noon")
    );

    @Test
    void testBritishSpokenTimeConversion() {
        mapOfInputsAndExpectedOutputs.forEach(
                (input, expectedOutput) -> assertEquals(
                        expectedOutput, BritishTimeToSpeechConverter.getConvertedSpeech(input)
                )
        );
    }
}