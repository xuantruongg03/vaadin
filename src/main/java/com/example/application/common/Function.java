package com.example.application.common;

import java.util.Random;

public class Function {
    public static String generaID() {
        String a = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String id = "";
        for (int i = 0; i < 30; i++) {
            Random r = new Random();
            id += a.charAt(r.nextInt(a.length()));
        }
        return id;
    }}
