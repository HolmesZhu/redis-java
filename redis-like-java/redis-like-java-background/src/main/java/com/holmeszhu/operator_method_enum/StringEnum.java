package com.holmeszhu.operator_method_enum;

public enum StringEnum {

    SET("set", "set"),
    SET_NX("setnx", "setNx"),
    SET_EX("setex", "setEx"),
    P_SET_EX("psetex", "pSetEx"),
    GET("get", "get"),

    GET_SET("getset", "getSet"),
    STRLEN("strlen", "strlen"),
    APPEND("append", "append"),

    SET_RANGE("setrange", "setRange"),
    GET_RANGE("getrange", "getRange"),

    INCR("incr", "incr"),
    INCR_BY("incrby", "incrBy"),
    INCR_BY_FLOAT("incrbyfloat", "incrByFloat"),


    DECR("decr", "decr"),
    DECR_BY("decrby","decrBy"),


    M_SET("mset", "mSet"),
    M_SET_NX("msetnx", "mSetNx"),
    M_GET("mget", "mGet"),

    ;


    private String redisOperate;

    private String methodName;

    StringEnum(String redisOperate, String methodName) {
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
