package com.icop.base.utils;

/**
 * @author: liukj_mci
 * @date: 2019/6/25
 * @description： 线程使用数据工具
 */
public class ThreadLocalUtils {

    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    private ThreadLocalUtils() { }

    public static void setValue(Object obj){
        THREAD_LOCAL.set(obj);
    }

    public static Object getValue(){
        return ThreadLocalUtils.THREAD_LOCAL.get();
    }

    public static void removeValue(){
        ThreadLocalUtils.THREAD_LOCAL.remove();
    }

}
