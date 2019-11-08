package com.holmeszhu.data_type;

import java.util.LinkedList;
import java.util.List;

public class ListDataType extends CommonDataType {


    public long lPush(String key, String value) {
        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key);
            list.addFirst(value);
            redisMap.put(key, list);
            return list.size();
        } else {
            List<String> list = new LinkedList<>();
            list.add(value);
            redisMap.put(key, list);
            return list.size();
        }
    }


    public long rPush(String key, String value) {
        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key);
            list.add(value);
            redisMap.put(key, list);
            return list.size();
        } else {
            List<String> list = new LinkedList<>();
            list.add(value);
            redisMap.put(key, list);
            return list.size();
        }
    }


    public long lLen(String key) {
        if (redisMap.containsKey(key)) {
            LinkedList<String> linkedList = lRange(key);
            return linkedList.size();
        } else {
            return 0;
        }
    }


    public String lPop(String key, String value) {

        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key);
            list.removeFirst();
        }
        return null;

    }


    public String rPop(String key, String value) {

        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key);
            list.removeLast();
        }
        return null;

    }


    public LinkedList<String> lRange(String key) {
        return (LinkedList<String>) redisMap.get(key);
    }



}
