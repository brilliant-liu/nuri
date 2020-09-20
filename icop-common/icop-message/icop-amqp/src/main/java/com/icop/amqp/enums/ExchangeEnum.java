package com.icop.amqp.enums;

import org.springframework.amqp.core.ExchangeTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/8/25
 * @description：
 */
public enum ExchangeEnum {
    /**
     * <p> name：交换机名称
     * <p> durable：持久化
     * <p> autoDelete：自动删除
     */
    TOPIC_EXCHANGE("nuri.topic.ex",true,false, ExchangeTypes.TOPIC),
    DIRECT_EXCHANGE("nuri.direct.ex",true,false,ExchangeTypes.DIRECT),
    HEADER_EXCHANGE("nuri.header.ex",true,false, ExchangeTypes.HEADERS),
    FANOUT_EXCHANGE("nuri.fanout.ex",true,false,ExchangeTypes.FANOUT),
    /**
     * 死信队列
     */
    DEATH_EXCHANGE("dlx.ex",true,false,ExchangeTypes.TOPIC),
    ;

    ExchangeEnum(String name, boolean durable, boolean autoDelete, String exchangeType) {
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.exchangeType = exchangeType;
    }

    /**
     * 交换机名称
     */
    public String name;
    /**
     * 持久化
     */
    public boolean durable;
    /**
     * 自动删除
     */
    public boolean autoDelete;
    /**
     * 交换机类型
     */
    public String exchangeType;

    /**
     * 声明交换参数
     * key：存储交换机名称
     * value: 存储该交换机对应的交换参数
     */
    public static Map<String,Map<String, Object>> arguments = new HashMap<>();

    /**
     * 交换机列表
     */
    public static List<ExchangeEnum> exchangeList = new ArrayList<>();

    static {
        //交换机列表
        exchangeList.add(ExchangeEnum.DIRECT_EXCHANGE);
        exchangeList.add(ExchangeEnum.TOPIC_EXCHANGE);
        exchangeList.add(ExchangeEnum.HEADER_EXCHANGE);
        exchangeList.add(ExchangeEnum.FANOUT_EXCHANGE);
        exchangeList.add(ExchangeEnum.DEATH_EXCHANGE);

        // 设置交换机的交换参数
        //dlxMap.put("x-dead-letter-exchange","dlx.exchange");
        /*arguments.put(ExchangeEnum.DIRECT_EXCHANGE.name,null);
        arguments.put(ExchangeEnum.FANOUT_EXCHANGE.name,null);
        arguments.put(ExchangeEnum.HEADER_EXCHANGE.name,null);
        arguments.put(ExchangeEnum.TOPIC_EXCHANGE.name,null);
        arguments.put(ExchangeEnum.DEATH_EXCHANGE.name,dlxMap);*/
    }
}
