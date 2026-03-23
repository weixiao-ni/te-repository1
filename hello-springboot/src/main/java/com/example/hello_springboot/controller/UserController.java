package com.example.hello_springboot.controller;

import org.springframework.web.bind.annotation.*;

import com.example.hello_springboot.entity.User;
import com.example.hello_springboot.service.UserService;

import java.util.List;

@RestController  //控制器,自动将方法返回值作为 HTTP 响应体
@RequestMapping("/users")  //表示该类中的所有方法都以此路径为前缀。
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUsers(); }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }

    @PostMapping
    public void createUser(@RequestBody User user) { userService.createUser(user); }

    @PutMapping
    public void updateUser(@RequestBody User user) { userService.updateUser(user); }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { userService.deleteUser(id); }
}
