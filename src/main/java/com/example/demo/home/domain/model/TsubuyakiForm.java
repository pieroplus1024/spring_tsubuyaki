package com.example.demo.home.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TsubuyakiForm {

	@NotBlank
	public String tsubuyaki;
}
