package com.icop.oauth.controller;

import com.icop.base.entities.R;
import com.icop.base.entities.WhiteList;
import com.icop.base.enums.ExceptionCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liukj
 * @date: 2020/6/12
 * @description：
 */
@RestController
public class ExceptionFallbackController {

    @GetMapping(WhiteList.UN_AUTH_REQUEST_PREFIX_PATH+"/fallback")
    public R<String> exceptionFallback(){
        return R.fail(ExceptionCode.SYSTEM_BUSY);
    }
}
