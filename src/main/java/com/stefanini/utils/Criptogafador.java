package com.stefanini.utils;

import java.util.Base64;

public final class Criptogafador {
    public static String criptografar(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes()));
    }

    public static String decriptografar(String encryptPassword) {
        return new String(Base64.getDecoder().decode(encryptPassword.getBytes()));
    }
}