package com.example.demo.chapter03.aop;

import java.text.SimpleDateFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {
	// [Before]
	// Greet型インスタンスのメソッド実行直前をJoinPointに指定
	// executionの中でポイントカットを定義
//	@Before("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
//	public void beforeAdvice(JoinPoint joinPoint) {
//		
//		System.out.println("=====Before Advice=====");
//		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date())); // 日付を表示
//		System.out.println(String.format("メソッド:%s", joinPoint.getSignature().getName())); // Targetのメソッド名を表示
//	}
	
	// [After]
	// Greet型インスタンスのメソッド実行直後をJoinPointに指定
	// executionの中でポイントカットを定義
//	@After("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
//	public void afterAdvice(JoinPoint joinPoint) {
//		
//		System.out.println("=====After Advice=====");
//		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date())); // 日付を表示
//		System.out.println(String.format("メソッド:%s", joinPoint.getSignature().getName())); // Targetのメソッド名を表示
//	}
	// [Around]
	// Greet型インスタンスのメソッド実行前後をJoinPointに指定
	// executionの中でポイントカットを定義
	@Around("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		
		System.out.println("=====Around Advice=====");
		System.out.println("▼▼▼ 処理前 ▼▼▼");
		
		Object result = joinPoint.proceed(); // 指定したクラスの指定したメソッドを実行
		
		System.out.println("▲▲▲ 処理後 ▲▲▲");
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date())); // 日付を表示
		System.out.println(String.format("メソッド:%s", joinPoint.getSignature().getName())); // Targetのメソッド名を表示
		
		return result; // Object型で戻り値を返す
	}
}
