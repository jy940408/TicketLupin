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
	private String rdelyn;
	private String rpick;
	private String rname;
	private String rtel;
	private String remail;
	private String rpaymethod;
	private String rcard;
	private String rquota;
	
	public ReservationVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationVo(int ridx, int sidx, int midx, String rseat, int rprice, String rdiscount, String srdate,
			String srround, Date rregdate, String rdelyn, String rpick, String rname, String rtel, String remail,
			String rpaymethod, String rcard, String rquota) {
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
		this.rdelyn = rdelyn;
		this.rpick = rpick;
		this.rname = rname;
		this.rtel = rtel;
		this.remail = remail;
		this.rpaymethod = rpaymethod;
		this.rcard = rcard;
		this.rquota = rquota;
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

	public String getRdelyn() {
		return rdelyn;
	}

	public void setRdelyn(String rdelyn) {
		this.rdelyn = rdelyn;
	}

	public String getRpick() {
		return rpick;
	}

	public void setRpick(String rpick) {
		this.rpick = rpick;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRtel() {
		return rtel;
	}

	public void setRtel(String rtel) {
		this.rtel = rtel;
	}

	public String getRemail() {
		return remail;
	}

	public void setRemail(String remail) {
		this.remail = remail;
	}

	public String getRpaymethod() {
		return rpaymethod;
	}

	public void setRpaymethod(String rpaymethod) {
		this.rpaymethod = rpaymethod;
	}

	public String getRcard() {
		return rcard;
	}

	public void setRcard(String rcard) {
		this.rcard = rcard;
	}

	public String getRquota() {
		return rquota;
	}

	public void setRquota(String rquota) {
		this.rquota = rquota;
	}

	@Override
	public String toString() {
		return "ReservationVo [ridx=" + ridx + ", sidx=" + sidx + ", midx=" + midx + ", rseat=" + rseat + ", rprice="
				+ rprice + ", rdiscount=" + rdiscount + ", srdate=" + srdate + ", srround=" + srround + ", rregdate="
				+ rregdate + ", rdelyn=" + rdelyn + ", rpick=" + rpick + ", rname=" + rname + ", rtel=" + rtel
				+ ", remail=" + remail + ", rpaymethod=" + rpaymethod + ", rcard=" + rcard + ", rquota=" + rquota + "]";
	}
	
}
