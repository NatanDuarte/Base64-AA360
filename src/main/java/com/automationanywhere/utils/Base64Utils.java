package com.automationanywhere.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

    public static String decode(String message) {
        return new String(Base64.getDecoder().decode(message), StandardCharsets.UTF_8);
    }

    public static String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
    }
}
