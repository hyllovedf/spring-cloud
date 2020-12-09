package com.df.config;

import com.df.config.ribbon.RibboConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @RibbonClient 为特定的ribbon client指定ribbon的配置类
 * @author hanyli
 * @date 2020/12/9
 */
@Configuration
@RibbonClient(name = "eureka-provider-user",configuration = RibboConfiguration.class)
public class TestConfiguration {
}
