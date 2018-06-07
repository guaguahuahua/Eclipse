package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class Person {
	
	@RequestMapping ("/mvc")
	public String find() {
		System.out.println("come here");
		return "hello";
	}
}
