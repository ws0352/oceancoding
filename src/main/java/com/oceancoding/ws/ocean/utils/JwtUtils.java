package com.oceancoding.ws.ocean.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

public class JwtUtils {
    /*
    *   设置过期时间为一天
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /*
    *   签名私钥
     */
    private static final String TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJz";

    /*
    * 生成token并设置时间
     */
    public static String createJwtToken(String id){
        String issuer = "www.oceancoding.com";
        String subject = "sahdadhajahwklajsa";
        long ttlMillis = System.currentTimeMillis();
        return createJwtToken(id, issuer, subject, ttlMillis);
    }

    private static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {
        //签名算法，对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //通过密钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TOKEN_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //如果用户登入，设置JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);

        //增加过期时间
        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //构建JWT并将其序列化为一个紧凑的URL安全字符串
        return builder.compact();
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(TOKEN_SECRET)).parseClaimsJws(jwt).getBody();
        return claims;
    }
    /*
    * 测试
     */
//    public static void main(String[] args) {
//
//        System.out.println(JwtUtil.createJwtToken("page=10"));
//    }

}
