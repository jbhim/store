package com.easy.store.exception;


import com.easy.store.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jbhim
 * @date 2018/12/1/001.
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    /**
     * 处理全局的自定义异常
     *
     * @param e CustomException
     * @return ResultVO
     */
    @ExceptionHandler(value = CustomException.class)
    public ResultVO exceptionHandler(CustomException e) {
        return ResultVO.error(e.getCode(), e.getMessage());
    }

}
