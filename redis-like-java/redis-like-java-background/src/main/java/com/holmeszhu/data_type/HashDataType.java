package com.holmeszhu.data_type;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashDataType extends CommonDataType {

    public boolean hashDataType(String hash) {
        return redisMap.get(hash) instanceof Map;
    }

    public String hSet(String hash, String field, String value) {
        if (exists(hash)) {
            if (!hashDataType(hash)) {
                return "-1";
            }
            Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);
            valueMap.put(hash, value);
            redisMap.put(hash, valueMap);
            return "0";
        }
        Map<String, String> map = new HashMap<>();
        map.put(field, value);
        redisMap.put(hash, map);
        return "1";
    }

    public String hSetNx(String hash, String field, String value) {
        return "";
    }


    public String hGet(String hash, String field) {

        if (exists(hash)) {

            if (!hashDataType(hash)) {
                return "ERROR";
            }
            Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);


        }
        return null;
    }


    public String hExists(String hash, String field) {

        if (exists(hash)) {

            if (!hashDataType(hash)) {
                return "-1";
            }
            Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);

            if (valueMap.containsKey(field)) {
                return "1";
            }
            return "0";

        }
        return "0";
    }

    public String hDel(String key, Set<String> fields) {
        return "";
    }


    public String hLen(String key) {
        return "";
    }

    public String hStrLen(String key, String field) {
        return "";
    }


    public String hIncrBy(String key, String field, int increment) {
        return "";
    }

    public String hIncrByFloat(String key, String field, double increment) {
        return "";
    }

    public String hMSet(String key, Map<String, String> map) {
        return "";
    }

    public String hMGet(String key, Set<String> fields) {
        return "";
    }

    public String hKeys(String key) {
        return "";
    }

    public String hVals(String key) {
        return "";
    }

    public String hGetAll(String key) {
        return "";
    }


}
