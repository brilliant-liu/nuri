package com.icop.oauth.config;

import com.icop.base.exception.AbstractDefaultGlobalExceptionProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liukj
 * @date: 2020/6/13
 * @description：
 */
@Configuration
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionProcessor extends AbstractDefaultGlobalExceptionProcessor {
}
