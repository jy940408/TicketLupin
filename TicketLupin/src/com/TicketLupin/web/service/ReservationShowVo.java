package com.TicketLupin.web.service;

import java.util.Date;

public class ReservationShowVo extends ReservationIdxVo{

	private String rdiscount;
	private String stitle;
	private int num;
	private String stitleimage;
	
	public ReservationShowVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRdiscount() {
		return rdiscount;
	}

	public void setRdiscount(String rdiscount) {
		this.rdiscount = rdiscount;
	}

	
	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStitleimage() {
		return stitleimage;
	}

	public void setStitleimage(String stitleimage) {
		this.stitleimage = stitleimage;
	}
	
	
	
}