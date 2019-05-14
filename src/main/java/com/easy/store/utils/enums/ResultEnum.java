package com.easy.store.utils.enums;

import lombok.Getter;

/**
 * @author jbhim
 * @date 2018/12/1/001.
 */
@Getter
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 参数不正确
     */
    PARAM_ERROR(1, "参数不正确"),
    /**
     * 用户名错误
     */
    FAILURE(4, "失败"),

    /**
     * 用户名错误
     */
    USER_NOT_FOUND(2, "用户名错误"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(3, "密码错误");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
