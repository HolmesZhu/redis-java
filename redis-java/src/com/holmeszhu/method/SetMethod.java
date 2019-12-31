package com.holmeszhu.method;

import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.data_type.SetDataType;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.HashSet;

public class SetMethod {

    private volatile static SetMethod setMethod;

    private SetMethod() {

    }

    private SetDataType setDataType = new SetDataType();

    public static SetMethod getInstance() {
        if (setMethod == null) {
            synchronized (SetMethod.class) {
                if (setMethod == null) {
                    setMethod = new SetMethod();
                }
            }
        }
        return setMethod;
    }


    public String sAdd(String key, String[] members) {
        if (!setDataType.exists(key)) {
            HashSet<String> hashSet = new HashSet<>(Arrays.asList(members));
            setDataType.setHashSet(key, hashSet);
        }
        if (!setDataType.setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(members));
        return String.valueOf(setDataType.sAdd(key, hashSet));
    }

    public String sIsMember(String key, String member) {
        if (!setDataType.exists(key)) {
            return "0";
        }
        if (!setDataType.setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return String.valueOf(setDataType.sIsMember(key, member));
    }


    public String sPop(String key) {
        if (!setDataType.exists(key)) {
            return null;
        }
        if (!setDataType.setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return setDataType.sPop(key);
    }

    public String sRandMember(String key) {
        if (!setDataType.exists(key)) {
            return null;
        }
        if (!setDataType.setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return setDataType.sRandMember(key);
    }

//    public String sRem(String key,String member) {
//
//    }


}
