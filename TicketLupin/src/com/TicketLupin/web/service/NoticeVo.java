package com.TicketLupin.web.service;

import java.sql.Date;

public class NoticeVo {
	
	private int num;
	private int nidx;
	private String ntitle;
	private String ncontent;
	private int midx;
	private Date nregdate;
	private int nhit;
	private String nimage;
	private String nfiles;
	private String npub;
	private int ngood;
	private String ndelyn;
	private String ncategory;
		
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNcategory() {
		return ncategory;
	}
	public void setNcategory(String ncategory) {
		this.ncategory = ncategory;
	}
	public int getNidx() {
		return nidx;
	}
	public void setNidx(int nidx) {
		this.nidx = nidx;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public Date getNregdate() {
		return nregdate;
	}
	public void setNregdate(Date nregdate) {
		this.nregdate = nregdate;
	}
	public int getNhit() {
		return nhit;
	}
	public void setNhit(int nhit) {
		this.nhit = nhit;
	}
	public String getNimage() {
		return nimage;
	}
	public void setNimage(String nimage) {
		this.nimage = nimage;
	}
	public String getNfiles() {
		return nfiles;
	}
	public void setNfiles(String nfiles) {
		this.nfiles = nfiles;
	}
	public String getNpub() {
		return npub;
	}
	public void setNpub(String npub) {
		this.npub = npub;
	}
	public int getNgood() {
		return ngood;
	}
	public void setNgood(int ngood) {
		this.ngood = ngood;
	}
	public String getNdelyn() {
		return ndelyn;
	}
	public void setNdelyn(String ndelyn) {
		this.ndelyn = ndelyn;
	}
	
}
