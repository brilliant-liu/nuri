package com.icop.base.entities;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icop.base.enums.IExceptionCode;
import com.icop.base.exception.IException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
public class R<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = -1;
    public static final int TIMEOUT_CODE = -2;
    public static final int VALID_EX_CODE = -9;
    public static final int OPERATION_EX_CODE = -10;
    /**
     * ("响应编码:0/200-请求处理成功")
     */
    private int code;
    @JsonIgnore
    private Boolean defExec = true;
    /**
     *  响应数据
     */
    private T data;
    /**
     *  提示消息
     */
    private String msg = "ok";
    /**
     * 请求路径
     */
    private String path;
    /**
     * 附加数据
     */
    private Map<String, Object> extra;
    /**
     * 响应时间戳
     */
    private long timestamp = System.currentTimeMillis();

    private R() {
    }

    public R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.defExec = false;
    }

    public R(int code, T data, String msg, boolean defExec) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.defExec = defExec;
    }

    public static <E> R<E> result(int code, E data, String msg) {
        return new R(code, data, msg);
    }

    public static <E> R<E> success(E data) {
        return new R(0, data, "ok");
    }

    public static R<Boolean> success() {
        return new R(0, true, "ok");
    }

    public static <E> R<E> successDef(E data) {
        return new R(0, data, "ok", true);
    }

    public static <E> R<E> successDef() {
        return new R(0, (Object)null, "ok", true);
    }

    public static <E> R<E> successDef(E data, String msg) {
        return new R(0, data, msg, true);
    }

    public static <E> R<E> success(E data, String msg) {
        return new R(0, data, msg);
    }

    public static <E> R<E> fail(int code, String msg) {
        return new R(code, (Object)null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> fail(String msg) {
        return fail(-10, msg);
    }

    public static <E> R<E> fail(String msg, Object... args) {
        String message = msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试";
        return new R(-10, (Object)null, String.format(message, args));
    }

    public static <E> R<E> fail(IExceptionCode e,E data){
        return result(e.getCode(),data,e.getMsg());
    }

    public static <E> R<E> fail(IException e, E data){
        return result(e.getErrCode(),data,e.getErrDesc());
    }

    public static <E> R<E> fail(IExceptionCode exceptionCode) {
        return validFail(exceptionCode);
    }

    public static <E> R<E> fail(Throwable throwable) {
        return fail(-1, throwable != null ? throwable.getMessage() : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> validFail(String msg) {
        return new R(-9, (Object)null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> validFail(String msg, Object... args) {
        String message = msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试";
        return new R(-9, (Object)null, String.format(message, args));
    }

    public static <E> R<E> validFail(IExceptionCode exceptionCode) {
        return new R(exceptionCode.getCode(), (Object)null, exceptionCode.getMsg() != null && !exceptionCode.getMsg().isEmpty() ? exceptionCode.getMsg() : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> timeout() {
        return fail(-2, "请求超时，请稍候再试");
    }

    public R<T> put(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap(10);
        }

        this.extra.put(key, value);
        return this;
    }

    public Boolean getIsSuccess() {
        return this.code == 0 || this.code == 200;
    }

    public Boolean getIsError() {
        return !this.getIsSuccess();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public int getCode() {
        return this.code;
    }

    public Boolean getDefExec() {
        return this.defExec;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getPath() {
        return this.path;
    }

    public Map<String, Object> getExtra() {
        return this.extra;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public R<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public R<T> setDefExec(Boolean defExec) {
        this.defExec = defExec;
        return this;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> setPath(String path) {
        this.path = path;
        return this;
    }

    public R<T> setExtra(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    public R<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
