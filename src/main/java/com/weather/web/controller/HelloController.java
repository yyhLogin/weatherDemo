package com.weather.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yyh
 * @date 2018/3/8 11:21
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello(){
		return "hello world!";
	}
}
