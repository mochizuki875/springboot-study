package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.chapter03.used.Greet;

// このアノテーションによりSpringBootアプリケーションの起動クラスになる
@SpringBootApplication
public class DependencyInjectionSampleApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DependencyInjectionSampleApplication.class, args);
		
		SpringApplication.run(DependencyInjectionSampleApplication.class, args).getBean(DependencyInjectionSampleApplication.class).execute();
		
	}

	// Greetインスタンスの作成
	// 本来ならインターフェイスと実装クラスを指定してnewするところを@Autowiredアノテーションを用いる
	// 実装クラスをしていないので、実装クラスに変更があっても使う側に影響がない
	@Autowired
	Greet greet;
	
	// 実行メソッド
	// mainの中でこのメソッドを実行
	private void execute() {
		greet.greeting();
	}
}
