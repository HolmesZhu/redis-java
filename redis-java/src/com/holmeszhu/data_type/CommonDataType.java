package com.holmeszhu.data_type;

import java.util.HashMap;

public class CommonDataType {

    protected HashMap<String, Object> redisMap = new HashMap<>();

    /**
     * @param key
     * @return key是否存在 存在true 不存在false
     */
    public boolean exists(String key) {
        return redisMap.containsKey(key);
    }


    /**
     * @param key
     * @return 删除成功返回1  失败返回0
     */
    protected Integer del(String key) {
        if (redisMap.containsKey(key)) {
            redisMap.remove(key);
            return 1;
        }
        return 0;
    }


    protected void expire(String key, long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        del(key);
    }


    protected void pExpire(String key, long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        del(key);
    }


}
