package com.icop.amqp.entities;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

/**
 * @author: liukj
 * @date: 2020/8/27
 * @description：
 */
@Data
public class TestMessage extends MessageContent {
    private static final long serialVersionUID = 4119659084132689914L;

    private String name;
    private String pwd;
    private Integer age;

}
