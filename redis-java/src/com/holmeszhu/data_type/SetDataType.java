package com.holmeszhu.data_type;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SetDataType extends CommonDataType {


    public boolean setDataType(String key) {
        return redisMap.get(key) instanceof HashSet;
    }

    public void setHashSet(String key, HashSet<String> hashSet) {
        redisMap.put(key, hashSet);
    }

    public int sAdd(String key, HashSet<String> members) {
        int num = 0;
        HashSet<String> hashSet = sMembers(key);
        for (String member : members) {
            if (!hashSet.contains(member)) {
                hashSet.add(member);
                num++;
            }
        }
        return num;
    }

//    public int isMember(String key, String member) {
//
//    }


    public int sIsMember(String key, String member) {

        HashSet<String> hashSet = sMembers(key);
        if (hashSet.contains(member)) {
            return 1;
        } else {
            return 0;
        }

    }

    public String sPop(String key) {
        HashSet<String> hashSet = sMembers(key);
        if (hashSet.isEmpty()) {
            return null;
        } else {
            Iterator it = hashSet.iterator();
            String value = null;
            while (it.hasNext()) {
                value = (String) it.next();
                it.remove();
                //删除一个值后就立刻break退出 保存value的值
                break;
            }
            return value;
        }
    }


    public String sRandMember(String key) {
        HashSet<String> hashSet = sMembers(key);
        if (hashSet.isEmpty()) {
            return null;
        }
        Iterator it = hashSet.iterator();
        String value = null;
        while (it.hasNext()) {
            value = (String) it.next();
            //获得一个值后就立刻break退出 保存value的值
            break;
        }
        return value;
    }

//    public String sRandMember(String key, String count) {
//
//    }



    public HashSet<String> sMembers(String key) {
        HashSet<String> hashSet = new HashSet<>();
        if (exists(key)) {
            if (!setDataType(key)) {
                return hashSet;
            }
            return (HashSet<String>) redisMap.get(key);
        }
        return hashSet;
    }

}
