package com.holmeszhu.data_type.operator_method_enum;

public enum StringEnum {

    SET("set", "set"),
    GET("get", "get"),
    M_SET("mset", "mset"),
    M_GET("mget", "mget"),

    INCR("incr", "incr"),
    DESR("decr", "decr"),
    APPEND("append", "append"),
    STRLEN("strlen", "strlen");


    private String command;

    private String methodName;

    StringEnum(String command, String methodName) {
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
