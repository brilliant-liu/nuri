package com.icop.base.token.entities;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description： token 认证信息
 */
@Data
public class AuthInfo {

    private String token;
    private String tokenType;
    private String refreshToken;
    private String name;
    private String account;
    /**
     * 用户头像
     */
    private String avatar;
    private String userId;
    private String roleCode;
    private String roleName;
    /**
     * 到期时间
     */
    private LocalDateTime expiration;

    public AuthInfo(){}

    public static class Build{
        private AuthInfo authInfo;

        public Build(AuthInfo authInfo){
            this.authInfo = authInfo;
        }
        public Build(){
            this.authInfo = new AuthInfo();
        }

        public Build setToken(String token){
            authInfo.token = token;
            return this;
        }

        public Build setTokenType(String tokenType){
            authInfo.tokenType = tokenType;
            return this;
        }

        public Build setRefreshToken(String refreshToken){
            authInfo.refreshToken = refreshToken;
            return this;
        }

        public Build setName(String name){
            authInfo.name = name;
            return this;
        }

        public Build setAccount(String account){
            authInfo.account = account;
            return this;
        }

        public Build setAvatar(String avatar){
            authInfo.avatar = avatar;
            return this;
        }

        public Build setRole(String roleCode){
            authInfo.roleCode = roleCode;
            return this;
        }

        public Build setRoleName(String roleName){
            authInfo.roleName = roleName;
            return this;
        }

        public Build setUserId(String userId){
            authInfo.userId = userId;
            return this;
        }

        public Build setExpiration(LocalDateTime expiration){
            authInfo.expiration = expiration;
            return this;
        }

        public AuthInfo build(){
            return this.authInfo;
        }

    }

}
