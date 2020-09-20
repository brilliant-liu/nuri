package com.icop.base.enums;

/**
 * @author: liukj
 * @date: 2020/8/27
 * @description：
 */
public enum Status {

    /**
     * 状态枚举
     */
    INIT("0","初始化"),
    ;
    /**
     * 状态值
     */
    public String status;
    /**
     * 状态名称
     */
    public String name;

    Status(String status, String name) {
        this.status = status;
        this.name = name;
    }
}
