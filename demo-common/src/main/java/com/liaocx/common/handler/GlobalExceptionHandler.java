package com.liaocx.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.liaocx.common.exception.GlobalException;
import com.liaocx.common.response.ResponseCode;
import com.liaocx.common.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 全局异常处理
 * @author liaocx
 * @date 2018年12月3日
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = GlobalException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ServerResponse<Object> GlobalErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        GlobalException ge = (GlobalException) e;
        ServerResponse<Object> response = ServerResponse.createByErrorCodeMessage(ge.getCode(), ge.getMessage());
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        List<String> err = Stream.of(e.getStackTrace()).map(m -> m.toString()).collect(Collectors.toList());
        err.add(e.getMessage());
        ServerResponse<Object> response = ServerResponse.createByErrorStack(ResponseCode.UNKNOW_ERR.getCode(),
                ResponseCode.UNKNOW_ERR.getMsg(), err);
        logger.error(JSONObject.toJSONString(e));
        return response;
    }

}
