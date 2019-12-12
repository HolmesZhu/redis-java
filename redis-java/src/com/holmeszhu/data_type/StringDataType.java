package com.holmeszhu.data_type;

import com.holmeszhu.result.BaseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.holmeszhu.constant.BaseResultCodeEnum.DATA_TYPE_ERROR;

public class StringDataType extends CommonDataType {

    /**
     * @param key
     * @return boolean
     * @description 判断这个键是否是string类型
     */
    private boolean stringDataType(String key) {
        return redisMap.get(key) instanceof String;
    }


    /**
     * @param key   键
     * @param value 值
     * @return String
     * @description 将字符串值 value 关联到 key 。
     * 如果 key 已经持有其他值， SET 就覆写旧值， 无视类型。
     * TODO 当 SET 命令对一个带有生存时间（TTL）的键进行设置之后， 该键原有的 TTL 将被清除。
     */
    public String set(String key, String value) {
        redisMap.put(key, value);
        return "OK";
    }

    /**
     * @param key
     * @param value
     * @return String
     * @description 只在键 key 不存在的情况下， 将键 key 的值设置为 value 。
     * 若键 key 已经存在， 则 SETNX 命令不做任何动作。
     */
    public String setNx(String key, String value) {
        if (exists(key)) {
            return null;
        }
        return set(key, value);
    }

    /**
     * @param key
     * @param seconds
     * @param value
     * @return String
     * @description 将键 key 的值设置为 value ， 并将键 key 的生存时间设置为 seconds 秒钟。
     * 如果键 key 已经存在， 那么 SETEX 命令将覆盖已有的值。
     * SETEX 命令的效果和以下两个命令的效果类似：
     * SET key value
     * EXPIRE key seconds  # 设置生存时间
     */
    public String setEx(String key, long seconds, String value) {
        set(key, value);
        expire(key, seconds);
        return "OK";
    }


    /**
     * @param key
     * @param milliseconds
     * @param value
     * @return String
     * @description 这个命令和 SETEX 命令相似， 但它以毫秒为单位设置 key 的生存时间， 而不是像 SETEX 命令那样以秒为单位进行设置。
     */
    public String pSetEx(String key, long milliseconds, String value) {
        set(key, value);
        pExpire(key, milliseconds);
        return "OK";
    }


    /**
     * @param key
     * @param value
     * @return String
     * @description 只在键已经存在时， 才对键进行设置操作。  和 NX 相反
     */
    public String setXX(String key, String value) {
        if (exists(key)) {
            return set(key, value);
        }
        return null;
    }


    /**
     * @param key
     * @return String
     * @description 当 key不属于string 类型时 返回error
     * 如果键 key 不存在， 那么返回null ； 否则， 返回键 key 的值。
     */
    public String get(String key) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return "ERROR";
            }
            return (String) redisMap.get(key);
        }
        return null;
    }


    /**
     * @param key
     * @param value
     * @return String
     * @description 将键 key 的值设为 value ， 并返回键 key 在被设置之前的旧值。
     * 如果键 key 没有旧值， 也即是说， 键 key 在被设置之前并不存在， 那么返回 null 。
     * 当键 key 存在但不是字符串类型时， 返回ERROR。
     */
    public String getSet(String key, String value) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return "ERROR";
            }
            String oldValue = get(key);
            set(key, value);
            return oldValue;
        } else {
            set(key, value);
            return null;
        }
    }


    /**
     * @param key
     * @return int
     * @description 返回键 key 储存的字符串值的长度。
     * 当键 key 不存在时， 命令返回 0 。
     * 当 key 储存的不是字符串值时， 返回一个错误。
     */
    public int strLen(String key) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            return String.valueOf(redisMap.get(key)).length();
        }
        return 0;
    }


    /**
     * @param key
     * @param value
     * @return int 追加 value 之后， 键 key 的值的长度。
     * @description 如果键 key 已经存在并且它的值是一个字符串， APPEND 命令将把 value 追加到键 key 现有值的末尾。
     * 如果 key 不存在， APPEND 就简单地将键 key 的值设为 value ， 就像执行 SET key value 一样。
     */
    public int append(String key, String value) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String newValue = get(key) + value;
            set(key, newValue);
            return strLen(key);
        }
        set(key, value);
        return strLen(key);
    }


    /**
     * @param key
     * @param offset
     * @param value
     * @return 从偏移量 offset 开始， 用 value 参数覆写(overwrite)键 key 储存的字符串值。
     * 不存在的键 key 当作空白字符串处理。
     */
    public int setRange(String key, int offset, String value) {
        //当key存在字符串的时候
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String str = get(key);
            //当偏移量大于str的长度时
            if (str.length() < offset) {
                StringBuilder sb = new StringBuilder(str);
                for (int i = 0; i < offset - strLen(key); i++) {
                    sb.append(" ");
                }
                String s = sb.append(value).toString();
                set(key, s);
                return s.length();
            } else {
                String s = str.substring(0, offset) + value;
                set(key, s);
                return s.length();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < offset; i++) {
                sb.append(" ");
            }
            String blankString = sb.toString();
            String s = blankString + value;
            set(key, s);
            return s.length();
        }
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return String
     * 返回键 key 储存的字符串值的指定部分， 字符串的截取范围由 start 和 end 两个偏移量决定 (包括 start 和 end 在内)。
     * 负数偏移量表示从字符串的末尾开始计数， -1 表示最后一个字符， -2 表示倒数第二个字符， 以此类推。
     * GETRANGE 通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求。
     */
    public String getRange(String key, int start, int end) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return "ERROR";
            }
            String str = get(key);
            int len = strLen(key);
            if (start < 0) {
                start = start + len >= 0 ? start + len : -start;
            }
            if (end < 0) {
                end = end + len >= 0 ? end + len : -end;
            }
            if (start <= end) {
                //当start >= 字符串长度时
                if (start >= len) {
                    return "";
                }
                //当start < 字符串长度时  且 end >= 字符串长度时
                if (end >= len) {
                    return str.substring(start, len);
                }
                //当 end < 字符串长度时
                return str.substring(start, end + 1);
            }
            return "";
        }
        //不存在key 直接返回null
        return null;

    }


    private boolean isValidInt(String key) {
        try {
            int value = Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDouble(String key) {
        try {
            double value = Double.parseDouble(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * @param key
     * @return int
     * @description 为键 key 储存的数字值加上一。
     * 如果键 key 不存在， 那么它的值会先被初始化为 0 ， 再incr
     * 如果键 key 储存的值不能被解释为数字， 那么 INCR 命令将返回-2。
     */
    public BaseResult<String> incr(String key) {
        BaseResult<String> result = new BaseResult<>();
        if (exists(key)) {
            if (!stringDataType(key)) {
                result.setBaseResultCodeEnum(DATA_TYPE_ERROR);
                return result;
            }
            String value = get(key);
            if (isValidInt(value)) {
                int newValue = Integer.parseInt(value) + 1;
                set(key, String.valueOf(newValue));
                result.setResult(String.valueOf(newValue));
                return result;
            } else {
                result.setResult("");
                return result;
            }
        }
        set(key, "1");
        result.setResult("1");
        return result;
    }


    /**
     * @param key
     * @param increment
     * @return 为键 key 储存的数字值加上增量 increment 。
     * 如果键 key 不存在， 那么键 key 的值会先被初始化为 0 ， 然后再执行 INCRBY 命令。
     * 如果键 key 储存的值不能被解释为数字， 那么 INCR 命令将返回一个错误。
     */
    public int incrBy(String key, int increment) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String value = get(key);
            if (isValidInt(value)) {
                int newValue = Integer.parseInt(value) + increment;
                set(key, String.valueOf(newValue));
                return newValue;
            } else {
                return -2;
            }
        }
        set(key, String.valueOf(increment));
        return increment;
    }

    /**
     * @param key
     * @param increment
     * @return
     * @description 为键 key 储存的数字值加上增量 increment 。
     * 如果键 key 不存在， 那么 INCRBYFLOAT 会先将键 key 的值设为 0 ， 然后再执行加法操作。
     * 如果命令执行成功， 那么键 key 的值会被更新为执行加法计算之后的新值， 并且新值会以字符串的形式返回给调用者。
     */
    public double incrByFloat(String key, double increment) {

        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String value = get(key);
            if (isValidDouble(value)) {
                double newValue = Double.parseDouble(value) + increment;
                set(key, String.valueOf(newValue));
                return newValue;
            } else {
                return -2;
            }
        }
        set(key, String.valueOf(increment));
        return increment;
    }


    /**
     *
     * @param key
     * @return
     * @description
     *
     */
    public int decr(String key) {
        if (exists(key)) {
            if (!stringDataType(key)) {
                return -1;
            }
            String value = get(key);
            if (isValidInt(value)) {
                int newValue = Integer.parseInt(value) - 1;
                set(key, String.valueOf(newValue));
                return newValue;
            } else {
                return -2;
            }

        }
        set(key, "1");
        return 1;
    }

//    public int decrBy(String ke, int increment) {
////
////    }


    public void mSet(List<Map.Entry<String, String>> entryList) {
        for (Map.Entry<String, String> entry : entryList) {
            redisMap.put(entry.getKey(), entry.getValue());
        }
    }


    public List<String> mGet(List<String> keys) {

        List<String> values = new ArrayList<>();

        for (String key : keys) {
            values.add(get(key));
        }
        return values;
    }


}
