package com.icop.gateway.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.icop.base.entities.Constants;
import com.icop.base.entities.WhiteList;
import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.MyException;
import com.icop.base.token.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * @author: liukj
 * @date: 2020/6/9
 * @description： JWT 认证过滤器
 */
@Slf4j
@Component
public class GlobalJwtAuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpRequest request = exchange.getRequest();
        requestRecord(request);
        HttpHeaders headers = request.getHeaders();
        String requestPath = request.getPath().toString();
        String requestUri = request.getURI().getPath();

        //TODO 白名单过滤
        boolean isWhiteList = isWhiteList(requestUri);
        if(isWhiteList){
            return chain.filter(exchange);
        }

        List<String> list = headers.get(Constants.AUTHORIZATION);
        if(CollectionUtil.isEmpty(list)){
            throw new MyException(ExceptionCode.JWT_PARSER_TOKEN_FAIL);
        }

        String requestToken = list.get(0);
        Claims claims = JwtUtils.parasAccessToken(requestToken);
        String userId = (String) claims.get("userId");


        return chain.filter(exchange);
    }


    public void requestRecord(ServerHttpRequest request){
        InetSocketAddress host = request.getRemoteAddress();
        String path = request.getURI().getPath();
        InetSocketAddress localAddress = request.getLocalAddress();
        String hostName = localAddress.getHostName();
        String ip = getRequestIp(request);
        String requestRecord = MessageFormat.format("请求主机名称:{0}，主机IP:{1}，请求路径：{2}",hostName,ip,path);
        log.info(requestRecord);
    }


    public static String getIpAddr(List<String> entryList,ServerHttpRequest request,Function<String,List<String>> fun){

        if(CollectionUtil.isEmpty(entryList)){
            return null;
        }
        String ipAddr = null;
        for(String key : entryList){
            List<String> ipAddrs = fun.apply(key);
            if(CollectionUtil.isEmpty(ipAddrs)){
                continue;
            }
            ipAddr = ipAddrs.get(0);
            if (!(ipAddr == null || ipAddr.length() == 0 || Constants.UN_KNOW_HOST.equalsIgnoreCase(ipAddr))) {
                return ipAddr;
            }
        }

        if (ipAddr == null || ipAddr.length() == 0 || Constants.UN_KNOW_HOST.equalsIgnoreCase(ipAddr)) {
            ipAddr = request.getRemoteAddress().getAddress().getHostAddress();
            if (Constants.LOCALHOST_IP.equals(ipAddr) || Constants.LOCALHOST_ADDR.equals(ipAddr)) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddr = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    return ExceptionCode.UNKNOWN_HOST.getMsg();
                }
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddr != null && ipAddr.length() > 15) {
            if (ipAddr.indexOf(Constants.COMMA_EN) > 0) {
                ipAddr = ipAddr.substring(0, ipAddr.indexOf(Constants.COMMA_EN));
            }
        }
        return ipAddr;
    }

    /**
     * 获取主机的IP
     * @param request
     * @return
     */
    public static String getRequestIp(ServerHttpRequest request) {

        HttpHeaders headers = request.getHeaders();
        if(null == headers){
            return ExceptionCode.UNKNOWN_HOST.getMsg();
        }

        List<String> entryList = new ArrayList();
        entryList.add("x-forwarded-for");
        entryList.add("Proxy-Client-IP");
        entryList.add("WL-Proxy-Client-IP");

        String ipAddress = getIpAddr(entryList,request,f -> headers.get(f));

        return ipAddress;
    }



    /**
     *  判断是否为白名单
     * @param path
     * @return
     */
    public boolean isWhiteList(String path){
        if(StringUtils.isBlank(path)){
            return false;
        }
        return path.startsWith(WhiteList.UN_AUTH_REQUEST_PREFIX_PATH);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
