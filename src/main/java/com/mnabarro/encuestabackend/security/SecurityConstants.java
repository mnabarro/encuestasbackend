package com.mnabarro.encuestabackend.security;

import com.mnabarro.encuestabackend.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_DATE = 864000000;
    public static final String LOGIN_URL = "/users/login";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static String getTokenSecret () {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
