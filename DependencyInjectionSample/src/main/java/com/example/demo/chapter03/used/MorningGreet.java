package com.example.demo.chapter03.used;

import org.springframework.stereotype.Component;

// Greetの実装クラス
// @Componentアノテーションを付与
@Component
public class MorningGreet implements Greet {
	@Override
	public void greeting() {
		System.out.println("------------------");
		System.out.println("Good Morining");
		System.out.println("------------------");
	}

}
