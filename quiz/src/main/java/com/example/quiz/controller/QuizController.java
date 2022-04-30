package com.example.quiz.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService service; // serviceをDIしてインスタンス作成
	
	// form-backing beanの初期化（リクエストハンドラメソッド実行時に呼ばれる）
	@ModelAttribute
	public QuizForm setUpForm() {
		QuizForm form = new QuizForm();
		// ラジオボタンデフォルト値設定
		form.setAnswer(true);
		return form;
	}
	
	// Quizの一覧を表示
	@GetMapping
	public String showList(QuizForm quizForm, Model model) {
		quizForm.setNewQuiz(true); // 初期値設定
		Iterable<Quiz> list = service.selectAll(); // クイズを全件取得
		model.addAttribute("list", list); // Modelに格納
		model.addAttribute("title", "登録用フォーム"); // Modelに格納
		
		return "crud"; // CRUDというViewを返す
	}
	
	// Quizデータを登録
	@PostMapping("/insert")
	public String insert(@Validated QuizForm quizForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		// FormからEntityへの詰め替え
		Quiz quiz = new Quiz();
		quiz.setQuestion(quizForm.getQuestion());
		quiz.setAnswer(quizForm.getAnswer());
		quiz.setAuthor(quizForm.getAuthor());
		
		// 入力チェック
		if(!bindingResult.hasErrors()) {
			service.insertQuiz(quiz);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/quiz";
		} else {
			// エラーがある場合
			return showList(quizForm, model); // Viewではなく一覧表示処理を呼び出す
		}
	}
	
	// Quizデータ1件取得しフォームに表示する
	@GetMapping("/{id}")
	public String showUpdate(QuizForm quizForm, @PathVariable Integer id, Model model) {
		// Quizを取得
		Optional<Quiz> quizOptional = service.selectOneById(id);
		// QuizFormへ詰め直し
		Optional<QuizForm> quizFormOpt = quizOptional.map(t -> makeQuizForm(t));
		// QuizFormがnullでなければ中身を取り出す
		if(quizFormOpt.isPresent()) {
			quizForm =quizFormOpt.get();
		}
		// 更新用のModelを作成する
		makeUpdateModel(quizForm, model);
		return "crud";
	}
	
	// idをKeyにしてデータを更新する
	@PostMapping("/update")
	public String update(@Validated QuizForm quizForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		// QuizFormからQuizに詰め直す
		Quiz quiz = makeQuiz(quizForm);
		// 入力チェック
		if(!result.hasErrors()) {
			// 更新処理、フラッシュスコープの使用、リダイレクト
			service.updateQuiz(quiz);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
			return "redirect:/quiz/" + quiz.getId();
		} else { // Validationエラーがある場合
			// 更新用Modelを作成する
			makeUpdateModel(quizForm, model);
			return "crud";
		}
	}
	
	// idをkeyにしてデータを削除する
	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		// Quizを1件削除
		service.deleteQuizById(Integer.parseInt(id));
		// リダイレクト
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/quiz";
		
	}
	
	// Quizデータをランダムに取得して表示
	@GetMapping("/play")
	public String showQuiz(QuizForm quizForm, Model model) {
		// Quizを取得
		Optional<Quiz> quizOpt = service.selectOneRandomQuiz();
		// 値が入っているかをチェック
		if(quizOpt.isPresent()) {
			// QuizFormへ詰め直し
			Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
			quizForm = quizFormOpt.get();
		} else {
			model.addAttribute("msg", "問題がありません");
			return "play";
		}
		// 表示用モデルへ格納
		model.addAttribute("quizForm", quizForm);
		return "play";
	}
	
	// クイズ判定
	@PostMapping("/check")
	public String checkQuiz(QuizForm quizForm, @RequestParam Boolean answer, Model model) {
		if(service.checkQuiz(quizForm.getId(), answer)) {
			model.addAttribute("msg", "正解");
		} else {
			model.addAttribute("msg", "不正解");
		}
		return "answer";
	}
	
	// -----------------内部向けメソッド------------------------------------------------
	// 更新用Modelを作成する
	private void makeUpdateModel(QuizForm quizForm, Model model) {
		model.addAttribute("id", quizForm.getId());
		quizForm.setNewQuiz(false);
		model.addAttribute("quizForm", quizForm);
		model.addAttribute("title", "更新用フォーム");
	}
	
	// QuizFormをQuizに変換
	private Quiz makeQuiz(QuizForm quizForm) {
		Quiz quiz = new Quiz();
		quiz.setId(quizForm.getId());
		quiz.setQuestion(quizForm.getQuestion());
		quiz.setAnswer(quizForm.getAnswer());
		quiz.setAuthor(quizForm.getAuthor());
		return quiz;
	}
	
	// QuizをQuizFormに変換
	private QuizForm makeQuizForm(Quiz quiz) {
		QuizForm form = new QuizForm();
		form.setId(quiz.getId());
		form.setQuestion(quiz.getQuestion());
		form.setAnswer(quiz.getAnswer());
		form.setAuthor(quiz.getAuthor());
		form.setNewQuiz(false);
		return form;
	}
}
