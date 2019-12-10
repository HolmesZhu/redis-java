package com.holmeszhu.data_type;

import java.util.LinkedList;
import java.util.List;

public class ListDataType extends CommonDataType {

    public boolean listDataType(String key) {
        return redisMap.get(key) instanceof List;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public int lPush(String key, String value) {
        if (redisMap.containsKey(key)) {
            if (!listDataType(key)) {
                return -1;
            }
            LinkedList<String> list = lRange(key, 0, -1);
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

//    public int lPushX(String key, String value) {
//        if (redisMap.containsKey(key)) {
//
//        }
//    }


    public long rPush(String key, String value) {
        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key, 0, -1);
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
            LinkedList<String> linkedList = lRange(key, 0, -1);
            return linkedList.size();
        } else {
            return 0;
        }
    }


    public String lPop(String key, String value) {

        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key, 0, -1);
            list.removeFirst();
        }
        return null;

    }


    public String rPop(String key, String value) {

        if (redisMap.containsKey(key)) {
            LinkedList<String> list = lRange(key, 0, -1);
            list.removeLast();
        }
        return null;

    }


    public LinkedList<String> lRange(String key, int start, int stop) {

        LinkedList<String> result = new LinkedList<>();
        LinkedList<String> list = (LinkedList<String>) redisMap.get(key);

        if (start < 0) {
            start = list.size() + start;
        }

        if (stop < 0) {
            stop = list.size() + stop;
        }

        if (start > stop) {
            return result;
        }

        if (start > list.size() - 1) {
            return result;
        }
        if (stop > list.size() - 1) {
            stop = list.size() - 1;
        }

        for (int i = start; i <= stop; i++) {
            result.add(list.get(i));

        }
        return result;

    }


}
