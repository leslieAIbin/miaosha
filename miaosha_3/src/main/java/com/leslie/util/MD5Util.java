package com.leslie.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Leslie
 * @create 2021/6/11 11:08
 */
public class MD5Util {
    private static String md5(String src){
        return DigestUtils.md5Hex(src);
    }


    private static final String salt = "1a2b3c4d";
    public static String inputPassFormPass(String inputPass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String fromPassFormPass(String formPass,String inputPass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input, String saltDB){
        String fromPass = inputPassFormPass(input);
        String dbPass = fromPassFormPass(fromPass, saltDB);
        return dbPass;
    }
    public static void main(String[] args) {
        System.out.println(inputPassFormPass("123456"));
        System.out.println(inputPassToDbPass("123456",  "1a2b3c4d"));
    }
}

