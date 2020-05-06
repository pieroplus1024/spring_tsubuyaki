package com.example.demo.home.domain.model;

import lombok.Data;

@Data
public class Tsubuyaki {
	public int id;				// つぶやきID
	public String tsubuyaki;	// つぶやき
	public int userId;			// つぶやいたユーザーのID
	public String userName;		// つぶやいたユーザー
	public String updateDate;	// 更新日時

	public int nextId;			// 採番後のID

}
