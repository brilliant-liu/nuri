package com.icop.base.exception;

/**
 * @author: liukj_mci
 * @date: 2019/4/23
 * @description： 业务描述异常抛出
 */

public class IException extends RuntimeException implements IThrowable {

    private int errCode;
    private String errDesc;

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }


    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrDesc() {
        return this.errDesc;
    }

    public IException(String errDesc){
        super(errDesc);
        setErrCode(errCode);
    }

    public IException(int errCode, String errDesc){
        super(errCode+":"+errDesc);
        setErrCode(errCode);
        setErrDesc(errDesc);
    }

    public IException(Throwable e){
        super(e);
    }

    public IException(int errCode, Throwable e){
        super(e);
        setErrCode(errCode);
    }
}
