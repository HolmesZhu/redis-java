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

    /**
     * @param key
     * @param values
     * @return 不存在这个key  新建list添加
     * 存在key不是list类型 返回错误
     */
    public String lPush(String key, String[] values) {
        if (!listDataType.exists(key)) {
            LinkedList<String> list = new LinkedList<>();
            for (String value : values) {
                list.addFirst(value);
            }
            listDataType.setList(key, list);
            return String.valueOf(values.length);
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.lPush(key, Arrays.asList(values));
    }

    //存在疑惑  当这个list存在值后来删除之后  list也为空  不知这时候能否再次插入
    public String lPushX(String key, String value) {
        if (!listDataType.exists(key)) {
            return "0";
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.lPushX(key, value);
    }

    public String rPush(String key, String[] values) {
        if (!listDataType.exists(key)) {
            LinkedList<String> list = new LinkedList<>(Arrays.asList(values));
            listDataType.setList(key, list);
            return String.valueOf(values.length);
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.rPush(key, Arrays.asList(values));
    }

    public String rPushX(String key, String value) {
        if (!listDataType.exists(key)) {
            return "0";
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.rPushX(key, value);
    }

    public String lPop(String key) {
        if (!listDataType.exists(key)) {
            return null;
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.lPop(key);
    }

    public String rPop(String key) {
        if (!listDataType.exists(key)) {
            return null;
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.rPop(key);
    }

    //当不存在这个source的时候 返回null
    //当source不属于list返回错误
    public String rPopLPush(String source, String destination) {
        if (!listDataType.exists(source)) {
            return null;
        }
        if (!listDataType.exists(destination)) {
            listDataType.setList(destination, new LinkedList<>());
        }
        if (!listDataType.listDataType(source)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (!listDataType.listDataType(destination)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return listDataType.rPopLPush(source, destination);
    }

    public String lRem(String key, String count, String value) {
        if (!listDataType.exists(key)) {
            return "0";
        }
        if (!listDataType.listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (Utils.isInteger(count)) {
            return String.valueOf(listDataType.lRem(key, Integer.parseInt(count), value));
        } else {
            return "count is not int";
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

//    public String lRange(String key, String start, String stop) {
//
//    }


}
