package com.liaocx.provider.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * provider dubbo 配置类
 * @author liaocx
 */
@Configuration
public class DubboConfig {

    @Value("${dubbo.protocolConfig.address}")
    private String address;

    @Value("${dubbo.zookeeper}")
    private String zookeeper;

    /**
     * 当前应用配置
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("provider");
        return applicationConfig;
    }

    /**
     * 当前连接注册中心配置
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(zookeeper);  //本地测试
        registryConfig.setClient("curator");
        registryConfig.setTimeout(5000);
        return registryConfig;
    }

    /**
     * 当前连接注册中心配置
     */
    @Bean
    public ProtocolConfig protocolConfig() {

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20000);
        protocolConfig.setHost(address);
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(7000);
        providerConfig.setHost(address);
        return providerConfig;
    }
}
