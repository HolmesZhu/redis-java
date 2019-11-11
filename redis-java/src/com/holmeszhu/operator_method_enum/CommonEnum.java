package com.holmeszhu.operator_method_enum;

public enum CommonEnum {

    EXISTS("exists", "exists"),
    DEL("del", "del"),
    ;

    private String redisOperate;

    private String methodName;

    CommonEnum(String redisOperate, String methodName) {
        this.redisOperate = redisOperate;
        this.methodName = methodName;
    }

    public String getRedisOperate() {
        return redisOperate;
    }

    public void setRedisOperate(String redisOperate) {
        this.redisOperate = redisOperate;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }



}
