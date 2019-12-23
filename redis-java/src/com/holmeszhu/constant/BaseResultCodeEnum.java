package com.holmeszhu.constant;

public enum BaseResultCodeEnum {

    SUCCESS(0, "成功"),
    DATA_TYPE_ERROR(1, "数据类型异常"),
    NOT_NUMBER_TYPE_ERROR(2,"不是数字类型异常"),
    NOT_EXISTS_KEY(3,"不存在这个key"),
    ;



    int code;
    String value;

    private BaseResultCodeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
