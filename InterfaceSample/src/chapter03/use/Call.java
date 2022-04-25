package chapter03.use;

import chapter03.used.Calculator;
import chapter03.used.SubCalc;

public class Call {
	public static void main(String[] args) {
		// インターフェイスと実装クラスを指定してインスタンスを作成
//		Calculator calculator = new AddCalc(); // 実装クラスにAddCalc()を指定
		Calculator calculator = new SubCalc(); // 実装クラスにSubCalc()を指定
		
		// 作成したインスタンスからメソッドを呼び出し
		Integer result = calculator.calc(10,5);
		
		// 結果出力
		System.out.println("calculator.calc(10,5)="+ result);

	}

}
