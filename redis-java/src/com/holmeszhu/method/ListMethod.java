package com.holmeszhu.method;

import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.data_type.ListDataType;
import com.holmeszhu.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    //这里当key不存在时需不需要插入一个新的列表值得怀疑
    public String lLen(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return listDataType.lLen(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String lIndex(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String index = params[1];
            if (!Utils.isInteger(index)) {
                return "index is not int";
            }
            return listDataType.lIndex(key, Integer.parseInt(index));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String lInsert(String[] params) {
        if (params.length == 4) {
            String key = params[0];
            String dict = params[1];
            dict = dict.toLowerCase();
            if (dict.equals("BEFORE") || dict.equals("AFTER")) {
                return "dict is not before or after";
            }
            String pivot = params[2];
            String value = params[3];
            return String.valueOf(listDataType.lInsert(key, dict, pivot, value));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String lSet(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String index = params[1];
            if (!Utils.isInteger(index)) {
                return "index is not int";
            }
            String value = params[2];
            return String.valueOf(listDataType.lSet(key, Integer.parseInt(index), value));
        } else {
            return CommonConstants.INVALID_PARAMS;
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
            if (!Utils.isInteger(stop)) {
                return "stop is not number";
            }
            return String.valueOf(listDataType.lRange(key, Integer.parseInt(start), Integer.parseInt(stop)));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String lTrim(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String start = params[1];
            if (!Utils.isInteger(start)) {
                return "start is not number";
            }
            String stop = params[2];
            if (!Utils.isInteger(stop)) {
                return "stop is not number";
            }
            return String.valueOf(listDataType.lTrim(key, Integer.parseInt(start), Integer.parseInt(stop)));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String bLPop(String[] params) {
        if (params.length >= 2) {
            List<String> keys = new ArrayList<>();
            for (int i = 0; i < params.length - 1; i++) {
                keys.add(params[i]);
            }
            String timeout = params[params.length - 1];
            if (!Utils.isInteger(timeout)) {
                return "timeout is not number";
            }
            return listDataType.bLPop(keys, Integer.parseInt(timeout));

        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String bRPop(String[] params){
        if (params.length >= 2) {
            List<String> keys = new ArrayList<>();
            for (int i = 0; i < params.length - 1; i++) {
                keys.add(params[i]);
            }
            String timeout = params[params.length - 1];
            if (!Utils.isInteger(timeout)) {
                return "timeout is not number";
            }
            return listDataType.bRPop(keys, Integer.parseInt(timeout));

        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String bRPopLPush(String[] params){
        if (params.length == 3) {
            String source = params[0];
            String destination = params[1];
            String timeout = params[2];
            if (!Utils.isInteger(timeout)) {
                return "timeout is not number";
            }
            return String.valueOf(listDataType.bRPopLPush(source, destination, Integer.parseInt(timeout)));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

}
