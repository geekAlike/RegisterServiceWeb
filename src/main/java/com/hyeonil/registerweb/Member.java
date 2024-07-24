package com.hyeonil.registerweb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

public class Member {
	int idx;
	int userType;
	String name;
	String regId;
	String password;
	int gender;
	String address;
	String created;
	
	Member() {
	}
	
	public void register(int userType, String name, String regId, String password, int gender, String address, String created) {
		this.userType = userType;
		this.name = name;
		this.regId = regId;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.created = created;
		//this.created = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}
}
