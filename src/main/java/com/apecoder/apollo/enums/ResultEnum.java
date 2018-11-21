package com.apecoder.apollo.enums;


public enum ResultEnum {

    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCES(0,"成功"),
    PRIMARY_SCHOOL(100,"你可能在上小学"),
    MIDDLE_SCHOOL(101,"你可能在上中学");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
