package com.df;

import javafx.scene.media.MediaException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.df.config.ribbon.*")})
public class MicroserviceEurekaConsumerMovieApplication {

    @Bean
    @LoadBalanced//整合ribbon  使其具有负载均衡能力
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceEurekaConsumerMovieApplication.class, args);
    }

}
