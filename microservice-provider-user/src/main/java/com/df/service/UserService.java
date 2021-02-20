package com.df.service;

import com.df.entity.User;
import com.df.repository.UserRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanyli
 * @date 2020/12/8
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    @Lazy



    public User findById(Long id) {
        return userRepository.findOne(id);
    }

//    @Async
    public User add() {
        List<User> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        names.add("df");
        names.add("dfdf");
        names.add("dfdfdf");
        adduser(names);

        return null;
    }

    public void adduser(List<String> names) {
        UserService userService = (UserService) AopContext.currentProxy();
        names.forEach(name->{
            try {
                userService.addtran(name);
            } catch (Exception e) {
                System.out.println(name+";"+e.getMessage());
            }
        });
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addtran(String name) {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(name + 1);
            user.setAge(i);
            userRepository.save(user);
            if ("dfdf".equals(name) && i >= 5) {
                throw new RuntimeException("false");
            }
        }
    }
}
