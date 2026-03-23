package com.example.hello_springboot.mapper;

import org.apache.ibatis.annotations.*;

import com.example.hello_springboot.entity.User;

import java.util.List;
@Mapper 
public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	User getUserById(@Param("id") Long id);

	@Select("SELECT * FROM users")
	List<User> getAllUsers();

	@Insert("INSERT INTO users(name, email) VALUES(#{name}, #{email})")
	void insertUser(User user);

	@Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
	void updateUser(User user);

	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteUser(@Param("id") Long id);
}
