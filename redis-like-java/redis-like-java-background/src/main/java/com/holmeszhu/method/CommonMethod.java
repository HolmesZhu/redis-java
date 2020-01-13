package com.holmeszhu.method;

import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.data_type.CommonDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonMethod {

    private volatile static CommonMethod commonMethod;

    private CommonMethod() {

    }

    public static CommonMethod getInstance() {
        if (commonMethod == null) {
            synchronized (HashMethod.class) {
                if (commonMethod == null) {
                    commonMethod = new CommonMethod();
                }
            }
        }
        return commonMethod;
    }

    private CommonDataType commonDataType = new CommonDataType();

    public String exists(String[] params) {
        if (params.length >= 1) {
            List<String> list = new ArrayList<>(Arrays.asList(params));
            return commonDataType.exists(list);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String type(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return commonDataType.type(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String rename(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String newKey = params[0];
            return commonDataType.rename(key, newKey);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String renameNx(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String newKey = params[0];
            return commonDataType.renameNx(key, newKey);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String del(String[] params) {
        if (params.length >= 1) {

            List<String> list = new ArrayList<>(Arrays.asList(params));
            return commonDataType.del(list);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String keys(String[] params) {
        if (params.length == 1) {
            String pattern = params[0];
            return commonDataType.keys(pattern);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


}
