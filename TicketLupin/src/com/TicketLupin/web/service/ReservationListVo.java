package com.TicketLupin.web.service;

import java.util.Date;

public class ReservationListVo extends ReservationIdxVo{

	private int pagenum;
	private int num;
	private String stitle;
	
	public ReservationListVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

}