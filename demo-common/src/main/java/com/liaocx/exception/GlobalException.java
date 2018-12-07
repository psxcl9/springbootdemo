package com.liaocx.exception;

import com.liaocx.common.ResponseCode;
import com.liaocx.util.StringUtil;

/**
 *  项目全局异常
 *  @author liaocx
 *  @date 2018年12月3日
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    @SuppressWarnings("unused")
    private Long timestamp = System.currentTimeMillis();

    public GlobalException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public static void throwError(ResponseCode errEnum) {
        throw new GlobalException(errEnum.getCode(), errEnum.getMsg());
    }

    public static void throwError(ResponseCode errEnum, String param) {
        if (StringUtil.isEmpty(param)) {
            throwError(errEnum);
        } else {
            String msg = errEnum.getMsg()+" : "+ param;
            throw new GlobalException(errEnum.getCode(), msg);
        }

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
