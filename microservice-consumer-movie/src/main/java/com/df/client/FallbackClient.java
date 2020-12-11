package com.df.client;

import com.df.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 回退类需要实现feign client的接口
 *  @feignClient注解中指定fallback属性
 * @author hanyli
 * @date 2020/12/11
 */
@Component
class FallbackClient implements UserClient {
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setAge(-1);
        user.setName("fallback");
        return user;
    }

    @Override
    public void multiParam(String username, String age, String role) {

    }

    @Override
    public void multiParam2(Map<String, Object> map) {

    }

    @Override
    public void post(User user, String role) {

    }
}
