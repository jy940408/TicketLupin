package com.TicketLupin.web.service;

import java.sql.Date;

public class ShowVo {

	private int sidx;
	private String stitle;
	private String sgenre;
	private Date sregdate;
	private String scontent;
	private String simage;
	private int spay;
	private String sdelyn;
	private int cidx;
	private Date sopendate;
	private Date senddate;
	private String srating;
	private String sround;
	private int spostcode;
	private String sroadaddress;
	private String sjibunaddress;
	private String sdetailaddress;
	private String sextraaddress;
	private int midx;
	
	public ShowVo() {
		
	}

	public ShowVo(int sidx, String stitle, String sgenre, Date sregdate, String scontent, String simage, int spay,
			String sdelyn, int cidx, Date sopendate, Date senddate, String srating, String sround, int spostcode,
			String sroadaddress, String sjibunaddress, String sdetailaddress, String sextraaddress, int midx) {
		super();
		this.sidx = sidx;
		this.stitle = stitle;
		this.sgenre = sgenre;
		this.sregdate = sregdate;
		this.scontent = scontent;
		this.simage = simage;
		this.spay = spay;
		this.sdelyn = sdelyn;
		this.cidx = cidx;
		this.sopendate = sopendate;
		this.senddate = senddate;
		this.srating = srating;
		this.sround = sround;
		this.spostcode = spostcode;
		this.sroadaddress = sroadaddress;
		this.sjibunaddress = sjibunaddress;
		this.sdetailaddress = sdetailaddress;
		this.sextraaddress = sextraaddress;
		this.midx = midx;
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

	public String getScontent() {
		return scontent;
	}

	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

	public String getSimage() {
		return simage;
	}

	public void setSimage(String simage) {
		this.simage = simage;
	}

	public int getSpay() {
		return spay;
	}

	public void setSpay(int spay) {
		this.spay = spay;
	}

	public String getSdelyn() {
		return sdelyn;
	}

	public void setSdelyn(String sdelyn) {
		this.sdelyn = sdelyn;
	}

	public int getCidx() {
		return cidx;
	}

	public void setCidx(int cidx) {
		this.cidx = cidx;
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

	public String getSround() {
		return sround;
	}

	public void setSround(String sround) {
		this.sround = sround;
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

	@Override
	public String toString() {
		return "ShowVo [sidx=" + sidx + ", stitle=" + stitle + ", sgenre=" + sgenre + ", sregdate=" + sregdate
				+ ", scontent=" + scontent + ", simage=" + simage + ", spay=" + spay + ", sdelyn=" + sdelyn + ", cidx="
				+ cidx + ", sopendate=" + sopendate + ", senddate=" + senddate + ", srating=" + srating + ", sround="
				+ sround + ", spostcode=" + spostcode + ", sroadaddress=" + sroadaddress + ", sjibunaddress="
				+ sjibunaddress + ", sdetailaddress=" + sdetailaddress + ", sextraaddress=" + sextraaddress + ",midx=" + midx + "]";
	}
	
	
	
}
