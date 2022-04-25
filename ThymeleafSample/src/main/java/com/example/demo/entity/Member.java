package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

// エンティティ作成
// テーブルの列に当たる

@Data
@AllArgsConstructor // 全フィールドに対する初期化値を引数に取るコンストラクタ
public class Member {
	private Integer id;
	private String name;
}