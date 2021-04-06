package com.TicketLupin.web.service;

public class GoodbadVo {
	private int lidx;
	private int midx;
	private int c_idx;
	private int origin_c_idx;
	private String lsort;
	
	public GoodbadVo(int lidx, int midx, int c_idx, int origin_c_idx, String lsort) {
		this.lidx = lidx; 
		this.midx = midx;
		this.c_idx = c_idx;
		this.origin_c_idx = origin_c_idx;
		this.lsort = lsort; 
	}
	
		
	public int getLidx() {
		return lidx;
	}
	public void setLidx(int lidx) {
		this.lidx = lidx;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public int getOrigin_c_idx() {
		return origin_c_idx;
	}
	public void setOrigin_c_idx(int origin_c_idx) {
		this.origin_c_idx = origin_c_idx;
	}
	public String getLsort() {
		return lsort;
	}
	public void setLsort(String lsort) {
		this.lsort = lsort;
	}
	

	
}