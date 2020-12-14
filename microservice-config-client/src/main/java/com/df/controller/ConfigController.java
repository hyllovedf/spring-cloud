package com.df.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanyli
 * @date 2020/12/14
 */
@RestController
public class ConfigController {
    @Value("${profile}")
    private String profile;

    @GetMapping("config")
    public String config() {
        return profile;
    }
}
