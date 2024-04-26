package com.example.application.common;

import java.util.Random;

/**
 * The Function class provides utility methods for generating IDs.
 */
public class Function {
    /**
     * Generates a random ID consisting of alphanumeric characters.
     *
     * @return the generated ID
     */
    public static String generaID() {
        String a = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String id = "";
        for (int i = 0; i < 30; i++) {
            Random r = new Random();
            id += a.charAt(r.nextInt(a.length()));
        }
        return id;
    }
}
