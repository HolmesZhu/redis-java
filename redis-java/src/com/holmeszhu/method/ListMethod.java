package com.holmeszhu.method;

public class ListMethod {

    private volatile static ListMethod listMethod;

    private ListMethod() {

    }

    public static ListMethod getInstance() {
        if (listMethod == null) {
            synchronized (ListMethod.class) {
                if (listMethod == null) {
                    listMethod = new ListMethod();
                }
            }
        }
        return listMethod;
    }

}
