package holmeszhu.method;


import holmeszhu.constant.CommonConstants;
import holmeszhu.data_type.ZSetDataType;
import holmeszhu.util.Utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ZSetMethod {

    private volatile static ZSetMethod zSetMethod;

    private ZSetMethod() {

    }

    public static ZSetMethod getInstance() {
        if (zSetMethod == null) {
            synchronized (ZSetMethod.class) {
                if (zSetMethod == null) {
                    zSetMethod = new ZSetMethod();
                }
            }
        }
        return zSetMethod;
    }

    private ZSetDataType zSetDataType = new ZSetDataType();

    public String zAdd(String[] params) {
        if (params.length > 1 && params.length % 2 == 1) {
            String key = params[0];
            Map<Double, String> map = new TreeMap<>();
            for (int i = 1; i < params.length; i += 2) {
                String score = params[i];
                if (!Utils.isDouble(score)) {
                    return "store is not number";
                }
                String member = params[i + 1];
                map.put(Double.parseDouble(score), member);
            }
            return zSetDataType.zAdd(key, map);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zScore(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String member = params[1];
            return zSetDataType.zScore(key, member);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String zIncrBy(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String increment = params[1];
            if (!Utils.isDouble(increment)) {
                return "increment is not number";
            }
            String member = params[2];
            return zSetDataType.zIncrBy(key, Integer.parseInt(increment), member);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String zCard(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return zSetDataType.zCard(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zCount(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zCount(key, Integer.parseInt(min), Integer.parseInt(max));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRange(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String start = params[1];
            if (!Utils.isInteger(start)) {
                return "start is not number";
            }
            String stop = params[2];
            if (!Utils.isInteger(stop)) {
                return "stop is not number";
            }
            return zSetDataType.zRange(key, Integer.parseInt(start), Integer.parseInt(stop));
        } else if (params.length == 4) {
            String key = params[0];
            String start = params[1];
            if (!Utils.isInteger(start)) {
                return "start is not number";
            }
            String stop = params[2];
            if (!Utils.isInteger(stop)) {
                return "stop is not number";
            }
            String param = params[3];
            if (param.toLowerCase().equals("withscores")) {
                return zSetDataType.zRangeWithScores(key, Integer.parseInt(start), Integer.parseInt(stop));
            } else {
                return "withscores is wrong";
            }
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRangeByScore(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zRangeByScore(key, Integer.parseInt(min), Integer.parseInt(max));
        } else if (params.length == 4) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "start is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            String param = params[3];
            if (param.toLowerCase().equals("withscores")) {
                return zSetDataType.zRangeByScoreWithScores(key, Integer.parseInt(min), Integer.parseInt(max));
            } else {
                return "withscores is wrong";
            }
        } else if (params.length == 6) {
            return "";
        } else if (params.length == 7) {
            return "";
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRevRangeByScore(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zRevRangeByScore(key, Integer.parseInt(min), Integer.parseInt(max));
        } else if (params.length == 4) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            String param = params[3];
            if (param.toLowerCase().equals("withscores")) {
                return zSetDataType.zRevRangeByScoreWithScores(key, Integer.parseInt(min), Integer.parseInt(max));
            } else {
                return "withscores is wrong";
            }
        } else if (params.length == 6) {
            return "";
        } else if (params.length == 7) {
            return "";
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRank(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String member = params[0];
            return zSetDataType.zRank(key, member);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRevRank(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String member = params[0];
            return zSetDataType.zRevRank(key, member);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRem(String[] params) {
        if (params.length >= 2) {
            String key = params[0];
            Set<String> members = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                members.add(params[i]);
            }
            return zSetDataType.zRem(key, members);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRemRangeByRank(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String start = params[1];
            if (!Utils.isInteger(start)) {
                return "start is not number";
            }
            String stop = params[2];
            if (!Utils.isInteger(stop)) {
                return "stop is not number";
            }
            return zSetDataType.zRemRangeByRank(key, Integer.parseInt(start), Integer.parseInt(stop));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRemRangeByScore(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zRemRangeByScore(key, Integer.parseInt(min), Integer.parseInt(max));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRangeByLex(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zRangeByLex(key, Integer.parseInt(min), Integer.parseInt(max));
        } else if (params.length == 6) {
            return "";
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zLexCount(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zLexCount(key, Integer.parseInt(min), Integer.parseInt(max));
        }  else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String zRemRangeByLex(String[] params) {
        if (params.length == 3) {
            String key = params[0];
            String min = params[1];
            if (!Utils.isInteger(min)) {
                return "min is not number";
            }
            String max = params[2];
            if (!Utils.isInteger(max)) {
                return "max is not number";
            }
            return zSetDataType.zRemRangeByLex(key, Integer.parseInt(min), Integer.parseInt(max));
        } else if (params.length == 6) {
            return "";
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }
}



