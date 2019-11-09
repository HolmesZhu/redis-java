package com.holmeszhu.test;

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

    public static void main(String[] args) {
        test();
    }
}
