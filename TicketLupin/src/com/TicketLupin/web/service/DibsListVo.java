package com.TicketLupin.web.service;

import java.sql.Date;

public class DibsListVo {
	private int num;
	private int sidx;
	private String stitle;
	private Date sopendate;
	private Date senddate;
	private int midx;
	
	public DibsListVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DibsListVo(int num, int sidx, String stitle, Date sopendate, Date senddate, int midx) {
		super();
		this.num = num;
		this.sidx = sidx;
		this.stitle = stitle;
		this.sopendate = sopendate;
		this.senddate = senddate;
		this.midx = midx;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public Date getSopendate() {
		return sopendate;
	}

	public void setSopendate(Date sopendate) {
		this.sopendate = sopendate;
	}

	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	@Override
	public String toString() {
		return "DibsListVo [num=" + num + ", sidx=" + sidx + ", stitle=" + stitle + ", sopendate=" + sopendate
				+ ", senddate=" + senddate + ", midx=" + midx + "]";
	}
	
	
}
