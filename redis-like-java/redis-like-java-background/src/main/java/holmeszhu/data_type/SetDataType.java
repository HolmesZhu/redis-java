package holmeszhu.data_type;



import holmeszhu.constant.CommonConstants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDataType extends CommonDataType {


    public boolean setDataType(String key) {
        return redisMap.get(key) instanceof HashSet;
    }

    public void setHashSet(String key, HashSet<String> hashSet) {
        redisMap.put(key, hashSet);
    }

    public String sAdd(String key, Set<String> members) {
        if (!exists(key)) {
            redisMap.put(key, members);
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        int num = 0;
        HashSet<String> hashSet = sMembers(key);
        for (String member : members) {
            if (!hashSet.contains(member)) {
                hashSet.add(member);
                num++;
            }
        }
        return String.valueOf(num);
    }

//    public int isMember(String key, String member) {
//
//    }


    public String sIsMember(String key, String member) {
        if (!exists(key)) {
            return "0";
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        HashSet<String> hashSet = sMembers(key);
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


    public String sRandMember(String key, int count) {
        if (!exists(key)) {
            return null;
        }
        if (!setDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
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

    public String sRem(String key, Set<String> members) {
        return "";
    }


    public String sMove(String source, String destination, String member) {
        return "";
    }

    public String sCard(String key) {
        return "";
    }


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

    public String sInter(Set<String> keys) {
        return "";
    }

    public String sInterStore(String destination, Set<String> keys) {
        return "";
    }

    public String sUnion(Set<String> keys) {
        return "";
    }

    public String sUnionStore(String destination, Set<String> keys) {
        return "";
    }

    public String sDiff(Set<String> keys) {
        return "";
    }

    public String sDiffStore(String destination, Set<String> keys) {
        return "";
    }
}
