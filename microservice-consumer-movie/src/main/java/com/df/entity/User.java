package com.df.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hanyli
 * @date 2020/12/8
 */
@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}
