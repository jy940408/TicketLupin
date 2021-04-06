package com.TicketLupin.web.service;

import java.sql.Date;

public class Show2Vo {

	private int sidx;
	private String stitleimage;
	private String sround;
	private String sprice;
	private String snotice;
	private String sdiscount;
	private String sinfo;
	private String scompany;
	private String sroundimage;
	private String spriceimage;
	private String snoticeimage;
	private String sdiscountimage;
	private String sinfoimage;
	private String scompanyimage;
	private String sdelyn;
	
	public Show2Vo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Show2Vo(int sidx, String stitleimage, String sround, String sprice, String snotice, String sdiscount,
			String sinfo, String scompany, String sroundimage, String spriceimage, String snoticeimage,
			String sdiscountimage, String sinfoimage, String scompanyimage, String sdelyn) {
		super();
		this.sidx = sidx;
		this.stitleimage = stitleimage;
		this.sround = sround;
		this.sprice = sprice;
		this.snotice = snotice;
		this.sdiscount = sdiscount;
		this.sinfo = sinfo;
		this.scompany = scompany;
		this.sroundimage = sroundimage;
		this.spriceimage = spriceimage;
		this.snoticeimage = snoticeimage;
		this.sdiscountimage = sdiscountimage;
		this.sinfoimage = sinfoimage;
		this.scompanyimage = scompanyimage;
		this.sdelyn = sdelyn;
	}

	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public String getStitleimage() {
		return stitleimage;
	}

	public void setStitleimage(String stitleimage) {
		this.stitleimage = stitleimage;
	}

	public String getSround() {
		return sround;
	}

	public void setSround(String sround) {
		this.sround = sround;
	}

	public String getSprice() {
		return sprice;
	}

	public void setSprice(String sprice) {
		this.sprice = sprice;
	}

	public String getSnotice() {
		return snotice;
	}

	public void setSnotice(String snotice) {
		this.snotice = snotice;
	}

	public String getSdiscount() {
		return sdiscount;
	}

	public void setSdiscount(String sdiscount) {
		this.sdiscount = sdiscount;
	}

	public String getSinfo() {
		return sinfo;
	}

	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}

	public String getScompany() {
		return scompany;
	}

	public void setScompany(String scompany) {
		this.scompany = scompany;
	}

	public String getSroundimage() {
		return sroundimage;
	}

	public void setSroundimage(String sroundimage) {
		this.sroundimage = sroundimage;
	}

	public String getSpriceimage() {
		return spriceimage;
	}

	public void setSpriceimage(String spriceimage) {
		this.spriceimage = spriceimage;
	}

	public String getSnoticeimage() {
		return snoticeimage;
	}

	public void setSnoticeimage(String snoticeimage) {
		this.snoticeimage = snoticeimage;
	}

	public String getSdiscountimage() {
		return sdiscountimage;
	}

	public void setSdiscountimage(String sdiscountimage) {
		this.sdiscountimage = sdiscountimage;
	}

	public String getSinfoimage() {
		return sinfoimage;
	}

	public void setSinfoimage(String sinfoimage) {
		this.sinfoimage = sinfoimage;
	}

	public String getScompanyimage() {
		return scompanyimage;
	}

	public void setScompanyimage(String scompanyimage) {
		this.scompanyimage = scompanyimage;
	}

	public String getSdelyn() {
		return sdelyn;
	}

	public void setSdelyn(String sdelyn) {
		this.sdelyn = sdelyn;
	}

	@Override
	public String toString() {
		return "Show2Vo [sidx=" + sidx + ", stitleimage=" + stitleimage + ", sround=" + sround + ", sprice=" + sprice
				+ ", snotice=" + snotice + ", sdiscount=" + sdiscount + ", sinfo=" + sinfo + ", scompany=" + scompany
				+ ", sroundimage=" + sroundimage + ", spriceimage=" + spriceimage + ", snoticeimage=" + snoticeimage
				+ ", sdiscountimage=" + sdiscountimage + ", sinfoimage=" + sinfoimage + ", scompanyimage="
				+ scompanyimage + ", sdelyn=" + sdelyn + "]";
	}

	
}
