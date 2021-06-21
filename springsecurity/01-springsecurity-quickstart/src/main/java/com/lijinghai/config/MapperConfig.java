package com.lijinghai.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lijinghai.mapper")
public class MapperConfig {
}
