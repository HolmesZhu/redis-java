package com.holmeszhu.test;

import com.holmeszhu.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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

    public static void test1(String[] ss) {
        for (String s : ss) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Exception {


//        Class[] classes = new Class[3];
//        Arrays.fill(classes, String.class);
//
//        StringDataType stringDataType = new StringDataType();
//
//        Method[] methods = stringDataType.getClass().getMethods();
//
//        for (Method method : methods) {
//            if (method.getName().equals("set") && Arrays.equals(method.getParameterTypes(), classes)) {
//                System.out.println(method.invoke(stringDataType, "1234", "12dsg344", "ahjd"));
//            }
//        }
//        System.out.println(stringDataType.get("1234"));
//
//        String s = "abcde";
//        System.out.println(s.substring(0, 5));
//        System.out.println(Double.parseDouble("5"));
//        double x = 3;
//        System.out.println(String.valueOf(x));
//
//        String s1 = null;
//        System.out.println(s1.equals("OK"));


//        System.out.println("1\n3");
//
//        Test test = new Test();
//        Method[] methods = test.getClass().getMethods();
//        Class[] classes = new Class[1];
//        classes[0] = String[].class;
//        String[] ss = {"1", "2", "3"};
//        for (Method method : methods) {
//            if (method.getName().equals("test1") && Arrays.equals(method.getParameterTypes(), classes)) {
//                method.invoke(test, (Object) ss);
//            }
//        }

        String s = "2";
        System.out.println(Utils.isDouble(s));


        Map<Double, String> map = new TreeMap<>();
        map.put(1.5, "zhuyao1");
        map.put(1.3, "zhuyao1");
        map.put(1.4, "zhuyao1");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
