package com.example.demo.login.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.shared.domain.model.Users;

@Mapper
public interface LoginMapper {

	public boolean insertUsers(Users user);
	public int getNextId(String tableName);
	public int chkNameDuplication(String name);
	public int chkMailDuplication(String mail);

}
