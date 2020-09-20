package com.icop.base.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/6/11
 * @description：
 */
public class WhiteList {

    /**
     * 不进行TOKEN认证的请求前缀
     */
    public static final String UN_AUTH_REQUEST_PREFIX_PATH = "/un";


    public static List<String> whiteList = new ArrayList<>(5);
    {
        whiteList.add(UN_AUTH_REQUEST_PREFIX_PATH);
    }

}
