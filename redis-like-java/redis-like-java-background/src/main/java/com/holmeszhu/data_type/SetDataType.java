package com.holmeszhu.data_type;


import com.holmeszhu.constant.CommonConstants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SetDataType extends CommonDataType {


    public boolean setDataType(String key) {
        return redisMap.get(key) instanceof HashSet;
    }


    public String sAdd(String key, Set<String> members) {
        if (!exists(key)) {
            redisMap.put(key, members);
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        int num = 0;
        Set<String> hashSet = (Set<String>) redisMap.get(key);
        for (String member : members) {
            if (!hashSet.contains(member)) {
                hashSet.add(member);
                num++;
            }
        }
        return String.valueOf(num);
    }


    public String sIsMember(String key, String member) {
        if (!exists(key)) {
            return "0";
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Set<String> hashSet = (Set<String>) redisMap.get(key);
        if (hashSet.contains(member)) {
            return "1";
        } else {
            return "0";
        }

    }

    public String sPop(String key) {
        if (!exists(key)) {
            return null;
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Set<String> hashSet = (Set<String>) redisMap.get(key);
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


    public String sRandMember(String key, int count) {
        if (!exists(key)) {
            return null;
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Set<String> hashSet = (Set<String>) redisMap.get(key);
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

    public String sRem(String key, Set<String> members) {
        if (!exists(key)) {
            return "0";
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Set<String> values = (Set<String>) redisMap.get(key);
        int num = 0;
        for (String member : members) {
            if (values.remove(member)) {
                num++;
            }
        }
        if (values.size() == 0) {
            del(key);
        }
        return String.valueOf(num);
    }


    public String sMove(String source, String destination, String member) {
        return "";
    }

    public String sCard(String key) {
        if (!exists(key)) {
            return "0";
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        Set<String> set = (Set<String>) redisMap.get(key);
        return String.valueOf(set);
    }


    public String sMembers(String key) {
        HashSet<String> hashSet = new HashSet<>();
        if (!exists(key)) {
            return CommonConstants.EMPTY_LIST_OR_SET;
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        return String.valueOf(redisMap.get(key));
    }

    public String sInter(List<String> keys) {
        Set<String> first = (Set<String>) redisMap.get(keys.get(0));
        for (String key : keys) {
            if (!exists(key)) {
                return CommonConstants.EMPTY_LIST_OR_SET;
            }
            if (!setDataType(key)) {
                return CommonConstants.WRONG_VALUE_TYPE;
            }
            Set<String> set = (Set<String>) redisMap.get(key);
            first.retainAll(set);
        }
        return String.valueOf(first);
    }

    public String sInterStore(String destination, List<String> keys) {
        Set<String> first = (Set<String>) redisMap.get(keys.get(0));
        for (String key : keys) {
            if (!exists(key)) {
                return CommonConstants.EMPTY_LIST_OR_SET;
            }
            if (!setDataType(key)) {
                return CommonConstants.WRONG_VALUE_TYPE;
            }
            Set<String> set = (Set<String>) redisMap.get(key);
            first.retainAll(set);
        }
        redisMap.put(destination, first);
        return String.valueOf(first.size());
    }

    public String sUnion(List<String> keys) {
        Set<String> first = new HashSet<>();
        for (String key : keys) {
            if (!exists(key)) {
                continue;
            }
            if (!setDataType(key)) {
                return CommonConstants.WRONG_VALUE_TYPE;
            }
            Set<String> set = (Set<String>) redisMap.get(key);
            first.addAll(set);
        }
        return String.valueOf(first);
    }

    public String sUnionStore(String destination, Set<String> keys) {
        Set<String> first = new HashSet<>();
        for (String key : keys) {
            if (!exists(key)) {
                continue;
            }
            if (!setDataType(key)) {
                return CommonConstants.WRONG_VALUE_TYPE;
            }
            Set<String> set = (Set<String>) redisMap.get(key);
            first.addAll(set);
        }
        return String.valueOf(first.size());
    }

    public String sDiff(List<String> keys) {
        Set<String> first = (Set<String>) redisMap.get(keys.get(0));
        for (String key : keys) {
            if (!exists(key)) {
                continue;
            }
            if (!setDataType(key)) {
                return CommonConstants.WRONG_VALUE_TYPE;
            }
            Set<String> set = (Set<String>) redisMap.get(key);
            first.removeAll(set);
        }
        return String.valueOf(first);
    }

    public String sDiffStore(String destination, List<String> keys) {
        Set<String> first = (Set<String>) redisMap.get(keys.get(0));
        for (String key : keys) {
            if (!exists(key)) {
                break;
            }
            if (!setDataType(key)) {
                return CommonConstants.WRONG_VALUE_TYPE;
            }
            Set<String> set = (Set<String>) redisMap.get(key);
            first.removeAll(set);
        }
        return String.valueOf(first.size());
    }
}
