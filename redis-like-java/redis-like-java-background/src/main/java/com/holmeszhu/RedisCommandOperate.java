package com.holmeszhu;



import com.holmeszhu.method.*;
import com.holmeszhu.operator_method_enum.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * redis命令相关处理操作
 */
public class RedisCommandOperate {

    //默认存放的是方法数组的list
    private static List<Method[]> methodsList = new ArrayList<>();

    private static List<Object> dataTypeMethodList = new ArrayList<>();

    //默认
    static {
        methodsList.add(StringMethod.getInstance().getClass().getMethods());
        methodsList.add(HashMethod.getInstance().getClass().getMethods());
        methodsList.add(ListMethod.getInstance().getClass().getMethods());
        methodsList.add(SetMethod.getInstance().getClass().getMethods());
        methodsList.add(ZSetMethod.getInstance().getClass().getMethods());

        dataTypeMethodList.add(StringMethod.getInstance());
        dataTypeMethodList.add(HashMethod.getInstance());
        dataTypeMethodList.add(ListMethod.getInstance());
        dataTypeMethodList.add(SetMethod.getInstance());
        dataTypeMethodList.add(ZSetMethod.getInstance());
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


    //根据redisOperate获得真正的方法名  一一对应关系
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

        for (SetEnum setEnum : setEnums) {
            if (setEnum.getRedisOperate().equals(redisOperate)) {
                return setEnum.getMethodName();
            }
        }

        for (ZSetEnum zSetEnum : zSetEnums) {
            if (zSetEnum.getRedisOperate().equals(redisOperate)) {
                return zSetEnum.getMethodName();
            }
        }


        return null;
    }


    //解析redis命令  根据命令的第一个单词即可确定操作
    public void parsingRedisCommand(String[] operate) throws Exception {

        String methodName = getMethodNameByOperate(operate[0]);

        if (methodName != null) {

            // classes 作为反射的参数  是一个Class类型的数组  存放的都是 String 类型
            Class[] classes = new Class[1];
            classes[0] = String[].class;

            //params 作为反射的参数  都是具体的值
            String[] params = new String[operate.length - 1];
            for (int i = 0; i < params.length; i++) {
                params[i] = operate[i + 1];
            }

            //根据方法名反射调用方法
            for (int i = 0; i < methodsList.size(); i++) {
                Method[] methods = methodsList.get(i);
                for (Method method : methods) {
                    if (method.getName().equals(methodName) && Arrays.equals(method.getParameterTypes(), classes)) {
                        System.out.println(method.invoke(dataTypeMethodList.get(i), (Object) params));
                    }
                }
            }
        } else {
            System.out.println("the command is not exists.");

        }
    }

}

