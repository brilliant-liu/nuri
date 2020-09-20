package com.icop.schema.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: liukj
 * @date: 2020/6/6
 * @description：
 */

@Data
@TableName(value = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -9025733754342820783L;

    @TableId(value ="userId" )
    private String userId;
    @TableField(value = "userPhone")
    private String userPhone;
    /**
     * 账户
     */
    private String account;
    /**昵称*/
    @TableField(value = "userNickname")
    private String userNickname;
    @TableField(value = "userEmail")
    private String userEmail;
    /**性别*/
    private String gender;
    /**用户状态*/
    @TableField(value = "userStatus")
    private String userStatus;
    /**密码*/
    private String password;
    /**注册时间*/
    @TableField(value = "registerTime")
    private Date registerTime;
    /**用户头像*/
    @TableField(value = "userPhoto")
    private String userPhoto;

}