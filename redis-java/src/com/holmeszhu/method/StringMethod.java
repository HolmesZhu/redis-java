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

    public String set(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String value = params[1];
            System.out.println("执行到这里");
            return stringDataType.set(key, value);
        } else if (params.length == 3) {
            String key = params[0];
            String value = params[1];
            String param = params[2];
            return stringDataType.set(key, value, param);
        } else if (params.length == 4) {
            String key = params[0];
            String value = params[1];
            String param = params[2];
            String time = params[3];
            return stringDataType.set(key, value, param, time);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String setNx(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String value = params[1];
            return stringDataType.setNx(key, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String setEx(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String seconds = params[1];
            if (!Utils.isLong(seconds)) {
                return "seconds is not number";
            }
            String value = params[2];
            return stringDataType.setEx(key, Long.parseLong(seconds), value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String pSetEx(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String milliseconds = params[1];
            if (!Utils.isLong(milliseconds)) {
                return "milliseconds is not number";
            }
            String value = params[2];
            return stringDataType.pSetEx(key, Long.parseLong(milliseconds), value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String get(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return stringDataType.get(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String getSet(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String value = params[1];
            return stringDataType.getSet(key, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String strLen(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return stringDataType.strLen(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String append(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String value = params[1];
            return stringDataType.append(key, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String setRange(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String offset = params[1];
            if (!Utils.isInteger(offset)) {
                return "offset is not integer.";
            }
            String value = params[2];
            return stringDataType.setRange(key, Integer.parseInt(offset), value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String getRange(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String start = params[1];
            if (!Utils.isInteger(start)) {
                return "start is not number.";
            }
            String end = params[2];
            if (!Utils.isInteger(end)) {
                return "end is not number.";
            }
            return stringDataType.getRange(key, Integer.parseInt(start), Integer.parseInt(end));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }



    public String incr(String key) {
        if (!stringDataType.exists(key)) {
            stringDataType.set(key, "1");
            return "1";
        }
        if (!stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return stringDataType.incr(key);
    }

    public String incrBy(String key, String increment) {
        if (!stringDataType.exists(key)) {
            if (Utils.isInteger(increment)) {
                stringDataType.set(key, increment);
                return increment;
            } else {
                return "increment is not int";
            }
        }
        if (!stringDataType.stringDataType(key)) {
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
        if (!stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isDouble(increment)) {
            return stringDataType.incrByFloat(key, Double.parseDouble(increment));
        } else {
            return "increment is not double";
        }
    }

    public String decr(String key) {
        if (!stringDataType.exists(key)) {
            stringDataType.set(key, "-1");
            return "-1";
        }
        if (!stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return stringDataType.decr(key);
    }

    public String decrBy(String key, String increment) {
        if (!stringDataType.exists(key)) {
            if (Utils.isInteger(increment)) {
                stringDataType.set(key, String.valueOf(-Integer.parseInt(increment)));
                return increment;
            } else {
                return "increment is not int";
            }
        }
        if (!stringDataType.stringDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isInteger(increment)) {
            return stringDataType.decrBy(key, Integer.parseInt(increment));
        } else {
            return "increment is not int";
        }
    }

//    public String mSet(String key, String value) {
//        stringDataType.mSet(key,value);
//    }


}
