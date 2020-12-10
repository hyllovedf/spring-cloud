package com.df.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign日志配置
 * 只对debug级日志
 * @author hanyli
 * @date 2020/12/10
 */
@Configuration
public class FeignLogConfiguration {
    @Bean
    Logger.Level level() {
        return Logger.Level.BASIC;
    }
}
