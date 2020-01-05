package com.holmeszhu.operator_method_enum;

public enum ListCommandToMethodEnum {

    L_PUSH("lpush", "lpush"),
    L_PUSH_NX("lpushx", "lPushX"),

    R_PUSH("rpush", "rpush"),
    R_PUSH_NX("rpushnx", "rPushNx"),

    L_POP("lpop", "lpop"),
    R_POP("rpop", "rpop"),
    R_POP_L_PUSH("rpoplpush", "rPopLPush"),

    L_REM("lrem","lRem"),

    L_LEN("llen", "llen"),
    L_INDEX("lindex", "lIndex"),
    L_INSERT("linsert", "linsert"),

    L_SET("lset","lSet"),
    L_RANGE("lrange", "lrange"),
    L_TRIM("ltrim","lTrim"),

    B_L_POP("blpop","bLPop"),
    B_R_POP("brpop","bRPop"),
    B_R_POP_L_PUSH("brpoplpush","bRPopLPush"),

    ;


    private String redisOperate;

    private String methodName;

    ListCommandToMethodEnum(String redisOperate, String methodName) {
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
