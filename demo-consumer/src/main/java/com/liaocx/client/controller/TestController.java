package com.liaocx.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.liaocx.common.response.ServerResponse;
import com.liaocx.api.service.TestService;
import com.liaocx.common.util.OSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Reference(version = "1.0")
    public TestService testService;

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ServerResponse hello(String param) {
        String data = testService.sayHello();
        return ServerResponse.createBySuccessMessage(data + "," + param);
    }

    @GetMapping("dataSource")
    public ServerResponse getDataSource() throws SQLException {
        return testService.dataSource();
    }

    @GetMapping("mybatis")
    public ServerResponse testMybatis() {
        return testService.mybatis();
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(MultipartFile[] file1) {
        String url = OSSUtil.batchuploadImg(file1);
        System.out.println(url);
    }
}
