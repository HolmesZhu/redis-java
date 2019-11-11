package com.holmeszhu.method;

public class ZSetMethod {

    private volatile static ZSetMethod zSetMethod;

    private ZSetMethod() {

    }

    public static ZSetMethod getInstance() {
        if (zSetMethod == null) {
            synchronized (ZSetMethod.class) {
                if (zSetMethod == null) {
                    zSetMethod = new ZSetMethod();
                }
            }
        }
        return zSetMethod;
    }


}



