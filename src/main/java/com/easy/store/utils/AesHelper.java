package com.easy.store.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AesHelper {
    private static SecretKeySpec AESKEY;

    static {
        SecretKeySpec secretKeySpec = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed("jbhim".getBytes());
            //通过使用大小和随机源的方式创建密钥生成器
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AESKEY = secretKeySpec;
    }


    public static String enc(String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, AESKEY);
        byte[] bytes = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8).replace("[\r\n]", "");
    }

    public static String dec(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] decode = Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, AESKEY);
        cipher.doFinal(decode);
        return new String(cipher.doFinal(decode), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String text = "hello world";
        String enc = enc(text);
        System.out.println(enc);
        System.out.println(dec(enc));

        String enc1 = enc(text);
        System.out.println(enc1);
        System.out.println(dec(enc1));
    }


}
