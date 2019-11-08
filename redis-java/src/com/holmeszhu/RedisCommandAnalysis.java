package com.holmeszhu;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RedisCommandAnalysis {


    private static Set<String> firstCommandSet = new HashSet<>();


    static {

        firstCommandSet.add("exists");

        //string
        firstCommandSet.add("set");
        firstCommandSet.add("get");

        firstCommandSet.add("mset");
        firstCommandSet.add("mget");
        firstCommandSet.add("del");

        firstCommandSet.add("incr");
        firstCommandSet.add("decr");

        firstCommandSet.add("append");
        firstCommandSet.add("strlen");


        //list
        firstCommandSet.add("lpush");
        firstCommandSet.add("rpush");
        firstCommandSet.add("lrange");
        firstCommandSet.add("linsert");
        firstCommandSet.add("lindex");
        firstCommandSet.add("llen");

        firstCommandSet.add("lpop");
        firstCommandSet.add("rpop");


        firstCommandSet.add("lrem");
        firstCommandSet.add("ltrim");

        firstCommandSet.add("lset");


        //hash
        firstCommandSet.add("hset");
        firstCommandSet.add("hget");
        firstCommandSet.add("hdel");
        firstCommandSet.add("hlen");
        firstCommandSet.add("hmset");
        firstCommandSet.add("hmget");


        firstCommandSet.add("hexists");
        firstCommandSet.add("hkeys");
        firstCommandSet.add("hvals");
        firstCommandSet.add("hgetall");


        //set
        firstCommandSet.add("sadd");
        firstCommandSet.add("srem");
        firstCommandSet.add("scard");
        firstCommandSet.add("sismember");
        firstCommandSet.add("srandmember");
        firstCommandSet.add("spop");
        firstCommandSet.add("smembers");

        //zset
        firstCommandSet.add("zadd");
        firstCommandSet.add("zrem");
        firstCommandSet.add("zcard");
        firstCommandSet.add("zscore");
        firstCommandSet.add("zrank");
        firstCommandSet.add("zrange");
        firstCommandSet.add("zrevrange");
        firstCommandSet.add("zrangebyscore");

        firstCommandSet.add("zcount");

    }


    //截取命令  根据首个命令单词  也就是操作来判断调用什么方法

    public static void commandAnalysis(String s) throws Exception {
        String[] ss = s.split("\\s+");
        if (firstCommandSet.contains(ss[0])) {

            if (ss[0].equals("set")) {
                if (ss.length == 3) {


                } else if (ss.length == 4) {


                } else if (ss.length == 5) {

                }
            }

//            String methodName = CommandMethodEnum.getMethodNameByCommand(ss[0]);
//            if (methodName != null) {
//                StringStruct stringStruct = new StringStruct();
//
//                Method method = stringStruct.getClass().getMethod(methodName, Class.forName("java.lang.String"), String.class);
//                method.invoke(stringStruct, "key1", "test");
//
//                HashMap<String, String> hashMap = stringStruct.getMap();
//
//                System.out.println(hashMap.get("key1"));
//
//            }
        }

    }


    public static void main(String[] args) throws Exception {
        commandAnalysis("set 123 123");
    }
}
