package com.icop.base.exception;

/**
 * @author: liukj_mci
 * @date: 2019/4/23
 * @description： 抛出异常的描述接口
 */
public interface IThrowable {
    int getErrCode();
    String getErrDesc();
}
