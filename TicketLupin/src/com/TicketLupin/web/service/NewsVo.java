package com.TicketLupin.web.service;

import java.sql.Date;

public class NewsVo {

	private int widx;
	private String wtitle;
	private String wbasicinfo;
	private String wintroduce;
	private String wdiscount;
	private String wcompany;
	private String wcategory;
	private int midx;
	private Date wregdate;
	private int whit;
	private String wimage;
	private String wfiles;
	private String wpub;
	private int wgood;
	private String wdelyn;
	private String wtitleposter;
	private Date wopendate;
	
	public NewsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsVo(int widx, String wtitle, String wbasicinfo, String wintroduce, String wdiscount, String wcompany,
			String wcategory, int midx, Date wregdate, int whit, String wimage, String wfiles, String wpub, int wgood,
			String wdelyn, String wtitleposter, Date wopendate) {
		super();
		this.widx = widx;
		this.wtitle = wtitle;
		this.wbasicinfo = wbasicinfo;
		this.wintroduce = wintroduce;
		this.wdiscount = wdiscount;
		this.wcompany = wcompany;
		this.wcategory = wcategory;
		this.midx = midx;
		this.wregdate = wregdate;
		this.whit = whit;
		this.wimage = wimage;
		this.wfiles = wfiles;
		this.wpub = wpub;
		this.wgood = wgood;
		this.wdelyn = wdelyn;
		this.wtitleposter = wtitleposter;
		this.wopendate = wopendate;
	}

	public int getWidx() {
		return widx;
	}

	public void setWidx(int widx) {
		this.widx = widx;
	}

	public String getWtitle() {
		return wtitle;
	}

	public void setWtitle(String wtitle) {
		this.wtitle = wtitle;
	}

	public String getWbasicinfo() {
		return wbasicinfo;
	}

	public void setWbasicinfo(String wbasicinfo) {
		this.wbasicinfo = wbasicinfo;
	}

	public String getWintroduce() {
		return wintroduce;
	}

	public void setWintroduce(String wintroduce) {
		this.wintroduce = wintroduce;
	}

	public String getWdiscount() {
		return wdiscount;
	}

	public void setWdiscount(String wdiscount) {
		this.wdiscount = wdiscount;
	}

	public String getWcompany() {
		return wcompany;
	}

	public void setWcompany(String wcompany) {
		this.wcompany = wcompany;
	}

	public String getWcategory() {
		return wcategory;
	}

	public void setWcategory(String wcategory) {
		this.wcategory = wcategory;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	public Date getWregdate() {
		return wregdate;
	}

	public void setWregdate(Date wregdate) {
		this.wregdate = wregdate;
	}

	public int getWhit() {
		return whit;
	}

	public void setWhit(int whit) {
		this.whit = whit;
	}

	public String getWimage() {
		return wimage;
	}

	public void setWimage(String wimage) {
		this.wimage = wimage;
	}

	public String getWfiles() {
		return wfiles;
	}

	public void setWfiles(String wfiles) {
		this.wfiles = wfiles;
	}

	public String getWpub() {
		return wpub;
	}

	public void setWpub(String wpub) {
		this.wpub = wpub;
	}

	public int getWgood() {
		return wgood;
	}

	public void setWgood(int wgood) {
		this.wgood = wgood;
	}

	public String getWdelyn() {
		return wdelyn;
	}

	public void setWdelyn(String wdelyn) {
		this.wdelyn = wdelyn;
	}

	public String getWtitleposter() {
		return wtitleposter;
	}

	public void setWtitleposter(String wtitleposter) {
		this.wtitleposter = wtitleposter;
	}

	public Date getWopendate() {
		return wopendate;
	}

	public void setWopendate(Date wopendate) {
		this.wopendate = wopendate;
	}

	@Override
	public String toString() {
		return "NewsVo [widx=" + widx + ", wtitle=" + wtitle + ", wbasicinfo=" + wbasicinfo + ", wintroduce="
				+ wintroduce + ", wdiscount=" + wdiscount + ", wcompany=" + wcompany + ", wcategory=" + wcategory
				+ ", midx=" + midx + ", wregdate=" + wregdate + ", whit=" + whit + ", wimage=" + wimage + ", wfiles="
				+ wfiles + ", wpub=" + wpub + ", wgood=" + wgood + ", wdelyn=" + wdelyn + ", wtitleposter="
				+ wtitleposter + ", wopendate=" + wopendate + "]";
	}
	
	
	
	
	
}
