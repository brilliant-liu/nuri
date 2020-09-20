package com.icop.schema.dao;

import com.icop.schema.entities.UserInfo;
import com.icop.schema.test.Cache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author: liukj
 * @date: 2020/6/6
 * @description：
 */
//@CacheNamespace(implementation= com.icop.schema.test.Cache.class )
public interface UserInfoMapper extends CommonMapper<UserInfo> {

    public int deleteUserInfo(UserInfo userInfo);

    public UserInfo queryUserInfo(UserInfo userInfo);

    public String queryUserPwd(UserInfo userInfo);

    public int updateUserInfo(UserInfo userInfo);

    public int insertUserInfo(UserInfo userInfo);

}
