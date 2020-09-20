package com.icop.amqp.test;

import com.icop.amqp.entities.TestMessage;
import com.icop.amqp.enums.ExchangeEnum;
import com.icop.amqp.enums.RoutingKeyEnum;
import com.icop.amqp.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liukj
 * @date: 2020/8/27
 * @description：
 */
@RestController
public class TestController {

    @Autowired
    Producer producer;

    @GetMapping("/test/send")
    public String send(){
        TestMessage testMessage = new TestMessage();
        testMessage.setAge(25);
        testMessage.setName("刘可俊");
        testMessage.setPwd("123456");
        producer.convertAndSend(ExchangeEnum.TOPIC_EXCHANGE.name, "TEST.LKJ",testMessage);
        System.out.println("发送消息完毕！");
        return "发送消息完毕！";
    }
}
