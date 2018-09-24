package com.seaturtle.spring.cloud.util.encrypt;

import com.google.common.base.Strings;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类，支持MD5,SHA1加盐加密
 * author chufei
 * date 2017年1月22日
 */
public class EncryptUtil {

    /**
     * MD5加密
     */
    public static final String ENCRYPT_MD5 = "MD5";

    /**
     * SHA1加密
     */
    public static final String ENCRYPT_SHA1 = "SHA1";

    /**
     * 被加密字符串
     */
    private String inputText = "";

    /**
     * 加盐
     */
    private String salt = "";

    /**
     * 加密方式，MD5或SHA1
     */
    private String algorithm = ENCRYPT_MD5;

    public EncryptUtil() {}

    public EncryptUtil(String inputText, String salt, String algorithm) {
        this.inputText = inputText;
        this.salt = salt;
        this.algorithm = Strings.isNullOrEmpty(algorithm) ? ENCRYPT_MD5 : algorithm;
    }

    /**
     * 加盐字符串加密
     * @throws Exception
     */
    public String encodeBySalt() {
        if (Strings.isNullOrEmpty(inputText)) {
            return null;
        }

        if (Strings.isNullOrEmpty(salt)) {
            return encode(inputText);
        }

        return encode(inputText + "{" + salt + "}");
    }

    /**
     *  字符串加密
     * @param inputText
     * @return
     */
    private String encode(String inputText) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
            digest.update(inputText.getBytes());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
