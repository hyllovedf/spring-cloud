package com.df.controller;

import com.df.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hanyli
 * @date 2020/12/8
 */
@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
       return restTemplate.getForObject("http://localhost:8000/user/" + id, User.class);
    }
}
