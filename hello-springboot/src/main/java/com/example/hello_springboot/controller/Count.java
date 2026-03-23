package com.example.hello_springboot.controller;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Count {
	@Autowired
	private ApplicationContext context;
    //获取Bean总数
	@GetMapping("/test-count")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String count() {
	    return "Bean总数: " + context.getBeanDefinitionCount();
	}
	@GetMapping("/test-detail")
	public List<String> getDetail() {
	    // 获取所有 Bean 的名称并转化为列表
	    return Arrays.asList(context.getBeanDefinitionNames());
	}
	//查看自定义bean
	@GetMapping("/my-beans")
	public List<String> getMyBeans() {
	    return Arrays.stream(context.getBeanDefinitionNames())
	            .filter(name -> context.getBean(name).getClass().getName().startsWith("com.example"))
	            .collect(Collectors.toList());
	}
}
