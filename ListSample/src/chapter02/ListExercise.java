package chapter02;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {

	public static void main(String[] args) {
		// String型を格納するListを用意する
        // ジェネリクス
        List<String> names = new ArrayList<>();

        // String型データを格納する
        names.add("太郎");
        names.add("二郎");
        names.add("Saburo");

        // 拡張forで出力
        for (String name : names) {
            System.out.println(name);
        }

	}

}
