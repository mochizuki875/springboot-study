package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// コントローラーのインスタンスを生成するアノテーション
// "/hello"に対するリクエストを扱う
@Controller
@RequestMapping("hello")
public class HelloViewController {
	// "/hello/view"というリクエストに対するリクエストハンドラメソッド
	@GetMapping("view")
	public String helloView() {
		return "hello";
	}
}
