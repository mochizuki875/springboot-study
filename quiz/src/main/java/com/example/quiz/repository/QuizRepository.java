package com.example.quiz.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.Quiz;

// CrudRepositoryの拡張インターフェースとしてRepositoryを作成
public interface QuizRepository extends CrudRepository<Quiz, Integer> {

}
