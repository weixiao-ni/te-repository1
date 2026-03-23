package com.example.hello_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//RestTemplate方法 同步阻塞
@Service
public class ApiService {
		@Autowired  //依赖注入
		private RestTemplate restTemlate;  
		  public String callExternalApi() {
			  String url="http://localhost:8080";
			  String response =restTemlate.getForObject(url,String.class);
			  return response;
		  }

}
