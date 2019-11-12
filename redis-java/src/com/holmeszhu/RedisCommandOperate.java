package com.holmeszhu;


import com.holmeszhu.method.*;
import com.holmeszhu.operator_method_enum.*;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * redis命令相关处理操作
 */
public class RedisCommandOperate {

    private static List<Method[]> methodsList = new ArrayList<>();

    static {
        methodsList.add(StringMethod.getInstance().getClass().getMethods());
        methodsList.add(HashMethod.getInstance().getClass().getMethods());
        methodsList.add(ListMethod.getInstance().getClass().getMethods());
        methodsList.add(SetMethod.getInstance().getClass().getMethods());
        methodsList.add(ZSetMethod.getInstance().getClass().getMethods());
    }


    private volatile static RedisCommandOperate redisCommandOperate;


    private RedisCommandOperate() {

    }

    public static RedisCommandOperate getSingleton() {
        if (redisCommandOperate == null) {
            synchronized (RedisCommandOperate.class) {
                if (redisCommandOperate == null) {
                    redisCommandOperate = new RedisCommandOperate();
                }
            }
        }
        return redisCommandOperate;
    }


    public String getMethodNameByOperate(String redisOperate) {
        CommonEnum[] commonEnums = CommonEnum.values();
        StringEnum[] stringEnums = StringEnum.values();
        ListEnum[] listEnums = ListEnum.values();
        HashEnum[] hashEnums = HashEnum.values();
        SetEnum[] setEnums = SetEnum.values();
        ZSetEnum[] zSetEnums = ZSetEnum.values();


        for (CommonEnum commonEnum : commonEnums) {
            if (commonEnum.getRedisOperate().equals(redisOperate)) {
                return commonEnum.getMethodName();
            }
        }

        for (StringEnum stringEnum : stringEnums) {
            if (stringEnum.getRedisOperate().equals(redisOperate)) {
                return stringEnum.getMethodName();
            }
        }

        for (ListEnum listEnum : listEnums) {
            if (listEnum.getRedisOperate().equals(redisOperate)) {
                return listEnum.getMethodName();
            }
        }

        for (HashEnum hashEnum : hashEnums) {
            if (hashEnum.getRedisOperate().equals(redisOperate)) {
                return hashEnum.getMethodName();
            }
        }

//        for (ListEnum listEnum : listEnums) {
//            if (listEnum.getRedisOperate().equals(redisOperate)) {
//                return listEnum.getMethodName();
//            }
//        }
//
//        for (ListEnum listEnum : listEnums) {
//            if (listEnum.getRedisOperate().equals(redisOperate)) {
//                return listEnum.getMethodName();
//            }
//        }


        return null;
    }


    public void parsingRedisCommand(String[] operate) throws Exception {

        String methodName = getMethodNameByOperate(operate[0]);

        if (methodName != null) {

            Class[] classes = new Class[operate.length - 1];
            Arrays.fill(classes, String.class);

            String[] params = new String[operate.length - 1];
            for (int i = 0; i < params.length; i++) {
                params[i] = operate[i + 1];
            }


            for (Method[] methods : methodsList) {
                for (Method method : methods) {
                    if (method.getName().equals(methodName) && Arrays.equals(method.getParameterTypes(), classes)) {
                        method.invoke(method, params);
                    }
                }
            }
        }

    }
}
