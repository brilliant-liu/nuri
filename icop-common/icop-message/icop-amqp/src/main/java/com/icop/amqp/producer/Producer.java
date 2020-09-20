package com.icop.amqp.producer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.icop.amqp.entities.Message;
import com.icop.amqp.entities.MessageContent;
import com.icop.base.entities.Constants;
import com.icop.base.enums.Status;
import com.icop.base.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: liukj
 * @date: 2020/8/27
 * @description：
 */
@Component
@Slf4j
public class Producer {

    @Autowired
    private RabbitTemplate myRabbitTemplate;

    public <T extends MessageContent> void convertAndSend (String exchange, String routingKey, T payload){
        Message<T> message = createMessage(payload);
        log.info("[生产消息]：{}", FastJsonUtils.object2String(message));
        myRabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

    public <T extends MessageContent> Message createMessage(T payload){
        Message message = new Message<>();
        message.setContent(payload);
        message.setSerialId(IdUtil.fastSimpleUUID());
        message.setSendTime(DateUtil.format(LocalDateTime.now(), Constants.FORMAT_YYYY_MM_DD_HHMMSS));
        message.setStatus(Status.INIT.status);
        return message;
    }
}
