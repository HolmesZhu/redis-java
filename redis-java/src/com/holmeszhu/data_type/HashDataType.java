package com.holmeszhu.data_type;

import java.util.HashMap;
import java.util.Map;

public class HashDataType extends CommonDataType {

    public boolean hashDataType(String hash) {
        return redisMap.get(hash) instanceof Map;
    }

    public int hSet(String hash, String field, String value) {
        if (exists(hash)) {
            if (!hashDataType(hash)) {
                return -1;
            }
            Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);
            valueMap.put(hash, value);
            redisMap.put(hash, valueMap);
            return 0;
        }
        Map<String, String> map = new HashMap<>();
        map.put(field, value);
        redisMap.put(hash, map);
        return 1;

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


    public int hExists(String hash, String field) {

        if (exists(hash)) {

            if (!hashDataType(hash)){
                return -1;
            }
            Map<String, String> valueMap = (Map<String, String>) redisMap.get(hash);

            if (valueMap.containsKey(field)){
                return 1;
            }
            return 0;

        }
        return 0;
    }




}
