package com.icop.oauth.controller;

import com.alibaba.fastjson.JSON;
import com.icop.base.entities.LoginParamDTO;
import com.icop.base.entities.R;
import com.icop.base.entities.WhiteList;
import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.MyException;
import com.icop.base.token.entities.AuthInfo;
import com.icop.base.token.utils.JwtUtils;
import com.icop.oauth.services.ITokenSvc;
import com.icop.oauth.services.IValidateCodeSvc;
import com.icop.schema.dao.UserInfoMapper;
import com.icop.schema.entities.UserInfo;
import com.icop.schema.services.IUserInfoSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */

@RestController
@Slf4j
public class LoginController {

    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    IValidateCodeSvc validateCodeSvc;
    @Resource
    ITokenSvc tokenSvc;
    @Resource
    IUserInfoSvc iUserInfoSvc;

    @PostMapping(value = WhiteList.UN_AUTH_REQUEST_PREFIX_PATH+"/login/login")
    public R<AuthInfo> login(@RequestBody LoginParamDTO loginParamDTO){
        // loginParamDTO参数校验

        // 验证码判断
        boolean isCodeRight = validateCodeSvc.verifyValidateCode(loginParamDTO.getKey(), loginParamDTO.getCode());
        if(!isCodeRight){
            //return R.fail(ExceptionCode.CAPTCHA_ERROR.getCode(),ExceptionCode.CAPTCHA_ERROR.getMsg("验证码错误！"));
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(loginParamDTO.getAccount());
        userInfo.setPassword(loginParamDTO.getPassword());
        UserInfo userInfo1 = iUserInfoSvc.queryUserInfoByCondition(userInfo);
        userInfo1= iUserInfoSvc.queryUserInfo(loginParamDTO.getCode());

        if (null == userInfo1){
            throw new MyException(ExceptionCode.JWT_USER_INVALID);
        }
        // token获取
        AuthInfo authInfo = tokenSvc.getAuthInfo(userInfo1, JwtUtils.EXPIRE_TOKEN);
        log.info(JSON.toJSONString(authInfo));
        return R.successDef(authInfo);
    }

    @GetMapping(value = "/oauth/login/getUserInfo")
    public R<List<UserInfo>> getUserInfo(){

        List<UserInfo> tests = userInfoMapper.selectList(null);
        tests.stream().forEach(userInfo -> {
            System.out.println(userInfo.getUserId());
            System.out.println(userInfo.getUserNickname());
            System.out.println(userInfo.getUserPhone());
        });

        return R.success(tests);
    }


}
