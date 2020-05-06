package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.repository.LoginMapper;
import com.example.demo.shared.domain.model.Users;

@Service
public class SignupService {

	@Autowired
	LoginMapper userMapper;

	public boolean insertUsers(Users user) {
		user.setNextId(getNextId("m_user"));
		return userMapper.insertUsers(user);
	}

	public int getNextId(String tableName) {
		return userMapper.getNextId(tableName);
	}

	public boolean chkNameDuplication(String name) {
		int count = userMapper.chkNameDuplication(name);
		if(count != 0) {
			return false;
		}
		return true;
	}

	public boolean chkMailDuplication(String mail) {
		int count = userMapper.chkMailDuplication(mail);
		if(count != 0) {
			return false;
		}
		return true;
	}

}
