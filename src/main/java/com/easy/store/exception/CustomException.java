package com.easy.store.exception;

import com.easy.store.utils.enums.ResultEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();

    }
}
