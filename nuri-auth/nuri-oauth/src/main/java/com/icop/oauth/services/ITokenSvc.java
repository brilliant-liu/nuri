package com.icop.oauth.services;

import com.icop.base.token.entities.AuthInfo;
import com.icop.schema.entities.UserInfo;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
public interface ITokenSvc {
    public String getToken(AuthInfo authInfo, long expire);

    public AuthInfo getAuthInfo(UserInfo userInfo, long expire);
}
