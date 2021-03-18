package com.TicketLupin.web.service;

import java.util.Date;

public class ReservationVo {

	private int ridx;
	private int sidx;
	private int midx;
	private String rseat;
	private int rprice;
	private String rdiscount;
	private String srdate;
	private String srround;
	private Date rregdate;
	
	public ReservationVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationVo(int ridx, int sidx, int midx, String rseat, int rprice, String rdiscount, String srdate,
			String srround, Date rregdate) {
		super();
		this.ridx = ridx;
		this.sidx = sidx;
		this.midx = midx;
		this.rseat = rseat;
		this.rprice = rprice;
		this.rdiscount = rdiscount;
		this.srdate = srdate;
		this.srround = srround;
		this.rregdate = rregdate;
	}

	public int getRidx() {
		return ridx;
	}

	public void setRidx(int ridx) {
		this.ridx = ridx;
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

	public String getRseat() {
		return rseat;
	}

	public void setRseat(String rseat) {
		this.rseat = rseat;
	}

	public int getRprice() {
		return rprice;
	}

	public void setRprice(int rprice) {
		this.rprice = rprice;
	}

	public String getRdiscount() {
		return rdiscount;
	}

	public void setRdiscount(String rdiscount) {
		this.rdiscount = rdiscount;
	}

	public String getSrdate() {
		return srdate;
	}

	public void setSrdate(String srdate) {
		this.srdate = srdate;
	}

	public String getSrround() {
		return srround;
	}

	public void setSrround(String srround) {
		this.srround = srround;
	}

	public Date getRregdate() {
		return rregdate;
	}

	public void setRregdate(Date rregdate) {
		this.rregdate = rregdate;
	}

	@Override
	public String toString() {
		return "ReservationVo [ridx=" + ridx + ", sidx=" + sidx + ", midx=" + midx + ", rseat=" + rseat + ", rprice="
				+ rprice + ", rdiscount=" + rdiscount + ", srdate=" + srdate + ", srround=" + srround + ", rregdate="
				+ rregdate + "]";
	}

	
}
