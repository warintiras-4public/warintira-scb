package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ApiController {

	@Autowired
	private ApiService apiService;

	@RequestMapping("/hello")
	public String hello() {
		return apiService.hello();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/encode")
	public String encode(@RequestBody String linklist) {
		return apiService.encode(linklist);
	}
}
