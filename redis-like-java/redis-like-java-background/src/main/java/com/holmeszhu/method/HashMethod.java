package com.holmeszhu.method;


import com.holmeszhu.constant.CommonConstants;
import com.holmeszhu.data_type.HashDataType;
import com.holmeszhu.util.Utils;

import java.util.*;

public class HashMethod {

    private volatile static HashMethod hashMethod;

    private HashMethod() {

    }

    public static HashMethod getInstance() {
        if (hashMethod == null) {
            synchronized (HashMethod.class) {
                if (hashMethod == null) {
                    hashMethod = new HashMethod();
                }
            }
        }
        return hashMethod;
    }

    private HashDataType hashDataType = new HashDataType();


    public String hSet(String[] params) {
        if (params.length == 3) {
            String hash = params[0];
            String field = params[1];
            String value = params[2];
            return hashDataType.hSet(hash, field, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hSetNx(String[] params) {
        if (params.length == 3) {
            String hash = params[0];
            String field = params[1];
            String value = params[2];
            return hashDataType.hSetNx(hash, field, value);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String hGet(String[] params) {
        if (params.length == 2) {
            String hash = params[0];
            String field = params[1];
            return hashDataType.hGet(hash, field);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String hExists(String[] params) {
        if (params.length == 2) {
            String hash = params[0];
            String field = params[1];
            return hashDataType.hExists(hash, field);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hDel(String[] params) {
        if (params.length >= 2) {
            String hash = params[0];
            Set<String> fields = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                fields.add(params[i]);
            }
            return hashDataType.hDel(hash, fields);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hLen(String[] params) {
        if (params.length == 1) {
            String hash = params[0];
            return hashDataType.hLen(hash);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hStrLen(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String field = params[1];
            return hashDataType.hStrLen(key, field);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hIncrBy(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String field = params[1];
            String increment = params[2];
            if (!Utils.isInteger(increment)) {
                return "increment is not number";
            }
            return hashDataType.hIncrBy(key, field, Integer.parseInt(increment));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hIncrByFloat(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String field = params[1];
            String increment = params[2];
            if (!Utils.isDouble(increment)) {
                return "increment is not number";
            }
            return hashDataType.hIncrByFloat(key, field, Double.parseDouble(increment));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hMSet(String[] params) {
        if (params.length >= 3) {
            String key = params[0];
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i < params.length; i += 2) {
                map.put(params[i], params[i + 1]);
            }
            return hashDataType.hMSet(key, map);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hMGet(String[] params) {
        if (params.length >= 2) {
            String key = params[0];
            Set<String> fields = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                fields.add(params[i]);
            }
            return hashDataType.hMGet(key, fields);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hKeys(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return hashDataType.hKeys(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hVals(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return hashDataType.hVals(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String hGetAll(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return hashDataType.hGetAll(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


}
