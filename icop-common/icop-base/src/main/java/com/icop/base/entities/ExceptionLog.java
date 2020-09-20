package com.icop.base.entities;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: liukj
 * @date: 2020/6/13
 * @description：
 */
@Data
public class ExceptionLog implements Serializable {
    private static final long serialVersionUID = -4045732840402622443L;

    private String id;
    private int code;
    private String message;
    private String type;
    private String errorTrack;
    private LocalDateTime date;
    private String position;

    public ExceptionLog(){
        this.id = IdUtil.fastSimpleUUID();
    }
}
