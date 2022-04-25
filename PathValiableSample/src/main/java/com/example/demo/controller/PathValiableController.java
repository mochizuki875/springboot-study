package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PathValiableController {
	@GetMapping("show")
	public String showView() {
		return "show";
	}
	
	// リンク処理
	// リクエストパスに応じたviewを返す
	@GetMapping("/function/{no}")
	public String selectFunction(@PathVariable Integer no) {
		String view = null;
		switch(no) {
		case 1:
			view = "pathvaliable/function1";
			break;
		case 2:
			view = "pathvaliable/function2";
			break;
		case 3:
			view = "pathvaliable/function3";
			break;
		}
		
		return view;
	}
	
	// ボタンA押下処理
	@PostMapping(value="send", params="a")
	public String showAView() {
		return "submit/a";
	}
	
	// ボタンB押下処理
	@PostMapping(value="send", params="b")
	public String showBView() {
		return "submit/b";
	}
	
	// ボタンC押下処理
	@PostMapping(value="send", params="c")
	public String showCView() {
		return "submit/c";
	}
}