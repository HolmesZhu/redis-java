package com.holmeszhu.method;

import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.data_type.ListDataType;
import com.holmeszhu.util.Utils;

import java.util.Arrays;
import java.util.LinkedList;

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

    private ListDataType listDataType = new ListDataType();


    public String lPush(String[] params) {
        if (params.length >= 2) {
            String key = params[0];
            LinkedList<String> values = new LinkedList<>();
            for (int i = 1; i < params.length; i++) {
                values.add(params[i]);
            }
            return listDataType.lPush(key, values);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    //存在疑惑  当这个list存在值后来删除之后  list也为空  不知这时候能否再次插入
    public String lPushX(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String value = params[1];
            return listDataType.lPushX(key, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String rPush(String[] params) {
        if (params.length >= 2) {
            String key = params[0];
            LinkedList<String> values = new LinkedList<>();
            for (int i = 1; i < params.length; i++) {
                values.add(params[i]);
            }
            return listDataType.rPush(key, values);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String rPushX(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String value = params[1];
            return listDataType.rPushX(key, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String lPop(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return listDataType.lPop(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String rPop(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return listDataType.rPop(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String rPopLPush(String[] params) {
        if (params.length == 2) {
            String source = params[0];
            String destination = params[1];
            return listDataType.rPopLPush(source, destination);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String lRem(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String count = params[1];
            if (!Utils.isInteger(count)) {
                return "count is not int";
            }
            String value = params[2];
            return listDataType.lRem(key, Integer.parseInt(count), value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    //这里不存在需不需要插入一个新的列表值得怀疑
    public String lLen(String key) {
        if (!listDataType.exists(key)) {
            return "0";
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return String.valueOf(listDataType.lLen(key));
    }

    public String lIndex(String key, String index) {
        if (!listDataType.exists(key)) {
            return null;
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isInteger(index)) {
            return listDataType.lIndex(key, Integer.parseInt(index));
        } else {
            return "index is not int";
        }
    }

    public String lInsert(String key, String param, String pivot, String value) {
        if (!listDataType.exists(key)) {
            return "0";
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (param.equals("BEFORE") || param.equals("AFTER")) {
            return String.valueOf(listDataType.lInsert(key, param, pivot, value));
        } else {
            return "param is not before or after";
        }
    }


    public String lSet(String key, String index, String value) {
        if (!listDataType.exists(key)) {
            return CommonConstants.EMPTY_KEY;
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isInteger(index)) {
            return listDataType.lSet(key, Integer.parseInt(index), value);
        } else {
            return "index is not int";
        }

    }

    public String lRange(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String start = params[1];
            if (!Utils.isInteger(start)) {
                return "start is not number";
            }
            String stop = params[2];
            if (!Utils.isInteger(start)) {
                return "stop is not number";
            }
            return String.valueOf(listDataType.lRange(key, Integer.parseInt(start), Integer.parseInt(stop)));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


}
