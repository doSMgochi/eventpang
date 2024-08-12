package com.callor.eventpang.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
	private String user_id;
	private String user_nick;
	private String user_password;
	private String user_email;
	private Date user_birth;
	private String user_tel;
	private String user_gender;
	private String user_role;
}