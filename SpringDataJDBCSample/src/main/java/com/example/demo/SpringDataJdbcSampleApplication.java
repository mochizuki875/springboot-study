package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberCrudRepository;


// SpringBoot起動クラス
@SpringBootApplication
public class SpringDataJdbcSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcSampleApplication.class, args).getBean(SpringDataJdbcSampleApplication.class).execute();
	}
	@Autowired
	MemberCrudRepository repository;
	
	private void execute() {
		// 登録
		executeInsert();
		// 全権取得
		executeSelect();
	}
	
	// 登録
	private void executeInsert() {
		
		Member member = new Member(null,"花子"); // エンティティ作成
		member = repository.save(member); // リポジトリを使って登録
		System.out.println("登録したデータ：" + member);
	}
	
	// 全権取得
	private void executeSelect() {
		System.out.println("---全件取得します---");
		Iterable<Member> members = repository.findAll(); // リポジトリを使用して全件取得
		for (Member member : members) {
			System.out.println(member);
		}
	}

}
