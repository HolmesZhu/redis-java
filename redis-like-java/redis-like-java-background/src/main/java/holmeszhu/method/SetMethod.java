package holmeszhu.method;


import holmeszhu.constant.CommonConstants;
import holmeszhu.data_type.SetDataType;
import holmeszhu.util.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetMethod {

    private volatile static SetMethod setMethod;

    private SetMethod() {

    }

    private SetDataType setDataType = new SetDataType();

    public static SetMethod getInstance() {
        if (setMethod == null) {
            synchronized (SetMethod.class) {
                if (setMethod == null) {
                    setMethod = new SetMethod();
                }
            }
        }
        return setMethod;
    }


    public String sAdd(String[] params) {
        if (params.length >= 2) {
            String key = params[0];
            Set<String> set = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                set.add(params[i]);
            }
            return setDataType.sAdd(key, set);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sIsMember(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String member = params[0];
            return setDataType.sIsMember(key, member);

        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sPop(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return setDataType.sPop(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sRandMember(String[] params) {
        if (params.length == 2) {
            String key = params[0];
            String count = params[1];
            if (!Utils.isInteger(count)) {
                return "count is not member";
            }
            return setDataType.sRandMember(key, Integer.parseInt(count));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String sRem(String[] params) {
        if (params.length >= 2) {
            String key = params[0];
            Set<String> members = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                members.add(params[i]);
            }
            return setDataType.sRem(key, members);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sMove(String[] params) {
        if (params.length >= 3) {
            String source = params[0];
            String destination = params[1];
            String member = params[2];
            return setDataType.sMove(source, destination, member);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sCard(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return setDataType.sCard(key);
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sMembers(String[] params) {
        if (params.length == 1) {
            String key = params[0];
            return String.valueOf(setDataType.sMembers(key));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sInter(String[] params) {
        if (params.length >= 1) {
            Set<String> keys = new HashSet<>(Arrays.asList(params));
            return String.valueOf(setDataType.sInter(keys));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sInterStore(String[] params) {
        if (params.length >= 2) {
            String destination = params[0];
            Set<String> keys = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                keys.add(params[i]);
            }
            return String.valueOf(setDataType.sInterStore(destination, keys));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sUnion(String[] params) {
        if (params.length >= 1) {
            Set<String> keys = new HashSet<>(Arrays.asList(params));
            return String.valueOf(setDataType.sUnion(keys));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sUnionStore(String[] params) {
        if (params.length >= 2) {
            String destination = params[0];
            Set<String> keys = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                keys.add(params[i]);
            }
            return String.valueOf(setDataType.sUnionStore(destination, keys));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }


    public String sDiff(String[] params) {
        if (params.length >= 1) {
            Set<String> keys = new HashSet<>(Arrays.asList(params));
            return String.valueOf(setDataType.sDiff(keys));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }

    public String sDiffStore(String[] params) {
        if (params.length >= 2) {
            String destination = params[0];
            Set<String> keys = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                keys.add(params[i]);
            }
            return String.valueOf(setDataType.sDiffStore(destination, keys));
        } else {
            return CommonConstants.INVALID_PARAMS;
        }
    }




}
