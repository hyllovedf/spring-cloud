package com.df.client;

import com.df.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 通过falllback factory可是了解到回退的具体原因
 * @FeignClient 注解中指定fallbackfactory属性
 * @author hanyli
 * @date 2020/12/11
 */
@Component
public class FallBackFactory2 implements FallbackFactory<UserClient2> {
    @Override
    public UserClient2 create(Throwable throwable) {
        return new UserClient2() {
            @Override
            public User findById(Long id) {
                User user = new User();
                user.setAge(-1);
                user.setName(throwable.getMessage());
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
        };
    }
}
