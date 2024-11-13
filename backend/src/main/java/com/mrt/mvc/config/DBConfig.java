package com.mrt.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.mrt.mvc.model.dao")
public class DBConfig {

}
