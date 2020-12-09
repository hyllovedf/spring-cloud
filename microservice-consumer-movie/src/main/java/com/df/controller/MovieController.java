package com.df.controller;

import com.df.client.UserClient;
import com.df.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hanyli
 * @date 2020/12/8
 */
@Slf4j
@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {

        return restTemplate.getForObject("http://eureka-provider-user/user/" + id, User.class);
    }
    @GetMapping("feign/{id}")
    public User feignFindById(@PathVariable Long id) {
        return userClient.findById(id);
    }

    @GetMapping("log")
    public void logUserInstance() {
//        ServiceInstance choose = loadBalancerClient.choose("eureka-provider-user");
        ServiceInstance choose = loadBalancerClient.choose("provider-user");
        log.info("{}:{}:{}", choose.getServiceId(), choose.getHost(), choose.getPort());
    }
}
