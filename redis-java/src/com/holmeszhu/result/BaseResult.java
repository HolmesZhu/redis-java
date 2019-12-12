package com.holmeszhu.result;

import com.holmeszhu.constant.BaseResultCodeEnum;

import static com.holmeszhu.constant.BaseResultCodeEnum.SUCCESS;

public class BaseResult<T> {

    private BaseResultCodeEnum baseResultCodeEnum;
    private T result;


    //初始化默认就是成功的
    public BaseResult(){
        baseResultCodeEnum = SUCCESS;
    }

    public BaseResultCodeEnum getBaseResultCodeEnum() {
        return baseResultCodeEnum;
    }

    public void setBaseResultCodeEnum(BaseResultCodeEnum baseResultCodeEnum) {
        this.baseResultCodeEnum = baseResultCodeEnum;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
