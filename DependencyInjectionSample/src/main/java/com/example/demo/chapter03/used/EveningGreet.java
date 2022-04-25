package com.example.demo.chapter03.used;

//Greetの実装クラス
//@Componentアノテーションを付与
// @Component
public class EveningGreet implements Greet {
	@Override
	public void greeting() {
		System.out.println("------------------");
		System.out.println("Good Evening");
		System.out.println("------------------");

	}

}
