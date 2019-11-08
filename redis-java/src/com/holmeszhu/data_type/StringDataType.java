package com.holmeszhu.data_type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringDataType extends CommonDataType {

    /**
     * @param key
     * @param value
     */
    public String set(String key, String value) {
        redisMap.put(key, value);
        return "OK";
    }

    public int setNx(String key, String value) {
        if (redisMap.containsKey(key)) {
            return 0;
        } else {
            redisMap.put(key, value);
            return 1;
        }
    }

    public String setEx(String key, long seconds, String value) {
        set(key, value);
        expire(key, seconds);
        return "OK";
    }


    public String pSetEx(String key, long milliseconds, String value) {
        set(key, value);
        pExpire(key, milliseconds);
        return "OK";

    }

    public String setXX(String key, String value) {
        if (redisMap.containsKey(key)) {
            redisMap.put(key, value);
            return "OK";
        }
        return null;
    }


    /**
     * @param key
     * @return 当 key不属于string 类型时  返回error
     * 属于string 类型时 从map中返回值   不存在返回的是null
     */
    public String get(String key) {
        if (!stringDataType(key)) {
            return "ERROR";
        }
        return (String) redisMap.get(key);
    }


    public String getSet(String key, String value) {
        if (redisMap.containsKey(key)) {
            String result = get(key);
            set(key, value);
            return result;
        } else {
            set(key, value);
            return null;
        }
    }


    public boolean stringDataType(String key) {
        Object value = redisMap.get(key);
        if (value != null) {
            if (value instanceof String) {
                return true;
            }
        }
        return false;
    }

//    public long strLen(String key) {
//        String value = get(key);
//    }


    public void mSet(List<Map.Entry<String, String>> entryList) {
        for (Map.Entry<String, String> entry : entryList) {
            redisMap.put(entry.getKey(), entry.getValue());
        }
    }


    public List<String> mGet(List<String> keys) {

        List<String> values = new ArrayList<>();

        for (String key : keys) {
            values.add(get(key));
        }
        return values;
    }


}
