package com.df.controller;

import com.df.entity.User;
import com.df.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanyli
 * @date 2020/12/8
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("{id}")
    public User findById(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(1000);
        return userService.findById(id);
    }
    @GetMapping("param")
    public void multiParam(@RequestParam String username, @RequestParam String age, @RequestParam String role) {
        log.info("{}:{}:{}", username, age, role);
    }
    @PostMapping("post")
    public void post(@RequestBody User user,@RequestParam String role) {
        log.info("{}:{}:{}", user.getUsername(), user.getAge(),role);
    }
}
