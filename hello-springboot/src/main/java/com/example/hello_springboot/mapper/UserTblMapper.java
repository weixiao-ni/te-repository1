package com.example.hello_springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.hello_springboot.DTO.UserTblEntity;

@Mapper
public interface UserTblMapper {
	 Integer insertUserInfo(UserTblEntity userTblEntity);
	 Integer selectCountByPhone(String userTel);
}
