package com.holmeszhu.test;

import com.holmeszhu.data_type.StringDataType;
import com.holmeszhu.method.StringMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

    public static void test() {

        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("1", 123);
        hashMap.put("2", "12354");
        hashMap.put("3", 23L);
        hashMap.put("4", true);

        // 3. 使用Iterator遍历
        Iterator<Map.Entry<String, Object>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            System.out.println(entry.getValue() instanceof String);

        }


    }

    public static void main(String[] args) throws Exception {

//        test();

//        Map<String, String> valueMap = new HashMap<>();
//
//        valueMap.put("1", "2");
//
//        HashMap<String, Object> map = new HashMap<>();
//
//        map.put("1", "2");
//        map.put("2", 2);
//        map.put("3", 5L);
//        map.put("4", valueMap);
//
//
//        System.out.println(map.get("3") instanceof Map);


        Class[] classes = new Class[3];
        Arrays.fill(classes, String.class);

        StringDataType stringDataType = new StringDataType();

        Method[] methods = stringDataType.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().equals("set") && Arrays.equals(method.getParameterTypes(), classes)) {
                System.out.println(method.invoke(stringDataType, "1234", "12dsg344", "ahjd"));
            }
        }
        System.out.println(stringDataType.get("1234"));

    }
}
