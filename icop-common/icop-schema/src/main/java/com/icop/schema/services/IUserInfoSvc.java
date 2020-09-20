package com.icop.schema.services;

import com.icop.schema.dao.CommonServices;
import com.icop.schema.entities.UserInfo;

/**
 * @author: liukj
 * @date: 2020/6/6
 * @description：
 */
public interface IUserInfoSvc extends CommonServices<UserInfo> {

    public UserInfo queryUserInfo(String id);

    public UserInfo queryUserInfoByCondition(UserInfo userInfo);
}
