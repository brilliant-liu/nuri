package com.icop.amqp.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liukj
 * @date: 2020/8/27
 * @description： message消息内容模板
 */
@Data
public class Message<T extends MessageContent> implements Serializable{
    private static final long serialVersionUID = -358831629389804803L;

    /**
     * 消息ID
     */
    private String id;
    /**
     * 流水ID(存放自定义的ID)
     */
    private String serialId;
    /**
     * 消息状态
     */
    private String status;
    /**
     * 消费时间
     */
    private String consumerTime;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 消息内容
     */
    private T content;


}
