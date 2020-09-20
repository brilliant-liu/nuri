package com.icop.oauth.services;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
public interface IValidateCodeSvc {

    public void getValidateCode(String key, HttpServletResponse response);

    public boolean verifyValidateCode(String key,String codeValue);
}
