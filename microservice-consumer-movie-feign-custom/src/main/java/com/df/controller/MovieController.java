package com.df.controller;

import com.df.client.UserClient;
import com.df.entity.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
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
@Import(FeignClientsConfiguration.class)
public class MovieController {
    private UserClient userClient;
    private UserClient adminClient;
    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.userClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(UserClient.class, "http://eureka-provider-user/");
        this.adminClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(UserClient.class, "http://eureka-provider-user/");
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {

        return userClient.findById(id);
    }
    @GetMapping("user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {

        return adminClient.findById(id);
    }

    @GetMapping("log")
    public void logUserInstance() {
//        ServiceInstance choose = loadBalancerClient.choose("eureka-provider-user");
        ServiceInstance choose = loadBalancerClient.choose("provider-user");
        log.info("{}:{}:{}", choose.getServiceId(), choose.getHost(), choose.getPort());
    }
}
