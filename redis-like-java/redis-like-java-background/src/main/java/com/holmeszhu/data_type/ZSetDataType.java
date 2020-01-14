package com.holmeszhu.data_type;

import com.holmeszhu.constant.CommonConstants;

import java.util.*;

public class ZSetDataType extends CommonDataType {


    public boolean zSetDataType(String key) {
        return redisMap.get(key) instanceof LinkedHashMap;
    }

    public void sort(Map<String, Double> map) {
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        //升序排序
        list.sort(Comparator.comparing(Map.Entry::getValue));
        map.clear();
        for (Map.Entry<String, Double> mapping : list) {
            map.put(mapping.getKey(), mapping.getValue());
        }
    }

    public String zAdd(String key, Map<String, Double> map) {
        if (!exists(key)) {
            Map<String, Double> newMap = new LinkedHashMap<>(map);
            sort(newMap);
            redisMap.put(key, newMap);
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        int num = 0;
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) iterator.next();
            if (!memberScoreMap.containsKey(entry.getKey())) {
                num++;
                memberScoreMap.put(entry.getKey(), entry.getValue());
            }
        }
        sort(memberScoreMap);
        redisMap.put(key, memberScoreMap);
        return String.valueOf(num);
    }

    public String zScore(String key, String member) {
        if (!exists(key)) {
            return null;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        if (memberScoreMap.containsKey(member)) {
            return String.valueOf(memberScoreMap.get(member));
        }
        return null;
    }

    public String zIncrBy(String key, double increment, String member) {
        if (!exists(key)) {
            Map<String, Double> map = new LinkedHashMap<>();
            map.put(member, increment);
            redisMap.put(key, map);
            return String.valueOf(increment);
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        if (memberScoreMap.containsKey(member)) {
            double score = memberScoreMap.get(member);
            double newScore = score + increment;
            memberScoreMap.put(member, newScore);
            sort(memberScoreMap);
            redisMap.put(key, memberScoreMap);
            return String.valueOf(newScore);
        }
        Map<String, Double> map = new LinkedHashMap<>();
        map.put(member, increment);
        redisMap.put(key, map);
        return String.valueOf(increment);
    }

    public String zCard(String key) {
        if (!exists(key)) {
            return "0";
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        return String.valueOf(memberScoreMap.size());
    }

    public int findBoundaryLeft(int left, int right, Double[] a, double num) {
        if (left == right) return left;
        int mid = (left + right) / 2;
        if (a[mid] < num) {
            return findBoundaryLeft(mid + 1, right, a, num);
        } else {
            return findBoundaryLeft(left, mid, a, num);
        }
    }

    public int findBoundaryRight(int left, int right, Double[] a, double num) {
        if (left == right) return right;

        int mid = (left + right + 1) / 2;
        if (a[mid] > num) {
            return findBoundaryRight(left, mid - 1, a, num);
        } else {
            return findBoundaryRight(mid, right, a, num);
        }
    }

    public String zCount(String key, double min, double max) {
        if (!exists(key)) {
            return "0";
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        List<Double> list = (List<Double>) memberScoreMap.values();
        Double[] a = list.toArray(new Double[0]);

        int left = findBoundaryLeft(0, a.length - 1, a, min);
        int right = findBoundaryRight(0, a.length - 1, a, max);
        return String.valueOf(right - left);
    }

    public String zRange(String key, int start, int stop) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        int len = memberScoreMap.size();
        if (start < 0) {
            start = start + len;
        }
        if (start < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop < 0) {
            stop = stop + len;
        }
        if (stop < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > stop) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > len - 1) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop >= len) {
            stop = len - 1;
        }
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());

        List<String> list = new ArrayList<>();
        for (int i = start; i <= stop; i++) {
            Map.Entry<String, Double> entry = indexedList.get(i);
            list.add(entry.getKey());
        }
        return String.valueOf(list);
    }

    public String zRangeWithScores(String key, int start, int stop) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        int len = memberScoreMap.size();
        if (start < 0) {
            start = start + len;
        }
        if (start < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop < 0) {
            stop = stop + len;
        }
        if (stop < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > stop) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > len - 1) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop >= len) {
            stop = len - 1;
        }
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());

        Map<String, Double> map = new LinkedHashMap<>();
        for (int i = start; i <= stop; i++) {
            Map.Entry<String, Double> entry = indexedList.get(i);
            map.put(entry.getKey(), entry.getValue());
        }
        return String.valueOf(map);
    }

    public String zRevRange(String key, int start, int stop) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        int len = memberScoreMap.size();
        if (start < 0) {
            start = start + len;
        }
        if (start < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop < 0) {
            stop = stop + len;
        }
        if (stop < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > stop) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > len - 1) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop >= len) {
            stop = len - 1;
        }
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());

        List<String> list = new ArrayList<>();
        for (int i = stop; i >= start; i++) {
            Map.Entry<String, Double> entry = indexedList.get(i);
            list.add(entry.getKey());
        }
        return String.valueOf(list);
    }

    public String zRevRangeWithScores(String key, int start, int stop) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        int len = memberScoreMap.size();
        if (start < 0) {
            start = start + len;
        }
        if (start < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop < 0) {
            stop = stop + len;
        }
        if (stop < 0) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > stop) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (start > len - 1) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (stop >= len) {
            stop = len - 1;
        }
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());

        Map<String, Double> map = new LinkedHashMap<>();
        for (int i = start; i <= stop; i++) {
            Map.Entry<String, Double> entry = indexedList.get(i);
            map.put(entry.getKey(), entry.getValue());
        }
        return String.valueOf(map);
    }

    public String zRangeByScore(String key, double min, double max) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        List<Double> list = (List<Double>) memberScoreMap.values();
        Double[] a = list.toArray(new Double[0]);
        int left = findBoundaryLeft(0, a.length - 1, a, min);
        int right = findBoundaryRight(0, a.length - 1, a, max);
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());
        List<String> members = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            members.add(indexedList.get(i).getKey());
        }
        return String.valueOf(members);
    }

    public String zRangeByScoreWithScores(String key, double min, double max) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        List<Double> list = (List<Double>) memberScoreMap.values();
        Double[] a = list.toArray(new Double[0]);
        int left = findBoundaryLeft(0, a.length - 1, a, min);
        int right = findBoundaryRight(0, a.length - 1, a, max);
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());
        Map<String, Double> result = new LinkedHashMap();
        for (int i = left; i <= right; i++) {
            result.put(indexedList.get(i).getKey(), indexedList.get(i).getValue());
        }
        return String.valueOf(result);
    }

    public String zRevRangeByScore(String key, double min, double max) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        List<Double> list = (List<Double>) memberScoreMap.values();
        Double[] a = list.toArray(new Double[0]);
        int left = findBoundaryLeft(0, a.length - 1, a, min);
        int right = findBoundaryRight(0, a.length - 1, a, max);
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());
        List<String> members = new ArrayList<>();
        for (int i = right; i >= left; i++) {
            members.add(indexedList.get(i).getKey());
        }
        return String.valueOf(members);
    }

    public String zRevRangeByScoreWithScores(String key, double min, double max) {
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        List<Double> list = (List<Double>) memberScoreMap.values();
        Double[] a = list.toArray(new Double[0]);
        int left = findBoundaryLeft(0, a.length - 1, a, min);
        int right = findBoundaryRight(0, a.length - 1, a, max);
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());
        Map<String, Double> result = new LinkedHashMap();
        for (int i = right; i >= left; i++) {
            result.put(indexedList.get(i).getKey(), indexedList.get(i).getValue());
        }
        return String.valueOf(result);
    }

    public String zRank(String key, String member) {
        if (!exists(key)) {
            return null;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        if (memberScoreMap.get(member) == null) {
            return null;
        }
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());
        for (int i = 0; i < indexedList.size(); i++) {
            if (indexedList.get(i).getKey().equals(member)) {
                return String.valueOf(i);
            }
        }
        return "0";
    }

    public String zRevRank(String key, String member) {
        if (!exists(key)) {
            return null;
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);
        if (memberScoreMap.get(member) == null) {
            return null;
        }
        List<Map.Entry<String, Double>> indexedList = new ArrayList<>(memberScoreMap.entrySet());
        for (int i = 0; i < indexedList.size(); i++) {
            if (indexedList.get(i).getKey().equals(member)) {
                return String.valueOf(indexedList.size() - 1 - i);
            }
        }
        return "0";
    }


    public String zRem(String key, Set<String> members) {
        if (!exists(key)) {
            return "0";
        }
        if (!zSetDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Map<String, Double> memberScoreMap = (Map<String, Double>) redisMap.get(key);


        return "";
    }

    public String zRemRangeByRank(String key, int start, int stop) {
        return "";
    }

    public String zRemRangeByScore(String key, int min, int max) {
        return "";
    }

    public String zRangeByLex(String key, int min, int max) {
        return "";
    }

    public String zLexCount(String key, int min, int max) {
        return "";
    }

    public String zRemRangeByLex(String key, int min, int max) {
        return "";
    }


}
