package br.com.r4s.adm.arq.autenticacao.jwt;

public class JWTConstants {

    public static final String AUTH_LOGIN_URL = "/authentication/login";
    public static final String JWT_SECRET = "jXn2r5u7x!A%D*G-KaPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@Nc";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
}
