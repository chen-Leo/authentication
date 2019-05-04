package com.example.authentication.controller;

import com.example.authentication.exception.AbstractApiResult;
import com.example.authentication.exception.IllegalException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *全局异常管理，捕获异常返回json给前端
 * @author hello
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public AbstractApiResult handlerException(Exception e) {

        //如果是自定义的异常，返回对应的错误信息
        if (e instanceof IllegalException) {
            e.printStackTrace();
            IllegalException exception = (IllegalException) e;
            return AbstractApiResult.error(exception.getCode(), exception.getMsg());
        } else {
            //如果不是已知异常，返回系统异常
            e.printStackTrace();
            return AbstractApiResult.error("SYS_EXCEPTION", "系统异常");
        }

    }

}