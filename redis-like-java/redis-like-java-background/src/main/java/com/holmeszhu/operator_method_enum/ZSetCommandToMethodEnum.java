package com.holmeszhu.operator_method_enum;

public enum ZSetCommandToMethodEnum {

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

    Z_RANGE_BY_LEX("zrangebylex", "zRangeByLex"),
    Z_LEX_COUNT("zlexcount", "zLexCount"),
    Z_REM_RANGE_BY_LEX("zremrangebylex", "zRemRangeByLex"),

    Z_SCAN("zscan", "zScan"),
    Z_UNION_STORE("zunionstore", "zUnionStore"),
    Z_INTER_STORE("zinterstore", "zInterStore"),


    ;

    private String redisOperate;

    private String methodName;

    ZSetCommandToMethodEnum(String redisOperate, String methodName) {
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
