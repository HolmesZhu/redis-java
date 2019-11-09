package com.holmeszhu.data_type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringDataType extends CommonDataType {

    /**
     * @param key
     * @return 判断这个key是否属于string类型的  前提是key一定存在
     */
    public boolean stringDataType(String key) {
        return redisMap.get(key) instanceof String;
    }


    /**
     * @param key
     * @param value 如果 key 已经持有其他值， SET 就覆写旧值， 无视类型。
     */
    public String set(String key, String value) {
        redisMap.put(key, value);
        return "OK";
    }

    /**
     * @param key
     * @param value
     * @return 在key不存在情况下 赋值
     * key已经存在  返回0   不管这个Key是不是string  不存在返回1   key不合法返回-1
     */
    public int setNx(String key, String value) {
        if (exists(key)) {
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
        if (exists(key)) {
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
        if (exists(key)) {
            if (!stringDataType(key)) {
                return "ERROR";
            }
            return (String) redisMap.get(key);
        }
        return null;
    }


    public String getSet(String key, String value) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return "ERROR";
            }
            String oldValue = get(key);
            set(key, value);
            return oldValue;
        } else {
            set(key, value);
            return null;
        }
    }


    public int strLen(String key) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            return String.valueOf(redisMap.get(key)).length();
        }
        return 0;
    }


    public int append(String key, String value) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String newValue = get(key) + value;
            set(key, newValue);
            return strLen(key);
        }
        set(key, value);
        return strLen(key);

    }

    public static boolean isValidLong(String key) {
        try {
            long value = Long.parseLong(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int incr(String key) {

        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String value = get(key);
            if (isValidLong(value)) {
                long newValue = Long.valueOf(value) + 1;
                set(key, String.valueOf(newValue));
            } else {
                return -1;
            }
        }
        set(key, "1");
        return 1;

    }


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
