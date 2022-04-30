package com.example.quiz.service;

import java.util.Optional;

import com.example.quiz.entity.Quiz;

// Quizサービス処理：Service
public interface QuizService {
	// インターフェースなので抽象メソッドを定義
	
	// クイズ全件取得
	Iterable<Quiz> selectAll();
	// クイズをIDをキーに1件取得
	Optional<Quiz> selectOneById(Integer id);
	// クイズをランダムで1件取得
	Optional<Quiz> selectOneRandomQuiz();
	// クイズの正誤判定
	Boolean checkQuiz(Integer id, Boolean myAnswer);
	// クイズを登録
	void insertQuiz(Quiz quiz);
	// クイズを更新
	void updateQuiz(Quiz quiz);
	// クイズを削除
	void deleteQuizById(Integer id);
}
