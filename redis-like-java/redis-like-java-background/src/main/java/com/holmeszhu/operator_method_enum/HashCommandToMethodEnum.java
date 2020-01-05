package com.holmeszhu.operator_method_enum;

public enum HashCommandToMethodEnum {

    HSET("hset", "hSet"),
    H_SET_NX("hsetnx", "hSetNx"),
    H_GET("hget", "hGet"),
    H_EXISTS("hexists", "hExists"),
    H_DEL("hdel", "hDel"),
    H_LEN("hlen", "hLen"),
    H_STRLEN("hstrlen", "hStrlen"),
    H_INCR_BY("hincrby", "get"),
    H_INCR_BY_FLOAT("hincrbyfloat", "hIncrNyFloat"),
    H_M_SET("hmset", "hMSet"),
    H_M_GET("hmget", "hMGet"),
    H_KEYS("hkeys", "hKeys"),
    H_VALS("hvals", "hVals"),
    H_GETALL("hgetall", "hGetAll"),
    H_SCAN("hscan", "hScan"),

    ;


    private String redisOperate;

    private String methodName;

    HashCommandToMethodEnum(String redisOperate, String methodName) {
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
