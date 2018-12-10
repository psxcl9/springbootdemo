package com.liaocx.controller;

import com.liaocx.common.ResponseCode;
import com.liaocx.common.ServerResponse;
import com.liaocx.exception.GlobalException;
import com.liaocx.util.OSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ServerResponse hello(String param) {
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        Boolean flag = true;
        if (!flag) {
            GlobalException.throwError(ResponseCode.ERR_PARAMETER, "flag is false");
        }
        return ServerResponse.createBySuccessMessage(param);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(MultipartFile[] file1) {
        String url = OSSUtil.batchuploadImg(file1);
        System.out.println(url);
    }

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public void error() {
        GlobalException.throwError(ResponseCode.DELETE_FAILURE, "删除失败");
    }

    @RequestMapping(value = "saveError", method = RequestMethod.GET)
    public void saveError() {
        GlobalException.throwError(ResponseCode.SAVE_FAILURE, "保存失败");
    }

    @RequestMapping(value = "succ", method = RequestMethod.GET)
    public ServerResponse succ() {
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public ServerResponse exception() {
        return ServerResponse.createByError();
    }

    @RequestMapping(value = "errorStatus", method = RequestMethod.GET)
    public ServerResponse errorStatus() {
        return ServerResponse.createByErrorStatus(ResponseCode.ACCESS_TOKEN_EXPIRE.getCode());
    }

}
