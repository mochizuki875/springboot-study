package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Memberテーブル：エンティティ
// テーブルの1レコードに該当

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	private Integer id; // 主キーに@Idアノテーションを付与
	private String name;
}
