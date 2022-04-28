package com.example.quiz;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;

@SpringBootApplication
public class QuizApplication {

	// 起動メソッド
	public static void main(String[] args) {
//		SpringApplication.run(QuizApplication.class, args);
		SpringApplication.run(QuizApplication.class, args).getBean(QuizApplication.class).execute();
	}
	
	// 注入
	// RepositoryをRepositoryImplで実装してDBテーブルを操作するためのインスタンスを作成
	@Autowired
	QuizRepository repository;
	
	// 実行メソッド
	private void execute() {
		// 登録処理
//		setup();
		
		// 全件取得
		// showList();
		
		// 1件取得
		// showOne();
		
		// 更新処理
		//updateQuiz();
		
		// 削除処理
		deleteQuiz();
	}
	
	// クイズを2件登録するメソッド
	private void setup() {
		// エンティティ生成
		Quiz quiz1 = new Quiz(null, "「Spring」はフレームワークですか？", true, "登録太郎");
		// 登録実行
		quiz1 = repository.save(quiz1);
		// 登録確認
		System.out.println("登録したデータは、 " + quiz1 + "です。");
		
		// エンティティ生成
		Quiz quiz2 = new Quiz(null, "「Spring MVC」はバッチ処理ですか？", false, "登録太郎");
		// 登録実行
		quiz2 = repository.save(quiz2);
		// 登録確認
		System.out.println("登録したデータは、 " + quiz2 + "です。");
	}
	
	// 全件取得するメソッド
	private void showList() {
		System.out.println("--- 全件取得開始 ---");
		
		Iterable<Quiz> quizzes = repository.findAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		System.out.println("--- 取得完了 ---");
	}
	
	// 1件取得するためのメソッド
	private void showOne() {
		System.out.println("--- 1件取得開始 ---");
		Optional<Quiz> quizOpt = repository.findById(1);
		// 存在値チェック
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当する問題が存在しませんでした");
		}
	}
	
	// 更新するためのメソッド
	private void updateQuiz() {
		System.out.println("--- 更新処理開始 ---");
		// 更新したいエンティティを作成する
		Quiz quiz = new Quiz(1, "「Spring」はフレームワークですか？", true, "登録タロウ");
		// 更新実行
		quiz = repository.save(quiz);
		// 更新確認
		System.out.println("更新したデータは、 " + quiz + "です。");
		System.out.println("--- 更新処理完了 ---");
		
	}
	
	// 削除するためのメソッド
	private void deleteQuiz() {
		System.out.println("--- 削除処理開始 ---");
		// 削除実行
		repository.deleteById(1);
		System.out.println("--- 削除処理完了 ---");
	}
	

}
