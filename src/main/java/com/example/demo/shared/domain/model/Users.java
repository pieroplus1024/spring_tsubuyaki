package com.example.demo.shared.domain.model;

import lombok.Data;

@Data
public class Users {

	// テーブル項目
	public int id;				// ID
	public String userName;		// ユーザー名
	public String password;		// パスワード
	public String mail;			// メール
	public int genderId;		// 性別ID
	public String genderName;	// 性別名
	public int[] hobbyIds;		// 趣味ID
	public int todofukenId;		// 都道府県ID
	public String todofukenName;// 都道府県名
	public int age;				// 年齢
	public String role;			// 権限
	public boolean enabled;		// 使用可否

	// テーブル項目以外
	public int nextId;		// 採番ID

}
