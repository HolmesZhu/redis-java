package com.holmeszhu.data_type;

import com.sun.prism.impl.BaseContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDataType extends CommonDataType {

    public boolean listDataType(String key) {
        return redisMap.get(key) instanceof LinkedList;
    }

    /**
     * @param key
     * @param values
     * @return
     * @description 将一个或多个值 value 插入到列表 key 的表头
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头：
     * 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
     * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * 当 key 存在但不是列表类型时，返回一个错误。
     */
    public int lPush(String key, List<String> values) {
        if (exists(key)) {
            if (!listDataType(key)) {
                return -1;
            }
            LinkedList<String> list = lRange(key, 0, -1);
            for (String value : values) {
                list.addFirst(value);
            }
            redisMap.put(key, list);
            return list.size();
        } else {
            LinkedList<String> list = new LinkedList<>();
            for (String value : values) {
                list.addFirst(value);
            }
            redisMap.put(key, list);
            return list.size();
        }
    }

    /**
     * @param key
     * @param value
     * @return
     * @description 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
     * 和 LPUSH key value [value …] 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
     */
    public int lPushX(String key, String value) {
        if (exists(key)) {
            if (!listDataType(key)) {
                return -1;
            }
            LinkedList<String> list = lRange(key, 0, -1);
            list.addFirst(value);
            redisMap.put(key, list);
            return list.size();
        }
        return 0;
    }


    /**
     * @param key
     * @param values
     * @return
     * @description 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     */
    public int rPush(String key, List<String> values) {
        if (exists(key)) {
            if (!listDataType(key)) {
                return -1;
            }
            LinkedList<String> list = lRange(key, 0, -1);
            list.addAll(values);
            redisMap.put(key, list);
            return list.size();
        } else {
            LinkedList<String> list = new LinkedList<>(values);
            redisMap.put(key, list);
            return list.size();
        }
    }


    /**
     * @param key
     * @param value
     * @return
     * @description 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
     */
    public int rPushX(String key, String value) {
        if (exists(key)) {
            if (!listDataType(key)) {
                return -1;
            }
            LinkedList<String> list = lRange(key, 0, -1);
            list.add(value);
            redisMap.put(key, list);
            return list.size();
        }
        return 0;
    }


    /**
     * @param key
     * @return
     * @description 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * 如果 key 不是列表类型，返回一个错误。
     */
    public long lLen(String key) {
        if (exists(key)) {
            if (!listDataType(key)) {
                return -1;
            }
            LinkedList<String> linkedList = lRange(key, 0, -1);
            return linkedList.size();
        } else {
            return 0;
        }
    }


    /**
     * @param key
     * @return
     * @description 移除并返回列表 key 的头元素。
     */
    public String lPop(String key) {

        if (exists(key)) {
            if (!listDataType(key)) {
                return "ERROR";
            }
            LinkedList<String> list = lRange(key, 0, -1);
            return list.removeFirst();
        }
        return null;

    }


    /**
     * @param key
     * @return
     * @description 移除并返回列表 key 的尾元素。
     */
    public String rPop(String key) {

        if (exists(key)) {
            if (!listDataType(key)) {
                return "ERROR";
            }
            LinkedList<String> list = lRange(key, 0, -1);
            return list.removeLast();
        }
        return null;

    }


    /**
     * @param key
     * @param start
     * @param stop
     * @return
     * @description 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     */
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

    public String rPopLPush(String source, String destination) {
        if (exists(source)) {
            if (!listDataType(source)) {
                return "ERROR";
            }
            String value = rPop(source);
            List<String> values = new ArrayList<>();
            values.add(value);
            int result = lPush(destination, values);
            if (result == -1) {
                return "ERROR";
            }
            return value;
        }
        return null;

    }


//    public int lRem(String key, int count, String value) {
//
//
//    }


}
