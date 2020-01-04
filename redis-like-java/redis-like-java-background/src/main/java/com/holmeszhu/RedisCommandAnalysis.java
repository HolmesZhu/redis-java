package com.holmeszhu;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RedisCommandAnalysis {


    //命令第一个单词set集合
    private static Set<String> firstCommandSet = new HashSet<>();


    static {

        firstCommandSet.add("exists");
        firstCommandSet.add("del");

        //string
        firstCommandSet.add("set");
        firstCommandSet.add("setnx");
        firstCommandSet.add("setex");
        firstCommandSet.add("psetex");

        firstCommandSet.add("get");
        firstCommandSet.add("getset");


        firstCommandSet.add("strlen");
        firstCommandSet.add("append");

        firstCommandSet.add("setrange");
        firstCommandSet.add("getrange");

        firstCommandSet.add("incr");
        firstCommandSet.add("incrBy");
        firstCommandSet.add("incrByFloat");

        firstCommandSet.add("decr");
        firstCommandSet.add("decrBy");

        firstCommandSet.add("mset");
        firstCommandSet.add("msetNx");

        firstCommandSet.add("mget");


        //list
        firstCommandSet.add("lpush");
        firstCommandSet.add("lpushx");
        firstCommandSet.add("rpush");
        firstCommandSet.add("rpushx");
        firstCommandSet.add("lpop");
        firstCommandSet.add("rpop");
        firstCommandSet.add("rpoplpush");
        firstCommandSet.add("lrem");
        firstCommandSet.add("llen");
        firstCommandSet.add("lindex");
        firstCommandSet.add("linsert");
        firstCommandSet.add("lset");
        firstCommandSet.add("lrange");
        firstCommandSet.add("ltrim");
        firstCommandSet.add("blpop");
        firstCommandSet.add("brpop");
        firstCommandSet.add("brpoplpush");


        //hash
        firstCommandSet.add("hset");
        firstCommandSet.add("hsetnx");

        firstCommandSet.add("hget");
        firstCommandSet.add("hexists");

        firstCommandSet.add("hdel");
        firstCommandSet.add("hlen");
        firstCommandSet.add("hstrlen");

        firstCommandSet.add("hincrby");
        firstCommandSet.add("hincrbyfloat");

        firstCommandSet.add("hmset");
        firstCommandSet.add("hmget");


        firstCommandSet.add("hkeys");
        firstCommandSet.add("hvals");
        firstCommandSet.add("hgetall");
        firstCommandSet.add("hscan");


        //set
        firstCommandSet.add("sadd");
        firstCommandSet.add("sismember");
        firstCommandSet.add("spop");
        firstCommandSet.add("srandmember");
        firstCommandSet.add("srem");
        firstCommandSet.add("smove");
        firstCommandSet.add("scard");
        firstCommandSet.add("smembers");
        firstCommandSet.add("sscan");
        firstCommandSet.add("sinter");
        firstCommandSet.add("sinterstore");
        firstCommandSet.add("sunion");
        firstCommandSet.add("sunionstore");
        firstCommandSet.add("sdiff");
        firstCommandSet.add("sdiffstore");


        //zset
        firstCommandSet.add("zadd");
        firstCommandSet.add("zscore");
        firstCommandSet.add("zincrby");
        firstCommandSet.add("zcard");


        firstCommandSet.add("zcount");
        firstCommandSet.add("zrange");
        firstCommandSet.add("zrevrange");

        firstCommandSet.add("zrangebyscore");
        firstCommandSet.add("zrevrangebyscore");

        firstCommandSet.add("zrank");
        firstCommandSet.add("zrevrank");


        firstCommandSet.add("zrem");
        firstCommandSet.add("zremrangebyrank");

        firstCommandSet.add("zremrangebyscore");

        firstCommandSet.add("zrangebylex");

        firstCommandSet.add("zlexcount");
        firstCommandSet.add("zremrangebylex");
        firstCommandSet.add("zscan");

        firstCommandSet.add("zunionstore");
        firstCommandSet.add("zinterstore");

    }


    //截取命令  根据首个命令单词  也就是操作来判断调用什么方法

    public static void commandAnalysis(String s) throws Exception {
        String[] ss = s.split("\\s+");
        if (firstCommandSet.contains(ss[0])) {
            RedisCommandOperate redisCommandOperate = RedisCommandOperate.getSingleton();
            redisCommandOperate.parsingRedisCommand(ss);
        }

    }

    public static void x(String[] ss) {
        ss[0] = "123";
    }

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        int sum = 0;

        while (nextLine != null && !nextLine.equals("")) {
            commandAnalysis(nextLine);
            nextLine = scanner.nextLine();
        }

//        commandAnalysis("set 123 121243");
//        commandAnalysis("setnx 123 csdf");
    }
}
