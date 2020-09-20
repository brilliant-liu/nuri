package com.icop.base.utils;
/**
 * @author: liukj
 * @date: 2020/6/17
 * @description：
 */
public class ExceptionUtils {

    public static String getStackTraceInfo(Throwable throwable){
        if(null != throwable){
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            StackTraceElement stackTraceElement = stackTrace[0];
            stackTraceElement.getClassName();
            stackTraceElement.getLineNumber();
            String string = stackTraceElement.toString();
            return string;
        }
        return null;
    }
}
