package com.icop.base.entities;

import lombok.Data;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@Data
public class LoginParamDTO  {
    /**
     *  验证码key
     */
    private String key;
    /**
     * 验证码
     */
    private String code;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
