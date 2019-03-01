package com.liaocx.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liaocx.api.dao.BannerMapper;
import com.liaocx.api.model.domain.Banner;
import com.liaocx.api.service.TestService;
import com.liaocx.common.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


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

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private DataSource dataSource;

    @Override
    public String sayHello() {
        return "hello world ";
    }

    @Override
    public ServerResponse dataSource() throws SQLException {
        logger.info("开始打印dataSource相关信息: ");
        logger.info("**************" + dataSource.getClass().getName());
        Connection conn = dataSource.getConnection();
        logger.info("===>>>>>>>>>>>" + conn.toString());
        //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<?> resultList = jdbcTemplate.queryForList("select * from customer_swing_record");
        conn.close();
        return ServerResponse.createBySuccess(resultList);
    }

    @Override
    public ServerResponse mybatis() {
        Banner banner = bannerMapper.selectByPrimaryKey(Long.valueOf(1));
        return ServerResponse.createBySuccess(banner);
    }
}
