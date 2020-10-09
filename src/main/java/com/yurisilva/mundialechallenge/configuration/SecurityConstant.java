package com.yurisilva.mundialechallenge.configuration;

public class SecurityConstant {
    static final String JWT_SECRET = "mYq3s6v9y34&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPe";
    static final String TOKEN_HEADER = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String TOKEN_TYPE = "JWT";
    static final String TOKEN_ISSUER = "secure-api";
    static final String TOKEN_AUDIENCE = "secure-app";
    static final Long EXPIRATION = 30L;
}
