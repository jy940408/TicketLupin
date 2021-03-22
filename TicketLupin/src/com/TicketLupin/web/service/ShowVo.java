package com.TicketLupin.web.service;

import java.sql.Date;

public class ShowVo {

	private int sidx;
	private String stitle;
	private String sgenre;
	private Date sregdate;
	private int spay;
	private String sdelyn;
	private int cidx;
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
	
	public ShowVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowVo(int sidx, String stitle, String sgenre, Date sregdate, int spay, String sdelyn, int cidx,
			Date sopendate, Date senddate, String srating, int spostcode, String sroadaddress, String sjibunaddress,
			String sdetailaddress, String sextraaddress, int midx, Date sticketingdate, String sround, String sprice,
			String snotice, String sdiscount, String sinfo, String scompany, String sroundimage, String spriceimage,
			String snoticeimage, String sdiscountimage, String sinfoimage, String scompanyimage) {
		super();
		this.sidx = sidx;
		this.stitle = stitle;
		this.sgenre = sgenre;
		this.sregdate = sregdate;
		this.spay = spay;
		this.sdelyn = sdelyn;
		this.cidx = cidx;
		this.sopendate = sopendate;
		this.senddate = senddate;
		this.srating = srating;
		this.spostcode = spostcode;
		this.sroadaddress = sroadaddress;
		this.sjibunaddress = sjibunaddress;
		this.sdetailaddress = sdetailaddress;
		this.sextraaddress = sextraaddress;
		this.midx = midx;
		this.sticketingdate = sticketingdate;
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

	@Override
	public String toString() {
		return "ShowVo [sidx=" + sidx + ", stitle=" + stitle + ", sgenre=" + sgenre + ", sregdate=" + sregdate
				+ ", spay=" + spay + ", sdelyn=" + sdelyn + ", cidx=" + cidx + ", sopendate=" + sopendate
				+ ", senddate=" + senddate + ", srating=" + srating + ", spostcode=" + spostcode + ", sroadaddress="
				+ sroadaddress + ", sjibunaddress=" + sjibunaddress + ", sdetailaddress=" + sdetailaddress
				+ ", sextraaddress=" + sextraaddress + ", midx=" + midx + ", sticketingdate=" + sticketingdate
				+ ", sround=" + sround + ", sprice=" + sprice + ", snotice=" + snotice + ", sdiscount=" + sdiscount
				+ ", sinfo=" + sinfo + ", scompany=" + scompany + ", sroundimage=" + sroundimage + ", spriceimage="
				+ spriceimage + ", snoticeimage=" + snoticeimage + ", sdiscountimage=" + sdiscountimage
				+ ", sinfoimage=" + sinfoimage + ", scompanyimage=" + scompanyimage + "]";
	}
	
	
}
