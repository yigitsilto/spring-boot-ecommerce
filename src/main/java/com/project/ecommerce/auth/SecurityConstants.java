package com.project.ecommerce.auth;

public class SecurityConstants {

    public static final String SECRET = "mySecretKey";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}