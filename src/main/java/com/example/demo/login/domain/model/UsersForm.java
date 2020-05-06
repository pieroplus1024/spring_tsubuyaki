package com.example.demo.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UsersForm {

	@NotBlank
	public String userName;

	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9]+$")
	public String password;

	@NotBlank
	@Email
	public String mail;

	public int genderId;
	public int[] hobbyIds;
	public int todofukenId;

	@Min(20)
	@Max(100)
	public int age;

}
