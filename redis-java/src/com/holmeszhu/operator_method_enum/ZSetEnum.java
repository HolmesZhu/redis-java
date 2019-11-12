package com.holmeszhu.operator_method_enum;

public enum ZSetEnum {

    Z_ADD("zadd", "zAdd"),
    Z_SCORE("zscore", "zScore"),
    Z_INCR_BY("zincrby", "zIncrBy"),
    Z_CARD("zcard", "zCard"),
    Z_COUNT("zcount", "zCount"),
    Z_RANGE("zrange", "zRange"),
    Z_REV_RANGE("zrevrange", "zRevRange"),
    Z_RANGE_BY_SCORE("zrangebyscore", "zRangeByScore"),
    Z_RANK("zrank", "zRank"),
    Z_REV_RANK("zrevrank", "zRevRank"),
    Z_REM("zrem", "zRem"),

    Z_REM_RANGE_BY_RANK("zremrangebyrank", "zRemRangeByRank"),
    Z_REM_RANGE_BY_SCORE("zremrangebyscore", "zRemRangeByScore"),

    Z_RANGE_BY_LEX("get", "get"),
    Z_LEX_COUNT("get", "get"),
    Z_REM_RANGE_BY_LEX("get", "get"),

    Z_SCAN("get", "get"),
    Z_UNION_STORE("get", "get"),
    Z_INTER_STORE("get", "get"),


    ;

    private String redisOperate;

    private String methodName;

    ZSetEnum(String redisOperate, String methodName) {
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
