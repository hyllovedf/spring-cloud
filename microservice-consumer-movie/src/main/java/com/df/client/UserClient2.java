package com.df.client;

import com.df.config.FeignLogConfiguration;
import com.df.config.ribbon.FeignNoHystrixConfiguration;
import com.df.config.ribbon.RibboConfiguration;
import com.df.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@FeignClient(name = "eureka-provider-user", configuration = FeignLogConfiguration.class,fallback =FallbackClient.class )
@FeignClient(name = "provider-user", path = "user",configuration = {FeignLogConfiguration.class},fallbackFactory = FallBackFactory2.class)
public interface UserClient2 {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    void multiParam(@RequestParam("username") String username, @RequestParam("age") String age,
                    @RequestParam("role") String role);

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    void multiParam2(@RequestParam Map<String, Object> map);

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    void post(@RequestBody User user, @RequestParam("role") String role);
}
