package com.icop.testservices.controller;

import com.icop.base.entities.LoginParamDTO;
import com.icop.schema.dao.UserInfoMapper;
import com.icop.schema.entities.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/6/5
 * @description：
 */
@RestController
@Slf4j
public class TestController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${user.name}")
    private String name;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "/test/services")
    public String testServices(HttpServletRequest httpServletRequest){

        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        System.out.println(userInfos);

        String blue = httpServletRequest.getParameter("blue");
        log.info(blue+ "========================"+httpServletRequest.getHeader("red"));
        log.info("查询结果：");
        return serverPort+": /test/services"+name;
    }


    @GetMapping(value = "/test/oauth/login")
    public String testLogin(HttpServletRequest httpServletRequest){

        String url = "http://nuri-oauth";
        LoginParamDTO loginParamDTO = new LoginParamDTO();
        loginParamDTO.setKey("123");
        loginParamDTO.setCode("19121612097141");
        loginParamDTO.setAccount("icop-dev");
        loginParamDTO.setPassword("YCkOO1klBjCDZOkAZZBY1X9CACXjOZYO");

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url + "/unoauth/login/login", loginParamDTO, String.class);

        log.info(stringResponseEntity.getBody());
        return serverPort+": /test/services"+name;
    }

}
