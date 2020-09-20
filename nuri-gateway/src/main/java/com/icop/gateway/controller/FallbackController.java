package com.icop.gateway.controller;

import com.icop.base.entities.R;
import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liukj
 * @date: 2020/6/13
 * @description：
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public R fallback(){
        throw new MyException(ExceptionCode.SYSTEM_BUSY);
        //return R.fail(ExceptionCode.SYSTEM_BUSY);
    }
}
