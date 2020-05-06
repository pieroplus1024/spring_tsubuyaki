package com.example.demo.shared.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.shared.domain.model.Users;

@Mapper
public interface UsersMapper {

	public int getUserId(String userName);
	public Users getUserInfo(int userId);
	public int[] getFollowIds(int userId);
	public int[] getFollowerIds(int userId);

}
