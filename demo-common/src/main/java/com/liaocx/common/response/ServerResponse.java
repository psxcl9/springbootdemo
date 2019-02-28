package com.liaocx.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;

/**
 * 服务端响应对象
 * @author liaocx on 2017/10/16.
 *
 */
@JsonInclude(Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    /**
     * true/false
     */
    private Boolean success;
    /**
     *  返回状态码 "0：成功, "other": 失败
     */
    @JsonProperty("status")
    private Integer statusCode;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 封装对象
     */
    private T data;
    /**
     * 时间戳
     */
    private Long timestamp;

    private ServerResponse(Boolean isSuccess){
        this.success = isSuccess;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    private ServerResponse(Boolean isSuccess, Integer statusCode){
        this.success = isSuccess;
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    private ServerResponse(Boolean isSuccess, Integer statusCode, T data){
        this.success = isSuccess;
        this.statusCode = statusCode;
        this.data = data;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    private ServerResponse(Boolean isSuccess, Integer status, String msg) {
        this.success = isSuccess;
        this.statusCode = status;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    private ServerResponse(Boolean isSuccess, Integer statusCode, String msg, T data) {
        this.success = isSuccess;
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public Boolean getSuccess() {
        return success;
    }
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * SUCCESS1
     * @param <T>
     * @return 成功, 无返回数据, 主要用于手动返回相应对象
     */
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(Boolean.TRUE);
    }

    /**
     * SUCCESS2
     * @param msg
     * @param <T>
     * @return 成功, 有返回提示信息
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(Boolean.TRUE, ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * SUCCESS3
     * @param data
     * @param <T>
     * @return 成功, 有返回对象
     */
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(Boolean.TRUE, ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * SUCCESS4
     * @param msg
     * @param data
     * @param <T>
     * @return 成功, 有返回提示信息和对象
     */
    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(Boolean.TRUE, ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * ERROR1
     * @param <T>
     * @return 失败, 无返回数据, 主要用于手动返回相应对象
     */
    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(Boolean.FALSE);
    }

    /**
     * ERROR2
     * @param errorMessage
     * @param <T>
     * @return 失败, 有错误提示信息, 手动返回通用的错误信息
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(Boolean.FALSE, ResponseCode.ERROR.getCode(), errorMessage);
    }

    /**
     * ERROR3
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorStatus(Integer errorCode) {
        return new ServerResponse<T>(Boolean.FALSE, errorCode);
    }

    /**
     * ERROR4
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return 失败, 有错误状态码和提示信息, 用于全局异常返回
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(Integer errorCode, String errorMessage) {
        return new ServerResponse<T>(Boolean.FALSE, errorCode, errorMessage);
    }

    /**
     * ERROR5
     * @param errorCode
     * @param errorMessage
     * @param data
     * @param <T>
     * @return 失败, 有错误状态码、提示信息和异常堆栈, 用于全局default异常返回
     */
    public static <T> ServerResponse<T> createByErrorStack(Integer errorCode, String errorMessage, T data) {
        return new ServerResponse<T>(Boolean.FALSE, errorCode, errorMessage, data);
    }

}
