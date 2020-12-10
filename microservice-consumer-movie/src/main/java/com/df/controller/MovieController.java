package com.df.controller;

import com.df.client.UserClient;
import com.df.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("feign/{id}")
    public User feignFindById(@PathVariable Long id) {
        return userClient.findById(id);
    }

    public User fallback(Long id) {
        log.info("-------"+id);
        User user = new User();
        user.setAge(-1);
        user.setName("fallback");
        return user;
    }
    @GetMapping("param")
    public void multiParam() {
        String username = "df";
        String age = "77";
        String role = "rr";
        userClient.multiParam(username, age, role);
    }

    @GetMapping("paramMap")
    public void paramMap() {
        String username = "dfdf";
        String age = "7766";
        String role = "rrrrr";
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("age", age);
        map.put("role", role);
        userClient.multiParam2(map);
    }
    @GetMapping("post")
    public void post() {
        String username = "dfdf";
        String age = "7766";
        String role = "rrrrr";
        User user = new User();
        user.setUsername(username);
        user.setAge(333);
        userClient.post(user,role);
    }


    @GetMapping("log")
    public void logUserInstance() {
//        ServiceInstance choose = loadBalancerClient.choose("eureka-provider-user");
        ServiceInstance choose = loadBalancerClient.choose("provider-user");
        log.info("{}:{}:{}", choose.getServiceId(), choose.getHost(), choose.getPort());
    }
}
