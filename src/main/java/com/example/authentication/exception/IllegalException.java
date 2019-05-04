package com.example.authentication.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义的异常类
 * @author hello
 */
@Data
@NoArgsConstructor
public class IllegalException extends RuntimeException {

    private String code ;
    private String msg;

    public IllegalException(String code, String msg) {
        super(code);
        this.code =code;
        this.msg =msg;
    }
}
