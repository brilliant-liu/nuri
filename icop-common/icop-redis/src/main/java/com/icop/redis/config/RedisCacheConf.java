package com.icop.redis.config;

/**
 * @author: liukj_mci
 * @date: 2019/9/11
 * @description： redis缓存名称配置
 */
public enum RedisCacheConf {

    /** 默认缓存配置 */
    TEST1_CACHE("TEST1_CACHE","180"),
    TEST2_CACHE("TEST2_CACHE","120"),
    VERIFICATION_CODE_CACHE("VERIFICATION_CODE_CACHE","300"),
    /**开关缓存,缓存时间一周*/
    SWITCH_CACHE("SWITCH_CACHE","604800"),
    /**系统参数配置，缓存1周*/
    SYSTEM_CONFIG_CACHE("SYSTEM_CONFIG_CACHE","604800")
    ;
    /**缓存名称*/
    public String cacheName;
    /**缓存生效时间(单位:秒)*/
    public String entryTtl;

    RedisCacheConf(String cacheName, String entryTtl) {
        this.cacheName = cacheName;
        this.entryTtl = entryTtl;
    }

    /**是否以Json的格式序列化存储对象：0：否、1:是*/
    public static final String serializationType ="1";
}
