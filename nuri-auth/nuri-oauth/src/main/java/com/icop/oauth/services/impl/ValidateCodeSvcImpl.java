package com.icop.oauth.services.impl;

import com.icop.oauth.services.IValidateCodeSvc;
import com.icop.redis.config.RedisCacheConf;
import com.icop.redis.utils.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@Component
public class ValidateCodeSvcImpl implements IValidateCodeSvc {

    private static Map<String,String> cacheMap = new HashMap<>(11);
    @Override
    public void getValidateCode(String key, HttpServletResponse response) {
        Captcha arithmeticCaptcha  = createCaptcha("gif");
        //cacheMap.put(key,arithmeticCaptcha.text());
        RedisUtils.put(RedisCacheConf.VERIFICATION_CODE_CACHE.cacheName,key,arithmeticCaptcha.text());

        String value = RedisUtils.getValue(RedisCacheConf.VERIFICATION_CODE_CACHE.cacheName,key);
        System.out.println(value);

        try {
            arithmeticCaptcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyValidateCode(String key,String codeValue) {
        String code = cacheMap.get(key);
        return !StringUtils.isBlank(code) && code.equals(codeValue);
    }

    /**
     * 选择验证码的种类
     * @param type
     * @return
     */
    private Captcha createCaptcha(String type) {
        Captcha captcha;
        if (StringUtils.equalsIgnoreCase(type, "gif")) {
            captcha = new GifCaptcha(115, 42, 4);
        } else if (StringUtils.equalsIgnoreCase(type, "png")) {
            captcha = new SpecCaptcha(115, 42, 4);
        } else if (StringUtils.equalsIgnoreCase(type, "chinese")) {
            captcha = new ChineseCaptcha(115, 42);
        } else  /*if (StringUtils.equalsIgnoreCase(type, "arithmetic")) */ {
            captcha = new ArithmeticCaptcha(115, 42);
        }
        captcha.setCharType(2);
        return captcha;
    }


}
