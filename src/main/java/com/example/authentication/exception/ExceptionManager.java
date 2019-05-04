package com.example.authentication.exception;

import org.springframework.stereotype.Component;

/**
 * 异常管理类
 *
 * @author hello
 */
@Component
public class ExceptionManager {

    public IllegalException create(String code, String msg) {
        return new IllegalException(code, msg);
    }

}