package com.easy.store.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @author Administrator
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public static ResultVO success() {
        return new ResultVO(0, "success", null);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVO success(T t) {
        return new ResultVO(0, "success", t);
    }

    @SuppressWarnings("unchecked")
    public static ResultVO error(Integer code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
