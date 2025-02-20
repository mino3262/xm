package com.app.xm.common;

import java.util.Date;
import java.util.Map;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


public class Jwt {
    private static String secret="abcdfg";          //秘钥
    private static long expiration = 1000*60*60*12; //12小时
        
    //token生成
    public static String generateToken(Map<String, Object> claims){
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiration))
                .sign(Algorithm.HMAC256(secret));
    }

    //token解码
    public static Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

    //token验证
    public static boolean verifyToken(String token){
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        Jwt.secret = secret;
    }

    public static long getExpiration() {
        return expiration;
    }

    public static void setExpiration(long expiration) {
        Jwt.expiration = expiration;
    }
  
}
