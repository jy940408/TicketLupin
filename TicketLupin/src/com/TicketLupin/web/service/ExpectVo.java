package com.TicketLupin.web.service;

import java.sql.Date;

public class ExpectVo {

	private int c_idx;
	private int sidx;
	private int midx;
	private String c_content;
	private Date c_regdate;
	private int origin_good;
	private int origin_bad;
	private int good;
	private int bad;
	private String c_delyn;
	private String mid;
	private int cnt;
	private int c_depth;
	private int origin_c_idx;
	
	
	public ExpectVo(int c_idx, int sidx, int midx, String c_content, Date c_regdate, int origin_good, int origin_bad,
			String c_delyn, String mid, int cnt, int c_depth,int good, int bad, int origin_c_idx) {
		this.c_idx = c_idx;
		this.sidx = sidx;
		this.midx = midx;
		this.c_content = c_content;
		this.c_regdate = c_regdate; 
		this.origin_good = origin_good;
		this.origin_bad = origin_bad;
		this.c_delyn = c_delyn;
		this.mid = mid;
		this.cnt= cnt;
		this.c_depth = c_depth;
		this.good = good;
		this.bad = bad;
		this.origin_c_idx =origin_c_idx;
	}


	public int getC_idx() {
		return c_idx;
	}


	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
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


	public String getC_content() {
		return c_content;
	}


	public void setC_content(String c_content) {
		this.c_content = c_content;
	}


	public Date getC_regdate() {
		return c_regdate;
	}


	public void setC_regdate(Date c_regdate) {
		this.c_regdate = c_regdate;
	}


	public int getOrigin_good() {
		return origin_good;
	}


	public void setOrigin_good(int origin_good) {
		this.origin_good = origin_good;
	}


	public int getOrigin_bad() {
		return origin_bad;
	}


	public void setOrigin_bad(int origin_bad) {
		this.origin_bad = origin_bad;
	}


	public int getGood() {
		return good;
	}


	public void setGood(int good) {
		this.good = good;
	}


	public int getBad() {
		return bad;
	}


	public void setBad(int bad) {
		this.bad = bad;
	}


	public String getC_delyn() {
		return c_delyn;
	}


	public void setC_delyn(String c_delyn) {
		this.c_delyn = c_delyn;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public int getCnt() {
		return cnt;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	public int getC_depth() {
		return c_depth;
	}


	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}


	public int getOrigin_c_idx() {
		return origin_c_idx;
	}


	public void setOrigin_c_idx(int origin_c_idx) {
		this.origin_c_idx = origin_c_idx;
	}



	
}
