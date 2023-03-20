package com.mini.voDto;

import java.sql.Timestamp;

public class MemberPoinVo {
	private int no;
	private String who;
	private Timestamp when; // date 타입은 sql 과 연동 시킬 때 TimeStamp 가져오면 쓰기 편하다
	private String why;
	private int howmuch;
	public int getNo() {
		return no;
	}
	public String getWho() {
		return who;
	}
	public Timestamp getWhen() {
		return when;
	}
	public String getWhy() {
		return why;
	}
	public int getHowmuch() {
		return howmuch;
	}
	public MemberPoinVo(int no, String who, Timestamp when, String why, int howmuch) {
		super();
		this.no = no;
		this.who = who;
		this.when = when;
		this.why = why;
		this.howmuch = howmuch;
	}
	@Override
	public String toString() {
		return "MemberPoinVo [no=" + no + ", who=" + who + ", when=" + when + ", why=" + why + ", howmuch=" + howmuch
				+ "]";
	}
	
	
	
}
