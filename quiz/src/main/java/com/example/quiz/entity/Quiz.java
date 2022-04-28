package com.example.quiz.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// quizテーブル用エンティティ
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	@Id
	private Integer id; // 主キー
	private String question; // クイズの内容
	private Boolean answer; // クイズの回答（○/×）
	private String author; // 作成者

}
