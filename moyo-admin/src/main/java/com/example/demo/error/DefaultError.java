package com.example.demo.error;
/**
 * @Auther: cch
 * @Date: 2020/9/21 16:07
 * @Description: 默认异常
 */
public enum DefaultError implements BaseError {

    SYSTEM_INTERNAL_ERROR("0000", "系统内部错误"),
    PARAMETER_ERROR("0001","参数错误");

    String errorCode;
    String errorMessage;
    private static final String ns = "DFT";

    DefaultError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return ns + "." + errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}

