package com.TicketLupin.web.service;

import java.sql.Date;

public class C_commentVo {
	
	private int ccidx;
	private int origin_ccidx;
	private int cidx;
	private int midx;
	private String cccontent;
	private Date ccregdate ;
	private String ccdelyn ;
	private int ccdepth;
	private String cccategory;
	public int getCcidx() {
		return ccidx;
	}
	public void setCcidx(int ccidx) {
		this.ccidx = ccidx;
	}
	public int getOrigin_ccidx() {
		return origin_ccidx;
	}
	public void setOrigin_ccidx(int origin_ccidx) {
		this.origin_ccidx = origin_ccidx;
	}
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getCccontent() {
		return cccontent;
	}
	public void setCccontent(String cccontent) {
		this.cccontent = cccontent;
	}
	public Date getCcregdate() {
		return ccregdate;
	}
	public void setCcregdate(Date ccregdate) {
		this.ccregdate = ccregdate;
	}
	public String getCcdelyn() {
		return ccdelyn;
	}
	public void setCcdelyn(String ccdelyn) {
		this.ccdelyn = ccdelyn;
	}
	public int getCcdepth() {
		return ccdepth;
	}
	public void setCcdepth(int ccdepth) {
		this.ccdepth = ccdepth;
	}
	public String getCccategory() {
		return cccategory;
	}
	public void setCccategory(String cccategory) {
		this.cccategory = cccategory;
	}
	
	
}
