package com.example.demo.home.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.home.domain.model.Tsubuyaki;
import com.example.demo.home.domain.model.TsubuyakiForm;
import com.example.demo.home.domain.service.HomeService;
import com.example.demo.session.UserSession;
import com.example.demo.shared.domain.model.Users;
import com.example.demo.shared.domain.service.UsersService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	HomeService homeService;

	@Autowired
	UsersService usersService;

	@Autowired
	UserSession userSession;

	private Users user;

	/*******************
	 * ユーザーIDをセッションに格納
	 * @param userName
	 *******************/
	private void setUserId(String userName) {
		userSession.setConnectUserId(usersService.getUserId(userName));
	}

	/*******************
	 * ユーザー情報を格納
	 * @param userName
	 *******************/
	private void setUserInfo(int userId) {
		this.user = usersService.getUserInfo(userId);
	}

	/*******************
	 * 「/home」のGET処理
	 * @param mav
	 * @param principal
	 * @return mav
	 *******************/
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getHome(ModelAndView mav,
					@ModelAttribute TsubuyakiForm tsubuyakiForm,
					Principal principal) {
		System.out.println(userSession);
		if(userSession.getConnectUserId() == 0) {
			String userName = principal.getName();
			// ユーザーIDをセッションに保存
			setUserId(userName);
		}
		// ログインユーザー情報のセット
		setUserInfo(userSession.getConnectUserId());
		// フォローIDの取得
		int[] followIds = usersService.getFollowIds(userSession.getConnectUserId());
		// フォロワーIDの取得
		int[] followerIds = usersService.getFollowerIds(userSession.getConnectUserId());
		// つぶやきリストの取得
		List<Tsubuyaki> tsubuyakiList = homeService.getTsubuyakiList(userSession.getConnectUserId(), followIds);

		mav.addObject("user", this.user);
		mav.addObject("tsubuyakiList", tsubuyakiList);
		mav.addObject("followCount", followIds.length);
		mav.addObject("followerCount", followerIds.length);

		mav.setViewName("/home/home");
		return mav;
	}

	/*********************
	 *
	 * @param mav
	 * @param tsubuyakiForm
	 * @param result
	 * @param principal
	 * @return
	 *********************/
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView postHome(ModelAndView mav,
						@ModelAttribute @Validated TsubuyakiForm tsubuyakiForm,
						BindingResult result,
						Principal principal) {

		// バリデーションチェック
		if(result.hasErrors()) {
			return getHome(mav, tsubuyakiForm, principal);
		}

		Tsubuyaki tsubuyaki = new Tsubuyaki();
		tsubuyaki.setNextId(homeService.getNextId());
		tsubuyaki.setUserId(userSession.getConnectUserId());
		tsubuyaki.setTsubuyaki(tsubuyakiForm.getTsubuyaki());

		// つぶやいた内容の登録処理
		boolean ret = homeService.insertTsubuyaki(tsubuyaki);
		if(ret) {
			mav.setViewName("redirect:/home");
			return mav;
		}else{
			return getHome(mav, tsubuyakiForm, principal);
		}

	}

	/********************
	 *
	 * @param mav
	 * @param id
	 * @return
	 ********************/
	@RequestMapping(value="/delete/{id:.+}", method=RequestMethod.POST)
	public ModelAndView deleteHome(ModelAndView mav,
						@PathVariable("id")int id) {
		boolean ret = homeService.deleteTsubuyaki(id);
		if(ret) {
			mav.setViewName("redirect:/home");
			return mav;
		}else{
			// ログインユーザー情報のセット
			setUserInfo(userSession.getConnectUserId());
			// フォローIDの取得
			int[] followIds = usersService.getFollowIds(userSession.getConnectUserId());
			// フォロワーIDの取得
			int[] followerIds = usersService.getFollowerIds(userSession.getConnectUserId());
			// つぶやきリストの取得
			List<Tsubuyaki> tsubuyakiList = homeService.getTsubuyakiList(userSession.getConnectUserId(), followIds);

			mav.addObject("user", this.user);
			mav.addObject("tsubuyakiList", tsubuyakiList);
			mav.addObject("followCount", followIds.length);
			mav.addObject("followerCount", followerIds.length);

			mav.setViewName("/home/home");
			System.out.println("test");
			return mav;
		}
	}

	@RequestMapping(value="/edit/{id:.+}", method=RequestMethod.POST)
	public ModelAndView updateHome(ModelAndView mav,
						@PathVariable("id")int id,
						@ModelAttribute @Validated TsubuyakiForm tsubuyakiForm,
						BindingResult result,
						Principal principal) {
		System.out.println("通ってる？");
		if(result.hasErrors()) {
			return getHome(mav, tsubuyakiForm, principal);
		}
		Tsubuyaki tsubuyaki = new Tsubuyaki();
		tsubuyaki.setId(id);
		tsubuyaki.setTsubuyaki(tsubuyakiForm.getTsubuyaki());
		boolean ret = homeService.updateTsubuyaki(tsubuyaki);
		if(ret) {
			mav.setViewName("redirect:/home");
			return mav;
		}else{
			return getHome(mav, tsubuyakiForm, principal);
		}
	}


}
