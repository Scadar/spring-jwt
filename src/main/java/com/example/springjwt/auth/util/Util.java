
package com.example.springjwt.auth.util;

import java.util.UUID;

public class Util {

    private Util() {
        throw new UnsupportedOperationException("Cannot instantiate a Util class");
    }

    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
