package com.TicketLupin.web.service;

import java.sql.Date;

public class Show1Vo extends Show2Vo{

	private int sidx;
	private String stitle;
	private String sgenre;
	private Date sregdate;
	private Date sopendate;
	private Date senddate;
	private String srating;
	private int spostcode;
	private String sroadaddress;
	private String sjibunaddress;
	private String sdetailaddress;
	private String sextraaddress;
	private int midx;
	private Date sticketingdate;
	private int svipprice;
	private int srprice;
	private int ssprice;
	private int saprice;
	private String sdelyn;
	
	public Show1Vo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Show1Vo(int sidx, String stitleimage, String sround, String sprice, String snotice, String sdiscount,
			String sinfo, String scompany, String sroundimage, String spriceimage, String snoticeimage,
			String sdiscountimage, String sinfoimage, String scompanyimage, String sdelyn) {
		super(sidx, stitleimage, sround, sprice, snotice, sdiscount, sinfo, scompany, sroundimage, spriceimage, snoticeimage,
				sdiscountimage, sinfoimage, scompanyimage, sdelyn);
		// TODO Auto-generated constructor stub
	}

	
	
	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getSgenre() {
		return sgenre;
	}

	public void setSgenre(String sgenre) {
		this.sgenre = sgenre;
	}

	public Date getSregdate() {
		return sregdate;
	}

	public void setSregdate(Date sregdate) {
		this.sregdate = sregdate;
	}

	public Date getSopendate() {
		return sopendate;
	}

	public void setSopendate(Date sopendate) {
		this.sopendate = sopendate;
	}

	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getSrating() {
		return srating;
	}

	public void setSrating(String srating) {
		this.srating = srating;
	}

	public int getSpostcode() {
		return spostcode;
	}

	public void setSpostcode(int spostcode) {
		this.spostcode = spostcode;
	}

	public String getSroadaddress() {
		return sroadaddress;
	}

	public void setSroadaddress(String sroadaddress) {
		this.sroadaddress = sroadaddress;
	}

	public String getSjibunaddress() {
		return sjibunaddress;
	}

	public void setSjibunaddress(String sjibunaddress) {
		this.sjibunaddress = sjibunaddress;
	}

	public String getSdetailaddress() {
		return sdetailaddress;
	}

	public void setSdetailaddress(String sdetailaddress) {
		this.sdetailaddress = sdetailaddress;
	}

	public String getSextraaddress() {
		return sextraaddress;
	}

	public void setSextraaddress(String sextraaddress) {
		this.sextraaddress = sextraaddress;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	public Date getSticketingdate() {
		return sticketingdate;
	}

	public void setSticketingdate(Date sticketingdate) {
		this.sticketingdate = sticketingdate;
	}

	public int getSvipprice() {
		return svipprice;
	}

	public void setSvipprice(int svipprice) {
		this.svipprice = svipprice;
	}

	public int getSrprice() {
		return srprice;
	}

	public void setSrprice(int srprice) {
		this.srprice = srprice;
	}

	public int getSsprice() {
		return ssprice;
	}

	public void setSsprice(int ssprice) {
		this.ssprice = ssprice;
	}

	public int getSaprice() {
		return saprice;
	}

	public void setSaprice(int saprice) {
		this.saprice = saprice;
	}

	public String getSdelyn() {
		return sdelyn;
	}

	public void setSdelyn(String sdelyn) {
		this.sdelyn = sdelyn;
	}

	@Override
	public String toString() {
		return "Show1Vo [sidx=" + sidx + ", stitle=" + stitle + ", sgenre=" + sgenre + ", sregdate=" + sregdate
				+ ", sopendate=" + sopendate + ", senddate=" + senddate + ", srating=" + srating + ", spostcode="
				+ spostcode + ", sroadaddress=" + sroadaddress + ", sjibunaddress=" + sjibunaddress
				+ ", sdetailaddress=" + sdetailaddress + ", sextraaddress=" + sextraaddress + ", midx=" + midx
				+ ", sticketingdate=" + sticketingdate + ", svipprice=" + svipprice + ", srprice=" + srprice
				+ ", ssprice=" + ssprice + ", saprice=" + saprice + ", sdelyn=" + sdelyn + "]";
	}

	
	
}
