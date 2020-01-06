package com.holmeszhu.data_type;

import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.util.Utils;

import java.util.*;

public class HashDataType extends CommonDataType {

    public boolean hashDataType(String hash) {
        return redisMap.get(hash) instanceof Map;
    }

    public String hSet(String hash, String field, String value) {

        if (!exists(hash)) {
            Map<String, String> map = new HashMap<>();
            map.put(field, value);
            redisMap.put(hash, map);
            return "1";
        }
        if (!hashDataType(hash)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }

        Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);
        valueMap.put(hash, value);
        redisMap.put(hash, valueMap);
        return "0";

    }

    public String hSetNx(String hash, String field, String value) {
        return "";
    }


    public String hGet(String hash, String field) {
        if (!exists(hash)) {
            return null;
        }
        if (!hashDataType(hash)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, String> map = (Map<String, String>) redisMap.get(hash);
        return map.get(field);
    }


    public String hExists(String hash, String field) {

        if (!exists(hash)) {
            return "0";
        }
        if (!hashDataType(hash)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);

        if (valueMap.containsKey(field)) {
            return "1";
        }
        return "0";


    }

    public String hDel(String key, Set<String> fields) {
        if (!exists(key)) {
            return "0";
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        int num = 0;
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        for (String field : fields) {
            if (map.containsKey(key)) {
                map.remove(field);
                num++;
            }
        }
        return String.valueOf(num);
    }

    public String hLen(String key) {
        if (!exists(key)) {
            return "0";
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        return String.valueOf(map.size());
    }

    public String hStrLen(String key, String field) {
        if (!exists(key)) {
            return "0";
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        String value = map.get(field);
        if (value == null) {
            return "0";
        } else {
            return String.valueOf(value.length());
        }
    }


    public String hIncrBy(String key, String field, int increment) {
        if (!exists(key)) {
            hSet(key, field, String.valueOf(increment));
            return String.valueOf(increment);
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        String value = map.get(field);
        if (value == null) {
            hSet(key, field, String.valueOf(increment));
            return String.valueOf(increment);
        } else {
            if (Utils.isInteger(value)) {
                int newValue = Integer.parseInt(value);
                map.put(field, String.valueOf(newValue + increment));
                return String.valueOf(newValue + increment);
            } else {
                return "value is null or value is not integer";
            }
        }
    }

    public String hIncrByFloat(String key, String field, double increment) {
        if (!exists(key)) {
            hSet(key, field, String.valueOf(increment));
            return String.valueOf(increment);
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        String value = map.get(field);
        if (value == null) {
            hSet(key, field, String.valueOf(increment));
            return String.valueOf(increment);
        } else {
            if (Utils.isDouble(value)) {
                double newValue = Double.parseDouble(value);
                map.put(field, String.valueOf(newValue + increment));
                return String.valueOf(newValue + increment);
            } else {
                return "value is null or value is not integer";
            }
        }
    }

    public String hMSet(String key, Map<String, String> map) {
        if (!exists(key)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                hSet(key, entry.getKey(), entry.getValue());
            }
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            hSet(key, entry.getKey(), entry.getValue());
        }
        return "OK";
    }

    public String hMGet(String key, Set<String> fields) {
        if (!exists(key)) {
            List<String> list = new ArrayList<>();
            for (String field : fields) {
                list.add(null);
            }
            return String.valueOf(list);
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        List<String> list = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        for (String field : fields) {
            list.add(map.get(key));
        }
        return String.valueOf(list);
    }

    public String hKeys(String key) {
        if (!exists(key)) {
            return "(empty list or set)";
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        List<String> list = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        list.addAll(map.keySet());
        return String.valueOf(list);
    }

    public String hVals(String key) {
        if (!exists(key)) {
            return "(empty list or set)";
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        List<String> list = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        list.addAll(map.values());
        return String.valueOf(list);
    }

    public String hGetAll(String key) {
        if (!exists(key)) {
            return "(empty list or set)";
        }
        if (!hashDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        List<String> list = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) redisMap.get(key);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getKey());
            list.add(entry.getValue());
        }
        return String.valueOf(list);
    }


}
