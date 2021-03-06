package com.TicketLupin.web.service;

public class DibsVo {

	private int didx;
	private int sidx;
	private int midx;
	
	public DibsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DibsVo(int didx, int sidx, int midx) {
		super();
		this.didx = didx;
		this.sidx = sidx;
		this.midx = midx;
	}

	public int getDidx() {
		return didx;
	}

	public void setDidx(int didx) {
		this.didx = didx;
	}

	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	@Override
	public String toString() {
		return "DibsVo [didx=" + didx + ", sidx=" + sidx + ", midx=" + midx + "]";
	}
	
	
	
}
