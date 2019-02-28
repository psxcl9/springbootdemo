package com.liaocx.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liaocx.api.service.TestService;


/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.5.7）
 * 		2）、配置服务提供者
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 *
 * @author liaocx
 *
 */
@Service(timeout = 8000, version = "1.0", cluster = "failfast", owner = "liaocx")
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello() {
        return "hello world ";
    }
}
