package com.df.client;

import com.df.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "eureka-provider-user")
public interface UserClient {

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}
