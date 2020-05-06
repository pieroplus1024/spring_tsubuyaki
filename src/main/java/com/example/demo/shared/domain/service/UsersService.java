package com.example.demo.shared.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.shared.domain.model.Users;
import com.example.demo.shared.domain.repository.UsersMapper;

@Service
public class UsersService {
	@Autowired
	UsersMapper usersMapper;

	/*******************
	 * ユーザー名からユーザーIDを取得する
	 * @param userName
	 * @return user
	 *******************/
	public int getUserId(String userName) {
		int userId = usersMapper.getUserId(userName);
		return userId;
	}

	public Users getUserInfo(int userId) {
		Users user = usersMapper.getUserInfo(userId);
		return user;
	}


	/********************
	 * ユーザーIDからフォローしている人のIDを取得する
	 * @param userId
	 * @return followIds
	 ********************/
	public int[] getFollowIds(int userId) {
		int[] followIds = usersMapper.getFollowIds(userId);
		return followIds;
	}

	/********************
	 * ユーザーIDからフォロワーのIDを取得する
	 * @param userId
	 * @return followerIds
	 ********************/
	public int[] getFollowerIds(int userId) {
		int[] followerIds = usersMapper.getFollowerIds(userId);
		return followerIds;
	}

}
