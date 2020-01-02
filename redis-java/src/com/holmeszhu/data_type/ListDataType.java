package com.holmeszhu.data_type;


import com.holmeszhu.constant.CommonConstants;

import java.util.*;

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
    public String lPush(String key, LinkedList<String> values) {
        if (!exists(key)) {
            LinkedList<String> linkedList = new LinkedList<>();
            for (String value : values) {
                linkedList.addFirst(value);
            }
            redisMap.put(key, linkedList);
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        for (String value : values) {
            list.addFirst(value);
        }
        redisMap.put(key, list);
        return String.valueOf(list.size());
    }

    /**
     * @param key
     * @param value
     * @return
     * @description 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
     * 和 LPUSH key value [value …] 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
     */
    public String lPushX(String key, String value) {
        if (!exists(key)) {
            return "0";
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        if (list.size() == 0) {
            return "0";
        }
        list.addFirst(value);
        redisMap.put(key, list);
        return String.valueOf(list.size());
    }


    /**
     * @param key
     * @param values
     * @return
     * @description 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     */
    public String rPush(String key, List<String> values) {
        if (!exists(key)) {
            LinkedList<String> linkedList = new LinkedList<>(values);
            redisMap.put(key, linkedList);
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        list.addAll(values);
        redisMap.put(key, list);
        return String.valueOf(list.size());
    }


    /**
     * @param key
     * @param value
     * @return
     * @description 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
     */
    public String rPushX(String key, String value) {
        if (!exists(key)) {
            return "0";
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        if (list.size() == 0) {
            return "0";
        }
        list.add(value);
        redisMap.put(key, list);
        return String.valueOf(list.size());
    }


    /**
     * @param key
     * @return
     * @description 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * 如果 key 不是列表类型，返回一个错误。
     */
    public String lLen(String key) {
        if (!exists(key)) {
            return "0";
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> linkedList = lRange(key, 0, -1);
        return String.valueOf(linkedList.size());
    }


    /**
     * @param key
     * @return
     * @description 移除并返回列表 key 的头元素。
     */
    public String lPop(String key) {
        if (!exists(key)) {
            return null;
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        if (list.size() > 0) {
            return list.removeFirst();
        } else {
            return CommonConstants.EMPTY_LIST;
        }
    }


    /**
     * @param key
     * @return
     * @description 移除并返回列表 key 的尾元素。
     */
    public String rPop(String key) {
        if (!exists(key)) {
            return null;
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        if (list.size() > 0) {
            return list.removeLast();
        } else {
            return CommonConstants.EMPTY_LIST;
        }
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
        if (!exists(key)) {
            return result;
        }
        if (!listDataType(key)) {
            return result;
        }

        // 获取存在的key
        LinkedList<String> list = (LinkedList<String>) redisMap.get(key);

        if (list.size() == 0) {
            return list;
        }

        //排除不符合的下标
        if (start >= list.size() || start < -list.size() || stop < -list.size()) {
            return result;
        }


        // 根据start的大小重置下标  譬如原来是-1 代表倒数第一个元素  列表有5个元素  下标就是4
        if (start < 0) {
            start = list.size() + start;
        }

        //根据stop的大小重置下标
        if (stop < 0) {
            stop = list.size() + stop;
        }

        //如果start下标比stop大  返回空list
        if (start > stop) {
            return result;
        }

        if (stop >= list.size()) {
            stop = list.size() - 1;
        }

        for (int i = start; i <= stop; i++) {
            result.add(list.get(i));
        }
        return result;

    }

    //如果 source 不存在，返回null，并且不执行其他动作。
    public String rPopLPush(String source, String destination) {
        if (!exists(source)) {
            return null;
        }
        if (!exists(destination)) {
            redisMap.put(destination, new LinkedList<>());
        }
        if (!listDataType(source)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        if (!listDataType(destination)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }

        String value = rPop(source);
        //当source正常的时候
        if (!value.equals(CommonConstants.EMPTY_LIST)) {
            //添加另一个参数
            LinkedList<String> list = lRange(destination, 0, -1);
            list.addFirst(value);
            redisMap.put(destination, list);
        }
        return value;
    }


    /**
     * @param key
     * @param count
     * @param value
     * @return 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     * count 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     * count = 0 : 移除表中所有与 value 相等的值。
     */
    public String lRem(String key, int count, String value) {

        if (!exists(key)) {
            return "0";
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }
        LinkedList<String> list = lRange(key, 0, -1);
        //计算出列表中值等于value的数量
        int num = 0;
        for (String newValue : list) {
            if (newValue.equals(value)) {
                num++;
            }
        }
        // count = 0 时候，移除所有与value相等的值
        // 当列表中等于value的数据比count小的时候 说明我们最多只能删除num大小的数据 这种就类似count为0的情况 全部删除
        if (count == 0 || num < Math.abs(count)) {
            list.removeIf(item -> item.equals(value));
            return String.valueOf(num);
        } else {
            int tempCount = 0;
            if (count > 0) {
                tempCount = count;
            } else {
                //count<0 逆置列表 方便处理
                tempCount = -count;
                Collections.reverse(list);
            }
            //迭代器遍历列表 删除相应的value
            for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
                String item = iterator.next();
                if (item.equals(value)) {
                    iterator.remove();
                    tempCount--;
                }
                if (tempCount == 0) {
                    break;
                }
            }
            return String.valueOf(Math.abs(count));
        }

    }


    /**
     * @param key
     * @param index
     * @return
     * @description 返回列表 key 中，下标为 index 的元素。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * 如果 key 不是列表类型，返回一个错误。
     */
    public String lIndex(String key, int index) {

        if (!exists(key)) {
            return null;
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }

        LinkedList<String> list = lRange(key, 0, -1);

        //如果下标大于等于0  并且index的值小于list的大小  直接获取
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        //如果下标小于0，并且index的值 <= list 的大小  做特殊处理
        else if (index < 0 && Math.abs(index) <= list.size()) {
            return list.get(list.size() + index);
        }
        // 如果 index 参数的值不在列表的区间范围内(out of range)，返回null
        else {
            return null;
        }
    }

    /**
     * @param key
     * @param direct
     * @param pivot
     * @param value
     * @return
     * @description 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。
     * 当 pivot 不存在于列表 key 时，不执行任何操作。
     * 当 key 不存在时， key 被视为空列表，不执行任何操作。
     * 如果 key 不是列表类型，返回一个错误。
     */
    public String lInsert(String key, String direct, String pivot, String value) {


        if (!exists(key)) {
            return "0";
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }


        return "0";

    }


    /**
     * @param key
     * @param index
     * @param value
     * @return
     * @description
     */
    public String lSet(String key, int index, String value) {

        if (!exists(key)) {
            return CommonConstants.EMPTY_KEY;
        }
        if (!listDataType(key)) {
            return CommonConstants.WRONG_VALUE_TYPE;
        }

        LinkedList<String> list = lRange(key, 0, -1);

        if (list.size() == 0) {
            return CommonConstants.EMPTY_LIST;
        }

        if (index >= list.size()) {
            return "index out of range";
        }

        list.set(index, value);

        return "OK";

    }

    public String lTrim(String key, int start, int stop) {
        return "";
    }

    public String bLPop(List<String> keys, int timeout) {
        return "";
    }

    public String bRPop(List<String> keys, int timeout) {
        return "";
    }

    public String bRPopLPush(String source, String destination, int timeout) {
        return "";
    }

}
