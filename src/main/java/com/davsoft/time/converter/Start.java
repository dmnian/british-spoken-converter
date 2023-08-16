package com.davsoft.time.converter;

import com.davsoft.time.converter.exception.TimeFormatException;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        System.out.println("Welcome in the British spoken time converter!\n");

        try (Scanner sc = new Scanner(System.in)) {
            var finished = false;
            while (!finished) {
                System.out.println("please type time in 12 hour format eg. 11:20 or 4:05, " +
                        "\n If you want to quit then just type 'exit' instead of time.");

                final String time = sc.next();

                if (time.equals("exit")) {
                    finished = true;
                    System.out.println("Thank you for using british time converter.");
                } else {
                    try {
                        final String convert = BritishTimeToSpeechConverter.getConvertedSpeech(time);
                        System.out.println("British spoken time converted: " + convert + "\n");
                    } catch (TimeFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
