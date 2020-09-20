package com.icop.oauth.services.impl;

import com.ctc.wstx.shaded.msv_core.reader.GrammarReader;
import com.icop.base.token.entities.AuthInfo;
import com.icop.base.token.utils.JwtUtils;
import com.icop.oauth.services.ITokenSvc;
import com.icop.schema.entities.UserInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@Component
public class TokenSvcImpl implements ITokenSvc {

    @Override
    public String getToken(AuthInfo authInfo, long expire){
        Map<String, Object> fillTokenMap = JwtUtils.fillTokenMap(authInfo);
        return JwtUtils.buildJwtToken(fillTokenMap,expire);
    }

    @Override
    public AuthInfo getAuthInfo(UserInfo userInfo, long expire) {

        AuthInfo authInfo = new AuthInfo.Build()
                .setAccount(userInfo.getAccount())
                .setAvatar(userInfo.getUserPhoto())
                .setName(userInfo.getUserNickname())
                .setUserId(userInfo.getUserId())
                .build();

        // 获取token
        String token = getToken(authInfo, JwtUtils.EXPIRE_TOKEN);
        // 获取refreshToken
        String refreshToken = getToken(authInfo,JwtUtils.EXPIRE_REFRESH_TOKEN);
        authInfo.setToken(token);
        authInfo.setRefreshToken(refreshToken);
        authInfo.setTokenType(JwtUtils.TOKEN_TYPE);

        return authInfo;
    }
}
