package com.example.authentication.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 加密类(md5加密后，base64转译成可见字符)
 * 用于password 加密存入数据库和usercode的生成
 *
 * @author hello
 */
public class EncryptUtils {

    public static String Encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //md5加密
        byte[] result = md5.digest(password.getBytes("UTF-8"));
        //base64编码
        byte[] results = Base64.encodeBase64(result);
        String rePassword = new String(results);
        return rePassword;
    }
}
