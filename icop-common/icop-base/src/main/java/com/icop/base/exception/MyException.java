package com.icop.base.exception;

import com.icop.base.enums.ExceptionCode;
import com.icop.base.enums.IExceptionCode;

/**
 * @author: liukj_mci
 * @date: 2019/4/23
 * @description： 业务报错使用接口
 */
public class MyException extends IException {

    public MyException(String errDesc) {
        super(errDesc);
    }

    public MyException(int errCode, String errDesc) {
        super(errCode, errDesc);
    }

    public MyException(Throwable e) {
        super(e);
    }

    public MyException(int errCode, Throwable e) {
        super(errCode, e);
    }

    public MyException(IExceptionCode exceptionCode){
        super(exceptionCode.getCode(),exceptionCode.getMsg());
    }

}
