package com.holmeszhu.method;

import com.holmeszhu.data_type.StringDataType;
import com.holmeszhu.util.Utils;

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

    public String set(String key, String value) {
        return stringDataType.set(key, value);
    }

    //这里的param是NX和XX两种情况
    public String set(String key, String value, String param) {
        String newParam = param.toLowerCase();
        if (newParam.equals("nx")) {
            return stringDataType.setNx(key, value);
        } else if (newParam.equals("xx")) {
            return stringDataType.setXX(key, value);
        } else {
            return "param is valid";
        }
    }

    //这里的param是ex和px
    public String set(String key, String value, String param, String time) {
        String newParam = param.toLowerCase();
        if (newParam.equals("ex")) {
            if (Utils.isInteger(time)) {
                return stringDataType.setEx(key, Integer.parseInt(time), value);
            } else {
                return "time is not int";
            }
        } else if (newParam.equals("px")) {
            if (Utils.isInteger(time)) {
                return stringDataType.pSetEx(key, Integer.parseInt(time), value);
            } else {
                return "time is not int";
            }
        } else {
            return "param is valid";
        }
    }


}
