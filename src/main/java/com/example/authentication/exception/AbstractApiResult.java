package com.example.authentication.exception;

import lombok.Data;

/**
 * @author hello
 */
@Data
public abstract class AbstractApiResult {

    protected String code;


    /**
     * 错误返回 * @param errorCode 错误码 * @param errorMessage 错误信息 * @return 错误返回体
     */
    public static AbstractApiResult error(String errorCode, String errorMessage) {
        return new ErrorApiResult(errorCode, errorMessage);
    }


    @Data
    public static class ErrorApiResult extends AbstractApiResult {

        private String msg;

        ErrorApiResult(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}