package com.holmeszhu.data_type;


import com.holmeszhu.util.Utils;

import java.util.*;
import java.util.concurrent.ExecutorService;


public class CommonDataType {

    protected Map<String, Object> redisMap = new HashMap<>();

    /**
     * @param key
     * @return key是否存在 存在true 不存在false
     */
    public boolean exists(String key) {
        return redisMap.containsKey(key);
    }

    public String exists(List<String> keys) {
        int num = 0;
        for (String key : keys) {
            if (exists(key)) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    public String type(String key) {
        if (!exists(key)) {
            return null;
        } else if (redisMap.get(key) instanceof String) {
            return "string";
        } else if (redisMap.get(key) instanceof List) {
            return "list";
        } else if (redisMap.get(key) instanceof Set) {
            return "set";
        } else if (redisMap.get(key) instanceof LinkedHashMap) {
            return "zset";
        } else if (redisMap.get(key) instanceof HashMap) {
            return "hash";
        }
        return "invalid type";
    }

    public String rename(String key, String newKey) {
        if (key.equals(newKey)) {
            return "err source and destination object are the same";
        }
        if (!exists(key)) {
            return "error no such key";
        }
        redisMap.put(newKey, redisMap.get(key));
        del(key);
        return "OK";
    }

    public String renameNx(String key, String newKey) {
        if (key.equals(newKey)) {
            return "err source and destination object are the same";
        }
        if (!exists(key)) {
            return "error no such key";
        }
        if (exists(newKey)) {
            return "0";
        }
        redisMap.put(newKey, redisMap.get(key));
        del(key);
        return "OK";
    }


    /**
     * @param key
     * @return 删除成功返回1  失败返回0
     */
    public Integer del(String key) {
        if (redisMap.containsKey(key)) {
            redisMap.remove(key);
            return 1;
        }
        return 0;
    }

    public String del(List<String> keys) {
        int num = 0;
        for (String key : keys) {
            if (del(key) == 1) {
                num++;
            }
        }
        return String.valueOf(num);
    }


    public String keys(String pattern) {
        return "OK";
    }


    protected void expire(String key, long seconds) {
        ExecutorService executorService = Utils.getSimpleExecutorService();
        executorService.execute(() -> {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            del(key);
        });

    }


    protected void pExpire(String key, long milliseconds) {
        ExecutorService executorService = Utils.getSimpleExecutorService();
        executorService.execute(() -> {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            del(key);
        });

    }


}
