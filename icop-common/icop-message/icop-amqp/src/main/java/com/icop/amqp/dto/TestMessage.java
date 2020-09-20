package com.icop.amqp.dto;

import com.icop.amqp.entities.Message;
import com.icop.amqp.entities.MessageContent;
import lombok.Data;

import java.util.Date;

/**
 * @author: liukj
 * @date: 2020/8/30
 * @description：
 */
@Data
public class TestMessage extends MessageContent {
    private static final long serialVersionUID = -7286189502234599758L;

    public String content;
    public Date date;
}
