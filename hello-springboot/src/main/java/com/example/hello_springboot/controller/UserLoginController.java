package com.example.hello_springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello_springboot.DTO.UserTblEntity;
import com.example.hello_springboot.VO.CommonResult;
import com.example.hello_springboot.enums.ResultCode;
import com.example.hello_springboot.service.AdminSystemRegisterService;

import jakarta.validation.Valid;
@RestController   //AOP
@RequestMapping("/adminList")  
public class UserLoginController {
	@Autowired
	private AdminSystemRegisterService userService; 
	@PostMapping("/auserSystemInsert")
    public CommonResult<String> login(@Valid @RequestBody UserTblEntity userTblEntity,BindingResult result) {
		System.out.println("收到的实体数据：" + userTblEntity.toString());
		if(result.hasErrors()) {
			System.out.println("校验失败详情：" + result.getFieldError().getDefaultMessage());
			return CommonResult.failed(ResultCode.UNAUTHORIZED);
		}
		
		try {
		    int i=userService.insertUserInfo(userTblEntity);
		    if(i==0) {
		    	 System.out.println("数据库插入失败！插入了" +i+"行");
		    	 return CommonResult.failed(ResultCode.UNAUTHORIZED,"用户已存在");
		    }
		    System.out.println("数据库插入成功！插入了" +i+"行");
		    return CommonResult.success(ResultCode.SUCCESS);
		} catch (Exception e) {
		    System.err.println("数据库操作失败，原因：");
		    e.printStackTrace(); // 这行能让你在控制台看到具体的 ORA-错误码
		    return CommonResult.failed(ResultCode.SYSTEM_ERROR);
		}
		
	}
}
