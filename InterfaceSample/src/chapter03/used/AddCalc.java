package chapter03.used;

// Calculatorインターフェイスの実装クラス
public class AddCalc implements Calculator {

	@Override
	public Integer calc(Integer x, Integer y) {
		return x+y;
	}

}
