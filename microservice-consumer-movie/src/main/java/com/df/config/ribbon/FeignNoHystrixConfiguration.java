package com.df.config.ribbon;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author hanyli
 * @date 2020/12/11
 */
@Configuration
public class FeignNoHystrixConfiguration {
    @Bean
    @Scope("prototype")
    public Feign.Builder feign() {
        return Feign.builder();
    }
}
