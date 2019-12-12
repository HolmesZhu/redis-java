package com.holmeszhu.data_type.operator_method_enum;

public enum CommonEnum {

    EXISTS("exists", "exists"),


    DEL("del", "del"),


    L_PUSH("lpush", "lpush"),
    R_PUSH("rpush", "rpush"),
    L_RANGE("lrange", "lrange"),
    L_INSERT("linsert", "linsert"),
    L_LEN("llen", "llen"),
    L_POP("lpop", "lpop"),
    R_POP("rpop", "rpop"),

    ;


    private String command;

    private String methodName;

    CommonEnum(String command, String methodName) {
        this.command = command;
        this.methodName = methodName;
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public static String getMethodNameByCommand(String command) {
        CommonEnum[] commonEnums = CommonEnum.values();
        for (CommonEnum commonEnum : commonEnums) {
            if (commonEnum.getCommand().equals(command)) {
                return commonEnum.getMethodName();
            }
        }
        return null;
    }

}
