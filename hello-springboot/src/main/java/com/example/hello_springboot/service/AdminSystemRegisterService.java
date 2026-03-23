package com.example.hello_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hello_springboot.DTO.UserTblEntity;
import com.example.hello_springboot.mapper.UserTblMapper;
@Service
public class AdminSystemRegisterService {
	@Autowired
	private UserTblMapper userTblMapper;
	@Transactional(rollbackFor = Exception.class) // 日本现场规范：涉及增删改必须加事务
	public Integer insertUserInfo(UserTblEntity userTblEntity){
		// 1. 调用 Mapper 执行插入
        // 注意：MyBatis 的 insert 通常返回受影响的行数
		if(userTblEntity.getPwd()!=null &&userTblEntity.getRepwd()!=null&&userTblEntity.getPwd().equals(userTblEntity.getRepwd())){
			int count = userTblMapper.selectCountByPhone(userTblEntity.getUserTel());
			if(count==0) {
				 int result = userTblMapper.insertUserInfo(userTblEntity);  
			     return result;	
			}
			else return 0;
		}
		else return 0;
	}

}
