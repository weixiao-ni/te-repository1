package com.example.hello_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_springboot.entity.User;
import com.example.hello_springboot.mapper.UserMapper;

import java.util.List;

@Service  //标识一个类是业务逻辑层的Bean
public class UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() { return userMapper.getAllUsers(); }
    public User getUserById(Long id) { return userMapper.getUserById(id); }
    public void createUser(User user) { userMapper.insertUser(user); }
    public void updateUser(User user) { userMapper.updateUser(user); }
    public void deleteUser(Long id) { userMapper.deleteUser(id); }
}
