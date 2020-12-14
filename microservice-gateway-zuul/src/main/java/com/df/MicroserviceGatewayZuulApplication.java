package com.df;

import com.df.filter.PreRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
/*
声明一个zuul代理  该代理使用ribbon定位注册在eureka的服务
同时整合了hystrix  所有经过zuul的请求都会在hystrix命令中执行
 */
@EnableZuulProxy
public class MicroserviceGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulApplication.class, args);
    }

    @Bean
    public PreRequestFilter requestFilter() {
        return new PreRequestFilter();
    }
}
