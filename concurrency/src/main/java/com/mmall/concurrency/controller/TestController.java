package com.mmall.concurrency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author mjf
 *
 */
@RestController
@Slf4j
public class TestController {
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}

}
