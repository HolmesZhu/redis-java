package com.holmeszhu.method;

public class HashMethod {

    private volatile static HashMethod hashMethod;

    private HashMethod() {

    }

    public static HashMethod getInstance() {
        if (hashMethod == null) {
            synchronized (HashMethod.class) {
                if (hashMethod == null) {
                    hashMethod = new HashMethod();
                }
            }
        }
        return hashMethod;
    }


}
