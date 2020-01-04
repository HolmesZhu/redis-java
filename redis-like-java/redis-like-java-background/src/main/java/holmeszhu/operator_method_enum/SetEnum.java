package holmeszhu.operator_method_enum;

public enum SetEnum {

    S_ADD("sadd", "sAdd"),
    S_IS_MEMBER("sismember", "sIsMember"),
    S_POP("spop", "sPop"),
    S_RAND_MEMBER("srem", "sRem"),
    S_MOVE("smove", "sMove"),
    S_CARD("scard", "sCard"),
    S_MEMBERS("smembers", "sMembers"),
    S_SCAN("sscan", "sScan"),
    S_INTER("sinter", "sInter"),
    S_INTER_STORE("sinterstore", "sInterStore"),
    S_UNION("sunion", "sUnion"),
    S_UNION_STORE("sunionstore", "sUnionStore"),
    S_DIFF("sdiff", "sDiff"),
    S_DIFF_STORE("sdiffstore", "sDiffStore"),

    ;

    private String redisOperate;

    private String methodName;

    SetEnum(String redisOperate, String methodName) {
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
