package com.example.demo.model;

import lombok.Data;

//User实体类对应数据库表的实体类
@Data
public class User {
    private Integer id;
    private String username;
    private String password;

}