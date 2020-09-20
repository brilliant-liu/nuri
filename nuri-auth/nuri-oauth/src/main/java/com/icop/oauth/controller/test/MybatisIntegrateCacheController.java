package com.icop.oauth.controller.test;

import cn.hutool.json.JSONUtil;
import com.icop.schema.dao.UserInfoMapper;
import com.icop.schema.entities.UserInfo;
import com.icop.schema.services.impl.UserInfoSvcImpl;
import io.micrometer.core.instrument.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liukj
 * @date: 2020/7/14
 * @description：
 */

@RestController
@Slf4j
public class MybatisIntegrateCacheController {

    @Resource
    private UserInfoSvcImpl userInfoSvc ;

    @Resource
    UserInfoMapper userInfoMapper;

    @GetMapping(value = "/query")
    public String query(@RequestParam String id){
        //UserInfo userInfo = userInfoSvc.queryUserInfo(id);
        // UserInfo userInfo2 = userInfoMapper.selectById(id);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        String userInfo2 = userInfoMapper.queryUserPwd(userInfo);
        String userInfo3 = userInfoMapper.queryUserPwd(userInfo);
        //log.info(JSONUtil.toJsonStr(userInfo2));
        return "查询:"+ userInfo3;
    }

}
