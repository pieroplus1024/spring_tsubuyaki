package com.example.demo.login.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.login.domain.model.UsersForm;
import com.example.demo.login.domain.service.SignupService;
import com.example.demo.shared.domain.model.Users;

@Controller
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	SignupService signupService;

	// 性別
	final static Map<String, String> RADIOGENDER =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>(){
				{
					put("男性", "1");
					put("女性", "2");
				}
			});

	// 趣味
	final static Map<String, String> CHECKHOBBY =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>(){
				{
					put("料理", "1");
					put("運動", "2");
					put("読書", "3");
					put("映画", "4");
					put("ドライブ", "5");
					put("資格", "6");
					put("音楽", "7");
					put("その他", "8");
				}
			});

	// 都道府県
	final static Map<String, String> SELECTTODOFUKEN =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>(){
				{
					put("北海道", "0");
					put("東北", "1");
					put("北陸", "2");
					put("関東", "3");
					put("中部", "4");
					put("近畿", "5");
					put("中国", "6");
					put("四国", "7");
					put("九州", "8");
				}
			});

	/***********************
	 *「"/signup"」のGet処理
	 * @param usersForm
	 * @param mav
	 * @return mav
	 ***********************/
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getSignup(@ModelAttribute UsersForm usersForm,
						ModelAndView mav) {
		mav.addObject("radioGender", RADIOGENDER);
		mav.addObject("checkHobby", CHECKHOBBY);
		mav.addObject("selectTodofuken", SELECTTODOFUKEN);
		mav.setViewName("/login/signup");
		return mav;
	}

	/**************************
	 * 「"/signup"」のPOST処理
	 * 新規登録画面より入力されたユーザー情報を登録する
	 * @param mav
	 * @param usersForm
	 * @param redirectAttributes
	 * @return mav
	 **************************/
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView mav(ModelAndView mav,
						@ModelAttribute @Validated UsersForm usersForm,
						BindingResult result,
						RedirectAttributes redirectAttributes) {

		// バリデーションチェック
		if(result.hasErrors()) {
			mav.addObject("radioGender", RADIOGENDER);
			mav.addObject("checkHobby", CHECKHOBBY);
			mav.addObject("selectTodofuken", SELECTTODOFUKEN);
			mav.setViewName("/login/signup");
			return mav;
		}

		// 入力情報の格納
		Users user = new Users();
		user.setUserName(usersForm.getUserName());
		user.setPassword(usersForm.getPassword());
		user.setMail(usersForm.getMail());
		user.setGenderId(usersForm.getGenderId());
		user.setHobbyIds(usersForm.getHobbyIds());
		user.setTodofukenId(usersForm.getTodofukenId());
		user.setAge(usersForm.getAge());
		user.setRole("ROLE_GENERAL");
		user.setEnabled(true);

		// エラーチェック
		if(!errCheck(mav, user.getUserName(), user.getMail())) {
			mav.addObject("radioGender", RADIOGENDER);
			mav.addObject("checkHobby", CHECKHOBBY);
			mav.addObject("selectTodofuken", SELECTTODOFUKEN);
			mav.setViewName("/login/signup");
			return mav;
		}

		// ユーザー登録処理
		boolean ret = signupService.insertUsers(user);

		if (ret) {
			redirectAttributes.addFlashAttribute("message", "ユーザー登録完了");
			mav.setViewName("redirect:/login");
			return mav;
		} else {
			mav.addObject("radioGender", RADIOGENDER);
			mav.addObject("checkHobby", CHECKHOBBY);
			mav.addObject("selectTodofuken", SELECTTODOFUKEN);
			mav.addObject("errMessage", "ユーザー登録に失敗しました。");
			mav.setViewName("/login/signup");
			return mav;
		}
	}

	/*********************
	 * エラーチェック
	 * @param mav
	 * @param inputName
	 * @param inputMail
	 * @return ret
	 *********************/
	private boolean errCheck(ModelAndView mav, String inputName, String inputMail) {
		boolean ret = true;
		if(!signupService.chkNameDuplication(inputName)) {
			mav.addObject("userNameErrMsg", "「" + inputName + "」はすでに登録済みです");
			ret = false;
		}
		if(!signupService.chkMailDuplication(inputMail)) {
			mav.addObject("mailErrMsg", "「" + inputMail + "」はすでに登録済みです");
			ret = false;
		}
		return ret;
	}

}
