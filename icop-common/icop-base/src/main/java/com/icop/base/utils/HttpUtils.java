package com.icop.base.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

//import javax.servlet.http.HttpServletRequest;
//import java.net.InetAddress;
//import java.net.UnknownHostException;

/**
 * @author: liukj
 * @date: 2020/3/26
 * @description：
 */
public class HttpUtils {

    /**
     *  根据请求获取请求者的IP
     * @param request
     * @return
     */
    /*public static String getRequestIp(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(ExceptionUtils.getStackTrace(e));
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割

        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }*/
}
