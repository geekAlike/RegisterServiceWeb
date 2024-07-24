package com.kopo.dicegame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Dice {
	int idx;
	int user;
	int com;
	String result;
	String created;
	
	Dice() {
	}
	
	public void roll() {
		Random random = new Random();
		this.user = random.nextInt(6) + 1;
		this.com = random.nextInt(6) + 1;
		if (this.user > this.com) {
			this.result = "WIN";
		} else if (this.user < this.com) {
			this.result = "LOSE";
		} else {
			this.result = "DRAW";
		}
		this.created = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getCom() {
		return com;
	}

	public void setCom(int com) {
		this.com = com;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
}
