package com.example.demo.home.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import lombok.Data;

@Data
@Scope(value= "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser {
	// テーブル項目
	public int id;
	public String userName;
	public String password;
	public String mail;
	public int genderId;
	public int[] hobbyIds;
	public int todofukenId;
	public int age;
	public String role;
	public boolean enabled;
}
