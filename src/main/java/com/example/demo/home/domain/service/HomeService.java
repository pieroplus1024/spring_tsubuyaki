package com.example.demo.home.domain.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.home.domain.model.Tsubuyaki;
import com.example.demo.home.domain.repository.HomeMapper;

@Service
public class HomeService {

	@Autowired
	HomeMapper homeMapper;

	/********************
	 * 自分及びフォローしているユーザーのつぶやきを取得する
	 * @param userId
	 * @param followIds
	 * @return tsubuyakiList
	 ********************/
	public List<Tsubuyaki> getTsubuyakiList(int userId, int[] followIds){
		Map<String, Object> idsInfo = new LinkedHashMap<String, Object>() {
			{
				put("userId", userId);
				put("followIds", followIds);
			}
		};
		List<Tsubuyaki> tsubuyakiList = homeMapper.getTsubuyakiList(idsInfo);
		return tsubuyakiList;
	}

	/********************
	 * つぶやきIDの採番
	 * @return nextId
	 ********************/
	public int getNextId() {
		int nextId = homeMapper.getNextId();
		return nextId;
	}

	/*******************
	 * つぶやきの登録
	 * @param tsubuyaki
	 * @return
	 *******************/
	public boolean insertTsubuyaki(Tsubuyaki tsubuyaki) {
		return homeMapper.insertTsubuyaki(tsubuyaki);
	}

	/*******************
	 * つぶやきの削除
	 * @param tsubuyaki
	 * @return
	 *******************/
	public boolean deleteTsubuyaki(int tsubuyakiId) {
		return homeMapper.deleteTsubuyaki(tsubuyakiId);
	}

	/*******************
	 * つぶやきの更新
	 * @param tsubuyaki
	 * @return
	 *******************/
	public boolean updateTsubuyaki(Tsubuyaki tsubuyaki) {
		return homeMapper.updateTsubuyaki(tsubuyaki);
	}
}
