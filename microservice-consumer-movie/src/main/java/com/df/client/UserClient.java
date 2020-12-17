package com.df.client;

import com.df.config.FeignLogConfiguration;
import com.df.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@FeignClient(name = "eureka-provider-user", configuration = FeignLogConfiguration.class,fallback =FallbackClient.class )
@FeignClient(name = "eureka-provider-user",fallbackFactory = FallBackFactory.class)
public interface UserClient {

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    @RequestMapping(value = "user/param", method = RequestMethod.GET)
    void multiParam(@RequestParam("username") String username, @RequestParam("age") String age,
                    @RequestParam("role") String role);

    @RequestMapping(value = "user/param", method = RequestMethod.GET)
    void multiParam2(@RequestParam Map<String, Object> map);

    @RequestMapping(value = "user/post", method = RequestMethod.POST)
    void post(@RequestBody User user, @RequestParam("role") String role);
}
