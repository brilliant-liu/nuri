package com.icop.amqp.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/8/25
 * @description： 队列
 */
public enum QueueEnum {

    /**
     * 队列及其绑定关系枚举
     */
    QUEUQ_TEST("nuri.queue.test",true,false,false,ExchangeEnum.TOPIC_EXCHANGE.name,RoutingKeyEnum.KEY_TEST.routingKey),
    /**
     * 死信队列
     */
    QUEUQ_DLX("nuri.queue.dlx",true,false,false,ExchangeEnum.DEATH_EXCHANGE.name,RoutingKeyEnum.KEY_ALL.routingKey),
    ;


    /**
     * 队列的名称
     */
    public String name;
    /**
     * 队列持久化
     */
    public boolean durable;
    /**
     * 排他性（如果我们声明一个排他队列，则为true（该队列仅由声明者的队列使用）
     */
    public boolean exclusive;
    /**
     * 自动删除
     */
    public boolean autoDelete;
    /**
     * 绑定交换机的名称
     */
    public String exchangeName;
    /**
     * 路由key
     */
    public String routingKey;

    QueueEnum(String name, boolean durable, boolean exclusive, boolean autoDelete, String exchangeName, String routingKey) {
        this.name = name;
        this.durable = durable;
        this.exclusive = exclusive;
        this.autoDelete = autoDelete;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public static List<QueueEnum> queueLists = new ArrayList<>();
    public static Map<String, Map<String, Object>> arguments = new HashMap<>();
    private static Map<String, Object> dlxMap = new HashMap<>();
    private static Map<String, Object> testTtlMap = new HashMap<>();

    static {
        // 队列列表
        queueLists.add(QueueEnum.QUEUQ_TEST);
        queueLists.add(QueueEnum.QUEUQ_DLX);

        // 队列初始话参数列表
        // 死信队列参数设置
        dlxMap.put("x-dead-letter-exchange",ExchangeEnum.DEATH_EXCHANGE.name);
        // 测试队列，消息设置TTL
        // 队列的过期时间
        // testTtlMap.put("x-expires",60000);
        // 消息的过期时间
        testTtlMap.put("x-message-ttl",60000);
        arguments.put(QueueEnum.QUEUQ_DLX.name,dlxMap);
        arguments.put(QueueEnum.QUEUQ_TEST.name,testTtlMap);
    }
}
