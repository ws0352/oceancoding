package com.oceancoding.ws.ocean.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    /*
    *   设置过期时间为一天
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /*
    *token私钥
     */
    private static final String TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJz";

    /*
    *生成签名并设置过期时间
     */
    public static String sign(String username, String userId){
        /*
        *过期时间
         */
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        /*
        *私钥及加密算法
         */
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带userName和userId生成签名
        return JWT.create().withHeader(header).withClaim("loginName","username").withClaim("userId", userId).withExpiresAt(date).sign(algorithm);

    }

    public static boolean verity(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }catch (JWTVerificationException e){
            e.printStackTrace();
            return false;
        }
    }

}
