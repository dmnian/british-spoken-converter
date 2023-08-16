package com.davsoft.time.converter;

import com.davsoft.time.converter.domain.Time;
import com.davsoft.time.converter.exception.TimeFormatException;

import java.util.Scanner;

import static com.davsoft.time.converter.domain.Time.buildTimeFromText;

public class ConsoleApplication {
    public static void main(String[] args) {
        TimeToSpeechConverter timeToSpeechConverter = new BritishTimeToSpeechConverter();

        startConsoleApplication(timeToSpeechConverter);
    }

    private static void startConsoleApplication(TimeToSpeechConverter timeToSpeechConverter) {
        System.out.println("Welcome in the British spoken time converter!\n");

        try (Scanner sc = new Scanner(System.in)) {
            var finished = false;
            while (!finished) {
                System.out.println("please type time in 12 hour format eg. 11:20 or 4:05." +
                        "\nIf you want to quit then just type 'exit' instead of time.");

                final String timeText = sc.next();

                if (timeText.equals("exit")) {
                    finished = true;
                    System.out.println("Thank you for using british time converter.");
                } else {
                    try {
                        final Time time = buildTimeFromText(timeText);
                        final String convert = timeToSpeechConverter.getConvertedSpeech(time);
                        System.out.println("British spoken time converted: " + convert + "\n");
                    } catch (TimeFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
