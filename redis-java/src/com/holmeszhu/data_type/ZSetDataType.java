package com.holmeszhu.data_type;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ZSetDataType extends CommonDataType {


    public boolean zSetDataType(String key) {
        return redisMap.get(key) instanceof TreeMap;
    }

    public String zAdd(String key, Map<Double, String> map) {
        return "";
    }

    public String zScore(String key, String member) {
        return "";
    }

    public String zIncrBy(String key, int increment, String member) {
        return "";
    }

    public String zCard(String key) {
        return "";
    }


    public String zCount(String key, int min, int max) {
        return "";
    }

    public String zRange(String key, int start, int stop) {
        return "";
    }

    public String zRangeWithScores(String key, int start, int stop) {
        return "";
    }

    public String zRevRange(String key, int start, int stop) {
        return "";
    }

    public String zRevRangeWithScores(String key, int start, int stop) {
        return "";
    }

    public String zRangeByScore(String key, int min, int max) {
        return "";
    }

    public String zRangeByScoreWithScores(String key, int min, int max) {
        return "";
    }

    public String zRevRangeByScore(String key, int min, int max) {
        return "";
    }

    public String zRevRangeByScoreWithScores(String key, int min, int max) {
        return "";
    }

    public String zRank(String key, String member) {
        return "";
    }

    public String zRevRank(String key, String member) {
        return "";
    }

    public String zRem(String key, Set<String> members) {
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
