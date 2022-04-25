package com.example.demo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Member;


@Controller
public class ThymeleafController {
	@GetMapping("show")
	public String showView(Model model) {
		// Memberを作成
		Member member = new Member(1, "会員1");
		Member member1 = new Member(10, "田中");
		Member member2 = new Member(20, "鈴木");
		
		// Listを作成
		List<String> directionList= new ArrayList<>();
		directionList.add("東");
		directionList.add("西");
		directionList.add("南");
		directionList.add("北");
		
		// ListにMemberを格納
		List<Member> memberList = new ArrayList<>();
		memberList.add(member1);
		memberList.add(member2);
		
		// MapにMemberを格納
		Map<String, Member> memberMap = new HashMap<>();
		memberMap.put("tanaka", member1);
		memberMap.put("suzuki", member2);
		
		
		// モデルに値を格納
		model.addAttribute("name", "太郎");
		model.addAttribute("mb", member);
		model.addAttribute("list", directionList);
		model.addAttribute("members", memberList);
		model.addAttribute("map", memberMap);
		
		return "useThymeleaf";
	}
	
	@GetMapping("a")
	public String showA() {
		return "pageA";
	}
	

}
