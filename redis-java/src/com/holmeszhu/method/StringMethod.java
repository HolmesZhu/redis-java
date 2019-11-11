package com.holmeszhu.method;

import com.holmeszhu.data_type.StringDataType;

public class StringMethod {


    private volatile static StringMethod stringMethod;

    private StringMethod() {

    }

    public static StringMethod getInstance() {
        if (stringMethod == null) {
            synchronized (StringMethod.class) {
                if (stringMethod == null) {
                    stringMethod = new StringMethod();
                }
            }
        }
        return stringMethod;
    }


    private StringDataType stringDataType = new StringDataType();

    public void set(String key, String value) {
        System.out.println(stringDataType.set(key, value));
    }

    public void set(String key, String value, String param) {
        System.out.println(stringDataType.exists(key));
        if (param.equals("nx")) {
            System.out.println(stringDataType.setNx(key, value));
        }
    }

    public void set(String key, String value, String param, String time) {
        System.out.println(stringDataType.exists(key));
        if (param.equals("nx")) {
            System.out.println(stringDataType.setNx(key, value));
        }
    }


}
