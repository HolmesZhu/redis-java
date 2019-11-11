package com.holmeszhu.method;

public class SetMethod {

    private volatile static SetMethod setMethod;

    private SetMethod() {

    }

    public static SetMethod getInstance() {
        if (setMethod == null) {
            synchronized (SetMethod.class) {
                if (setMethod == null) {
                    setMethod = new SetMethod();
                }
            }
        }
        return setMethod;
    }
}
