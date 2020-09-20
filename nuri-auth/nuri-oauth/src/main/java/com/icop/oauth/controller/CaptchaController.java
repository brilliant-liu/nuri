package com.icop.oauth.controller;

import com.icop.base.entities.WhiteList;
import com.icop.oauth.services.IValidateCodeSvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@RestController
public class CaptchaController {

    @Resource
    IValidateCodeSvc iValidateCodeSvc;

    @GetMapping(value = WhiteList.UN_AUTH_REQUEST_PREFIX_PATH+"/login/captcha")

    public void getValidateCode(@RequestParam String key, HttpServletResponse response){
        iValidateCodeSvc.getValidateCode(key,response);
    }
}
