package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;

@SpringBootApplication
public class QuizApplication {

	// 起動メソッド
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
//		SpringApplication.run(QuizApplication.class, args).getBean(QuizApplication.class).execute();
	}
	
	// 注入
	
	@Autowired
//	QuizRepository repository;  // RepositoryをRepositoryImplで実装してDBテーブルを操作するためのインスタンスを作成
	QuizService service;  // Serviceインスタンスの作成（IF:QuizService, 実装クラス：QuizServiceImpl）
	
	// 実行メソッド
	private void execute() {
		// 登録処理
//		setup();
		
		// 全件取得
//		showList();
		
		// 1件取得
//		showOne();
		
		// 更新処理
		updateQuiz();
		
		// 削除処理
		deleteQuiz();
		
		// クイズ実行
		doQuiz();
	}
	
	// クイズを5件登録するメソッド
	private void setup() {
		System.out.println("--- 登録開始 ---");
		// エンティティ生成
		Quiz quiz1 = new Quiz(null, "「Java」はオブジェクト指向言語である", true, "登録太郎");
		Quiz quiz2 = new Quiz(null, "「Spring Data」はデータアクセス機能を提供する", true, "登録太郎");
		Quiz quiz3 = new Quiz(null, "プログラムの配置されたサーバーのことをライブラリと呼ぶ", false, "登録太郎");
		Quiz quiz4 = new Quiz(null, "「@Component」はインスタンス生成アノテーションである", true, "登録太郎");
		Quiz quiz5 = new Quiz(null, "「Spring MVC」で全てのリクエストを1つのコントローラーで受け取るのは「シングルコントローラーパターン」である", false, "登録太郎");
		
		// リクエストにエンティティを格納
		List<Quiz> quizList = new ArrayList<>(); 
		Collections.addAll(quizList, quiz1, quiz2, quiz3, quiz4, quiz5);
		for(Quiz quiz : quizList) {
			// 登録実行
			service.insertQuiz(quiz);
		}
		System.out.println("--- 登録完了 ---");
		
		
//		// エンティティ生成
//		Quiz quiz1 = new Quiz(null, "「Spring」はフレームワークですか？", true, "登録太郎");
//		// 登録実行
//		quiz1 = repository.save(quiz1);
//		// 登録確認
//		System.out.println("登録したデータは、 " + quiz1 + "です。");
//		
//		// エンティティ生成
//		Quiz quiz2 = new Quiz(null, "「Spring MVC」はバッチ処理ですか？", false, "登録太郎");
//		// 登録実行
//		quiz2 = repository.save(quiz2);
//		// 登録確認
//		System.out.println("登録したデータは、 " + quiz2 + "です。");
	}
	
	// 全件取得するメソッド
	private void showList() {
		System.out.println("--- 全件取得開始 ---");
		// リポジトリを使用して全件取得
		Iterable<Quiz> quizzes = service.selectAll();
		for(Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		
//		Iterable<Quiz> quizzes = repository.findAll();
//		for (Quiz quiz : quizzes) {
//			System.out.println(quiz);
//		}
		System.out.println("--- 全権取得完了 ---");
	}
	
	// 1件取得するためのメソッド
	private void showOne() {
		System.out.println("--- 1件取得開始 ---");
		Optional<Quiz> quizOpt = service.selectOneById(1);
		if(quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当する問題が存在しませんでした");
		}
		System.out.println("--- 1件取得完了 ---");
//		Optional<Quiz> quizOpt = repository.findById(1);
//		// 存在値チェック
//		if (quizOpt.isPresent()) {
//			System.out.println(quizOpt.get());
//		} else {
//			System.out.println("該当する問題が存在しませんでした");
//		}
	}
	
	// 更新するためのメソッド
	private void updateQuiz() {
		System.out.println("--- 更新処理開始 ---");
		Quiz quiz = new Quiz(1, "「スプリング」はフレームワークである", true, "登録タロウ");
		service.updateQuiz(quiz);
//		// 更新したいエンティティを作成する
//		Quiz quiz = new Quiz(1, "「Spring」はフレームワークですか？", true, "登録タロウ");
//		// 更新実行
//		quiz = repository.save(quiz);
//		// 更新確認
//		System.out.println("更新したデータは、 " + quiz + "です。");
		System.out.println("--- 更新処理完了 ---");
		
	}
	
	// 削除するためのメソッド
	private void deleteQuiz() {
		System.out.println("--- 削除処理開始 ---");
		service.deleteQuizById(2);
		// 削除実行
//		repository.deleteById(1);
		System.out.println("--- 削除処理完了 ---");
	}
	
	// ランダムでクイズを取得してクイズの正解/不正解を判定する
	private void doQuiz() {
		System.out.println("--- クイズをランダムで1件取得開始 ---");
		Optional<Quiz> quizOpt = service.selectOneRandomQuiz();
		if(quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("--- クイズを取得できませんでした ---");
		}
		System.out.println("--- クイズをランダムで1件取得完了 ---");
		
		// 回答実施
		Boolean myAnswer = false; // クイズに対する回答
		Integer id = quizOpt.get().getId();
		if(service.checkQuiz(id, myAnswer)) {
			System.out.println("正解");
		} else {
			System.out.println("不正解");
		}
	}

}
