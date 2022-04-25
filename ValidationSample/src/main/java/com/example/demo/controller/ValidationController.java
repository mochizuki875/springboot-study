package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.CalcForm;
import com.example.demo.validator.CalcValidator;

@Controller
public class ValidationController {
	// インジェクション
	@Autowired
	CalcValidator calcValidator;
	// 相関チェック
	@InitBinder("calcForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(calcValidator);
	}
	
	// 「form-backing bean」の初期化（validationを使うため）
	// このクラスのリクエストハンドラメソッドが呼ばれる前に実行される
	@ModelAttribute
	public CalcForm setUpForm() {
		return new CalcForm(); // ModelにcalcFormとして格納
	}
	
	// 入力画面を表示する
	@GetMapping("show")
	public String showView() {
		return "entry";
	}
	
	// 計算結果を表示する
	@PostMapping("calc")
	public String confirmView(@Validated CalcForm form, BindingResult bindingResult, Model model) {
		// 入力チェックされた場合
		if(bindingResult.hasErrors()) {
			return "entry";
		}
		// 加算実行
		// CalcFormクラスで@Dataを付与しているため各フィールドに対するgetter/setterが使える
		Integer result = form.getLeftNum() + form.getRightNum();
		// Modelに格納
		model.addAttribute("result", result);
		// model.addAttribute("result", Integer.valueOf(result).toString());
		return "confirm";
	}
	
	
	
	
}
