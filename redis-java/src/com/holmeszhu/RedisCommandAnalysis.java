package com.holmeszhu;


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


    public void commandAnalysis(String s) {
        String[] ss = s.split("\\s+");
        if (firstCommandSet.contains(ss[0])){

        }

    }


    public static void main(String[] args) {

    }
}
