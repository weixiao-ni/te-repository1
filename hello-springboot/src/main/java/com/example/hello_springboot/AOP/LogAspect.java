package com.example.hello_springboot.AOP;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    //定义一个 前置通知（Before Advice）；
    @Before("execution(* com.example.demo.service.*.*(..))")
    public void beforeMethod() {
        System.out.println("方法开始执行");
    }

    //定义一个 后置通知（After Advice）；
    @After("execution(* com.example.demo.service.*.*(..))")
    public void afterMethod() {
        System.out.println("方法执行结束");
    }
}