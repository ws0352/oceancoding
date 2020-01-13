package com.oceancoding.ws.ocean.utils;

import java.security.MessageDigest;

public class MD5Utils {
    private static final String SALT = "ocean";

    public static String encode(String password){
        password = password + SALT;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        char[] chars = password.toCharArray();
        byte[] bytes = new byte[chars.length];

        for(int i = 0; i <chars.length; i++){
            bytes[i] = (byte)chars[i];
        }
        byte[] md5bytes = messageDigest.digest(bytes);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5bytes.length; i++){
            int val = ((int)md5bytes[i]) & 0xff;
            if(val < 16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args){
        System.out.println(MD5Utils.encode("aaaaa"));
    }
}
