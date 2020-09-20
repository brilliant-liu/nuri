package com.icop.schema.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icop.schema.dao.UserInfoMapper;
import com.icop.schema.dao.impl.CommonServicesImpl;
import com.icop.schema.entities.UserInfo;
import com.icop.schema.services.IUserInfoSvc;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Service;

/**
 * @author: liukj
 * @date: 2020/6/6
 * @description：
 */
@Service
public class UserInfoSvcImpl extends CommonServicesImpl<UserInfoMapper,UserInfo> implements IUserInfoSvc {

    @Override
    public UserInfo queryUserInfo(String id){
        UserInfo userInfo = baseMapper.selectById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        return userInfo;
    }

    @Override
    public UserInfo queryUserInfoByCondition(UserInfo userInfo){
        UserInfo userInfo1 = baseMapper.selectOne(new QueryWrapper(userInfo));
        return userInfo1;
    }
}
