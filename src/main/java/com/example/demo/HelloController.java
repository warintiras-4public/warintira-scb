package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@Autowired
	private HelloService helloService;

	@RequestMapping("/")
	public String hello() {
		return helloService.hello();
	}

}
