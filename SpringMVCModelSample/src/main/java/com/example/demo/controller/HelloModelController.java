package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloModelController {
	@GetMapping("model")
	public String helloView(Model model) { // 引数にModel型を渡す
		model.addAttribute("msg", "イベントハンドラメソッド（helloView）内でModelに設定した文字列");
		return "helloThymeleaf";
		
	}
}
