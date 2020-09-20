package com.icop.base.token.entities;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@Data
public class Token implements Serializable {
    private static final long serialVersionUID = 2728343896145965840L;

    private String token;
    private String tokenType;
    private LocalDateTime expiration;

}
