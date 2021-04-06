package com.TicketLupin.web.service;

import java.sql.Date;

public class AnswerVo {

	private int aidx;
	private int qidx;
	private String atitle;
	private String acontent;
	private int midx;
	private Date aregdate;
	private int ahit;
	private String aimage;
	private String afiles;
	private String apub;
	private String adelyn;
	
	public int getAidx() {
		return aidx;
	}
	public void setAidx(int aidx) {
		this.aidx = aidx;
	}
	public int getQidx() {
		return qidx;
	}
	public void setQidx(int qidx) {
		this.qidx = qidx;
	}
	public String getAtitle() {
		return atitle;
	}
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public Date getAregdate() {
		return aregdate;
	}
	public void setAregdate(Date aregdate) {
		this.aregdate = aregdate;
	}
	public int getAhit() {
		return ahit;
	}
	public void setAhit(int ahit) {
		this.ahit = ahit;
	}
	public String getAimage() {
		return aimage;
	}
	public void setAimage(String aimage) {
		this.aimage = aimage;
	}
	public String getAfiles() {
		return afiles;
	}
	public void setAfiles(String afiles) {
		this.afiles = afiles;
	}
	public String getApub() {
		return apub;
	}
	public void setApub(String apub) {
		this.apub = apub;
	}
	public String getAdelyn() {
		return adelyn;
	}
	public void setAdelyn(String adelyn) {
		this.adelyn = adelyn;
	}
}
