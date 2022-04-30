package com.example.quiz.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.Quiz;

// CrudRepositoryの拡張インターフェースとしてRepositoryを作成
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
	// 独自で使用したいメソッドを定義
	@Query("SELECT id FROM quiz ORDER BY RANDOM() limit 1")
	Integer getRandomId();
}
