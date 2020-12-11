package com.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author hanyli
 * @date 2020/12/11
 */
@SpringBootApplication
@EnableHystrixDashboard
public class MicroserviceDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDashboardApplication.class);
    }
}
