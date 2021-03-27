package com.TicketLupin.web.service;

import java.sql.Date;

public class FaqVo {

	private int num;
	private int Fidx;
	private String Ftitle;
	private String Fcontent;
	private int Midx;
	private Date Fregdate;
	private String Fdelyn;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getFidx() {
		return Fidx;
	}
	public void setFidx(int fidx) {
		Fidx = fidx;
	}
	public String getFtitle() {
		return Ftitle;
	}
	public void setFtitle(String ftitle) {
		Ftitle = ftitle;
	}
	public String getFtype() {
		return Ftype;
	}
	public void setFtype(String ftype) {
		Ftype = ftype;
	}
	private String Ftype;
	public String getFcontent() {
		return Fcontent;
	}
	public void setFcontent(String fcontent) {
		Fcontent = fcontent;
	}
	public int getMidx() {
		return Midx;
	}
	public void setMidx(int midx) {
		Midx = midx;
	}
	public Date getFregdate() {
		return Fregdate;
	}
	public void setFregdate(Date fregdate) {
		Fregdate = fregdate;
	}
	public String getFdelyn() {
		return Fdelyn;
	}
	public void setFdelyn(String fdelyn) {
		Fdelyn = fdelyn;
	}
}
