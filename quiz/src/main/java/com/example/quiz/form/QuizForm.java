package com.example.quiz.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizForm {
	private Integer id; // 識別ID
	@NotBlank(message="クイズ内容を入力してください")
	private String question; // クイズの内容
	private Boolean answer; // クイズの解答
	@NotBlank(message="クイズの作成者を入力してください")
	private String author; // クイズの作成者
	private Boolean newQuiz; // 登録/更新判定用フラグ（画面項目の表示有無を制御）
}
