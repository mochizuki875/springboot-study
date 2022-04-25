package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.form.CalcForm;

// Validatorインターフェイスに対する実装クラス
@Component
public class CalcValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 引数で渡されたFormが入力チェックの対象かを論理値で返す
		// ここではCalcFormをチェック対象として設定
		return CalcForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 対象Formを取得する
		CalcForm form = (CalcForm) target;
		// 値が入っているかの判定
		if(form.getLeftNum() != null && form.getRightNum() != null) {
			// (左側入力項目が奇数かつ右側入力項目が偶数でない)
			if(!((form.getLeftNum() % 2 ==1) && (form.getRightNum() %2 == 0))) {
				// エラーの場合は引数にErrorsインターフェイスのrejectメソッドにエラーメッセージのキーを指定する
				// キーはmessages.propertiesで定義
				errors.reject("com.example.demo.validator.CalcValidator.message");
			}
		}

	}

}