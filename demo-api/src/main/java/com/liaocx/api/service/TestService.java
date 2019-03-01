package com.liaocx.api.service;

import com.liaocx.common.response.ServerResponse;

import java.sql.SQLException;

public interface TestService {
    String sayHello();

    ServerResponse dataSource() throws SQLException;

    ServerResponse mybatis();
}
