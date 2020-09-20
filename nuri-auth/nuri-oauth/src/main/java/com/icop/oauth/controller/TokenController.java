package com.icop.oauth.controller;

import com.icop.base.entities.R;
import com.icop.base.token.entities.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@RestController
@Slf4j
public class TokenController {

    public R<AuthInfo> verifyToken(){

        return R.successDef(new AuthInfo());
    }


}
