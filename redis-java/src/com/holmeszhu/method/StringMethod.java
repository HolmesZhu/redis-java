package com.holmeszhu.method;

import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.data_type.StringDataType;
import com.holmeszhu.util.Utils;

/**
 * 我们将参数校验的逻辑放到这一层来处理
 * 传入参数都是字符串  我们需要进行校验  看看key是否存在  或者是否是正确的类型
 * 还有对应的参数是否能转化为数字类型
 */
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
        //先将参数字母全部变为小写
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
            if (Utils.isLong(time)) {
                return stringDataType.setEx(key, Long.parseLong(time), value);
            } else {
                return "time is not int";
            }
        } else if (newParam.equals("px")) {
            if (Utils.isLong(time)) {
                return stringDataType.pSetEx(key, Long.parseLong(time), value);
            } else {
                return "time is not int";
            }
        } else {
            return "param is valid";
        }
    }


    public int setNx(String key, String value) {
        if (stringDataType.setNx(key, value) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public String setEx(String key, String seconds, String value) {
        if (Utils.isLong(seconds)) {
            return stringDataType.setEx(key, Long.parseLong(seconds), value);
        } else {
            return "time is not int";
        }
    }

    public String pSetEx(String key, String milliseconds, String value) {
        if (Utils.isLong(milliseconds)) {
            return stringDataType.pSetEx(key, Long.parseLong(milliseconds), value);
        } else {
            return "time is not int";
        }
    }

    public String get(String key) {
        if (!stringDataType.exists(key)) {
            return null;
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return stringDataType.get(key);
    }

    public String getSet(String key, String value) {
        if (!stringDataType.exists(key)) {
            set(key, value);
            return null;
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return stringDataType.getSet(key, value);
    }


    public String strLen(String key) {
        if (!stringDataType.exists(key)) {
            return "0";
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return String.valueOf(stringDataType.strLen(key));
    }


    public String append(String key, String value) {
        if (!stringDataType.exists(key)) {
            stringDataType.set(key, value);
            return String.valueOf(value.length());
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return String.valueOf(stringDataType.append(key, value));
    }

    public String setRange(String key, String offset, String value) {
        if (Utils.isInteger(offset)) {
            return String.valueOf(stringDataType.setRange(key, Integer.parseInt(offset), value));
        } else {
            return "offset is not integer.";
        }
    }

    public String getRange(String key, String start, String end) {
        if (Utils.isInteger(start)) {
            if (Utils.isInteger(end)) {
                return stringDataType.getRange(key, Integer.parseInt(start), Integer.parseInt(end));
            } else {
                return "end is not integer.";
            }
        } else {
            return "start is not integer";
        }
    }

    public String incr(String key) {
        if (!stringDataType.exists(key)) {
            stringDataType.set(key, "1");
            return "1";
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return stringDataType.incr(key);
    }

    public String incrBy(String key, String increment) {
        if (!stringDataType.exists(key)) {
            stringDataType.set(key, increment);
            return increment;
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isInteger(increment)) {
            return stringDataType.incrBy(key, Integer.parseInt(increment));
        } else {
            return "increment is not int";
        }
    }

    public String incrByFloat(String key, String increment) {
        if (!stringDataType.exists(key)) {
            stringDataType.set(key, increment);
            return increment;
        }
        if (stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isDouble(increment)) {
            return stringDataType.incrByFloat(key, Double.parseDouble(increment));
        } else {
            return "increment is not double";
        }
    }

}
