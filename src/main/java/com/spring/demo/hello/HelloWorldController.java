package com.spring.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

	@GetMapping("/hello")
	public String helloWorld() {
		return "hello, World";
	}
	
	@GetMapping("/hello-bean")
	public UserDetaiuls helloBean() {
		return new UserDetaiuls("k", "l",
				"BBSR");
	}
	
}
