package com.icop.base.enums;

/**
 * @author: liukj
 * @date: 2020/6/13
 * @description：
 */
public enum ExceptionType {

    /**
     * 业务异常（MyException）
     * */
    BUSI_TYPE("BUSI"),
    UNKNOWN_ERROR("UNKNOWN"),

    ;

    public String type;
    ExceptionType(String type){
        this.type = type;
    }

}
