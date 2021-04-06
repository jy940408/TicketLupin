package com.TicketLupin.web.service;

import java.sql.Date;

public class ShowRoundVo {

	private int sridx;
	private int sidx;
	private String srdate;
	private String srround1;
	private String srround2;
	private String srround3;
	private String srround4;
	
	public ShowRoundVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowRoundVo(int sridx, int sidx, String srdate, String srround1, String srround2, String srround3,
			String srround4) {
		super();
		this.sridx = sridx;
		this.sidx = sidx;
		this.srdate = srdate;
		this.srround1 = srround1;
		this.srround2 = srround2;
		this.srround3 = srround3;
		this.srround4 = srround4;
	}

	public int getSridx() {
		return sridx;
	}

	public void setSridx(int sridx) {
		this.sridx = sridx;
	}

	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public String getSrdate() {
		return srdate;
	}

	public void setSrdate(String srdate) {
		this.srdate = srdate;
	}

	public String getSrround1() {
		return srround1;
	}

	public void setSrround1(String srround1) {
		this.srround1 = srround1;
	}

	public String getSrround2() {
		return srround2;
	}

	public void setSrround2(String srround2) {
		this.srround2 = srround2;
	}

	public String getSrround3() {
		return srround3;
	}

	public void setSrround3(String srround3) {
		this.srround3 = srround3;
	}

	public String getSrround4() {
		return srround4;
	}

	public void setSrround4(String srround4) {
		this.srround4 = srround4;
	}

	@Override
	public String toString() {
		return "ShowRoundDao [sridx=" + sridx + ", sidx=" + sidx + ", srdate=" + srdate + ", srround1=" + srround1
				+ ", srround2=" + srround2 + ", srround3=" + srround3 + ", srround4=" + srround4 + "]";
	}
	
	
	
}
