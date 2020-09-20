package com.icop.amqp.enums;

/**
 * @author: liukj
 * @date: 2020/8/25
 * @description：
 */
public enum RoutingKeyEnum {
    /**
     * 路由
     */
    KEY_TEST("TEST.#"),
    KEY_ALL("#"),
    ;

    /**
     * 路由key
     */
    public String routingKey;

    RoutingKeyEnum(String routingKey) {
        this.routingKey = routingKey;
    }
}
