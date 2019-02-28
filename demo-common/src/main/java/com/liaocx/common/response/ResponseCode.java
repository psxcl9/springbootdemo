package com.liaocx.common.response;

/**
 * 服务端相应代码及描述
 * @author liaocx
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    /**
     *  通用错误码
     */
    ERROR(1, "ERROR"),

    UNKNOW_ERR(500, "系统异常"),
    MISSING_PARAMETER(100000, "缺少参数"),
    ERR_PARAMETER(100001, "参数错误"),
    ACCESS_TOKEN_EXPIRE(100002, "accessToken已过期"),
    MISS_ACCESS_TOKEN(100003, "无访问权限"),
    SAVE_FAILURE(100004, "保存失败"),
    DELETE_FAILURE(100005, "删除失败"),
    UPDATE_FAILURE(100006, "更新失败"),
    NO_DATA(100007, "暂无数据");

    private final int code;
    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
