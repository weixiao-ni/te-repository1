package com.example.hello_springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "USR_001", description = "用户管理相关接口") // 通常对应设计书的 API ID 或模块名
@RestController
@RequestMapping("/api/v1/users")
public class APIController {
	@io.swagger.v3.oas.annotations.Operation(summary = "获取用户列表")
    @org.springframework.web.bind.annotation.GetMapping
    public String getUsers() {
        return "OK";
    }
   
}
