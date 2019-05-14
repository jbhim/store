package com.easy.store.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 公共工具
 *
 * @author jbhim
 */
public class CommonUtil {

    /**
     * 判断手机号是否有效
     *
     * @param phoneNumber 手机号
     * @return 是否是手机号
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        String regex = "^(13|17|14|15|18|16|19)\\d{9}";
        return Pattern.compile(regex).matcher(phoneNumber).matches();
    }

    /**
     * 生成随机码
     *
     * @param length 生成随机码的位数
     * @return length位数随机码
     */
    public static String createRandom(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        // 随机产生位数字的字符串
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            code.append(rand);
        }
        return code.toString();
    }

}