package com.icop.base.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.icop.base.entities.ExceptionLog;
import com.icop.base.entities.R;
import com.icop.base.enums.ExceptionCode;
import com.icop.base.enums.ExceptionType;
import com.icop.base.enums.IExceptionCode;
import com.icop.base.utils.ExceptionUtils;
import com.icop.base.utils.FastJsonUtils;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @author: liukj
 * @date: 2020/6/13
 * @description：
 */
@Slf4j
public abstract class AbstractDefaultGlobalExceptionProcessor {

    public AbstractDefaultGlobalExceptionProcessor(){};

    /**
     * 返回样例
     {
        "code": -9,
        "data": {
           "id": "4699617e757a403da67be1ad4f5ed584",
           "code": -9,
           "message": "统一验证参数异常",
           "type": "BUSI",
           "errorTrack": "MyException:  异常码：-9 异常原因：统一验证参数异常",
           "date": "2020-06-13T15:25:44.59",
           "position": null
        },
        "msg": "统一验证参数异常",
        "path": null,
        "extra": null,
        "timestamp": 1592033144602,
        "isSuccess": false,
        "isError": true
     }
     */
    public static String ERROR_FORMAT = "异常ID:[ {} ]：{}";

    @ExceptionHandler(MyException.class)
    public R myExceptionProcessor(MyException e){

        ExceptionLog exceptionLog = generateExceptionLog(e);
        log.error(ERROR_FORMAT,exceptionLog.getId(),FastJsonUtils.toJsonStringWithDateFormat(exceptionLog,FastJsonUtils.FORMAT_YMDHMS));

        // TODO 异常事件通知

        return R.fail(e,exceptionLog);
    }

    @ExceptionHandler(Exception.class)
    public R exceptionProcessor(Throwable e){

        ExceptionLog exceptionLog = generateExceptionLog(e);
        log.error(ERROR_FORMAT,exceptionLog.getId(),FastJsonUtils.toJsonStringWithDateFormat(exceptionLog,FastJsonUtils.FORMAT_YMDHMS));
        return R.fail(ExceptionCode.INTERNAL_SERVER_ERROR,exceptionLog);
    }

    public ExceptionLog generateExceptionLog(IException e){
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setCode(e.getErrCode());
        exceptionLog.setMessage(e.getErrDesc());
        exceptionLog.setType(ExceptionType.BUSI_TYPE.type);
        // 获取异常链中最尾端的异常的消息(即最早发生异常的位置)，消息格式为：{SimpleClassName}: {ThrowableMessage}
        exceptionLog.setErrorTrack(ExceptionUtil.getRootCauseMessage(e));
        exceptionLog.setDate(LocalDateTime.now());
        exceptionLog.setPosition(ExceptionUtils.getStackTraceInfo(e));
        return exceptionLog;
    }

    public ExceptionLog generateExceptionLog(Throwable e){
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setCode(ExceptionCode.INTERNAL_SERVER_ERROR.getCode());
        exceptionLog.setMessage(ExceptionCode.INTERNAL_SERVER_ERROR.getMsg());
        exceptionLog.setType(ExceptionType.UNKNOWN_ERROR.type);
        // 获取异常链中最尾端的异常的消息(即最早发生异常的位置)，消息格式为：{SimpleClassName}: {ThrowableMessage}
        exceptionLog.setErrorTrack(ExceptionUtil.getRootCauseMessage(e));
        exceptionLog.setDate(LocalDateTime.now());
        exceptionLog.setPosition(ExceptionUtils.getStackTraceInfo(e));
        return exceptionLog;
    }

}
