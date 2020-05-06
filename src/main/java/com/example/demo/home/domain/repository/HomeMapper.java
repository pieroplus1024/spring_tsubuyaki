package com.example.demo.home.domain.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.home.domain.model.Tsubuyaki;

@Mapper
public interface HomeMapper {

	public List<Tsubuyaki> getTsubuyakiList(Map<String, Object> idsInfo);
	public boolean insertTsubuyaki(Tsubuyaki tsubuyaki);
	public boolean deleteTsubuyaki(int tsubuyakiId);
	public boolean updateTsubuyaki(Tsubuyaki tsubuyaki);
	public int getNextId();

}
