package com.holmeszhu.operator_method_enum;

public enum ListEnum {

    L_PUSH("lpush", "lpush"),
    R_PUSH("rpush", "rpush"),
    L_RANGE("lrange", "lrange"),
    L_INSERT("linsert", "linsert"),
    L_LEN("llen", "llen"),
    L_POP("lpop", "lpop"),
    R_POP("rpop", "rpop"),
    ;


    private String redisOperate;

    private String methodName;

    ListEnum(String redisOperate, String methodName) {
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
