package com.holmeszhu.constant;

public enum BaseResultCodeEnum {

    SUCCESS(0, "成功"),
    DATA_TYPE_ERROR(1, "数据类型错误");


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
