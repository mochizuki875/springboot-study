package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Member; // Member（エンティティ）をインポート

// Memberテーブル：リポジトリ
// CrudRepositoryクラスを継承することでメソッドを定義しなくても継承元のメソッドを利用できる
public interface MemberCrudRepository extends CrudRepository<Member, Integer> {

}
