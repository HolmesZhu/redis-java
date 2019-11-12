package com.holmeszhu.data_type;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetDataType extends CommonDataType {


    public boolean setDataType(String key) {
        return redisMap.get(key) instanceof Set;
    }

    public int sAdd(String key, List<String> members) {
        if (exists(key)) {
            if (!setDataType(key)) {
                return -1;
            }
            int num = 0;
            Set<String> set = sMembers(key);
            for (String member : members) {
                if (!set.contains(member)) {
                    set.add(member);
                    num++;
                }
            }
            return num;
        }
        Set<String> set = new HashSet<>(members);
        redisMap.put(key, set);
        return set.size();
    }

//    public int isMember(String key, String member) {
//
//    }

    public Set<String> sMembers(String key) {
        Set<String> set = new HashSet<>();
        if (exists(key)) {
            if (!setDataType(key)) {
                return set;
            }
            return (Set<String>) redisMap.get(key);
        }
        return set;
    }

}
