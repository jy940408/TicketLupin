package com.TicketLupin.web.service;

import java.sql.Date;

public class MemberVo extends ReservationVo{
	
	private int midx;
	private String mid;
	private String mname;
	private String mpwd;
	private String mssn;
	private String maddress;
	private String memail;
	private String mphone;
	private int maccount;
	private Date msignindate;
	private Date msecessiondate;
	private String msecessionyn;
	private String mgrade;
	private String memailchecked;
	private String mbirthmonth;
	private String mbirthday;
	private String mpostcode;
	private String mdetailaddress;
	private String mextraaddress;
	private String mgender;
	private String stitle;
	
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public String getMpostcode() {
		return mpostcode;
	}
	public void setMpostcode(String mpostcode) {
		this.mpostcode = mpostcode;
	}
	public String getMdetailaddress() {
		return mdetailaddress;
	}
	public void setMdetailaddress(String mdetailaddress) {
		this.mdetailaddress = mdetailaddress;
	}
	public String getMextraaddress() {
		return mextraaddress;
	}
	public void setMextraaddress(String mextraaddress) {
		this.mextraaddress = mextraaddress;
	}
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public String getMbirthmonth() {
		return mbirthmonth;
	}
	public void setMbirthmonth(String mbirthmonth) {
		this.mbirthmonth = mbirthmonth;
	}
	public String getMbirthday() {
		return mbirthday;
	}
	public void setMbirthday(String mbirthday) {
		this.mbirthday = mbirthday;
	}


	
	
	public MemberVo() {
		super();
	}
	
	
	
	
	public String getMemailchecked() {
		return memailchecked;
	}
	public void setMemailchecked(String memailchecked) {
		this.memailchecked = memailchecked;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	public String getMssn() {
		return mssn;
	}
	public void setMssn(String mssn) {
		this.mssn = mssn;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public int getMaccount() {
		return maccount;
	}
	public void setMaccount(int maccount) {
		this.maccount = maccount;
	}
	public Date getMsignindate() {
		return msignindate;
	}
	public void setMsignindate(Date msignindate) {
		this.msignindate = msignindate;
	}
	public Date getMsecessiondate() {
		return msecessiondate;
	}
	public void setMsecessiondate(Date msecessiondate) {
		this.msecessiondate = msecessiondate;
	}
	public String getMsecessionyn() {
		return msecessionyn;
	}
	public void setMsecessionyn(String msecessionyn) {
		this.msecessionyn = msecessionyn;
	}
	public String getMgrade() {
		return mgrade;
	}
	public void setMgrade(String mgrade) {
		this.mgrade = mgrade;
	}
	
	public MemberVo(int midx, String mid, String mname, String mpwd, String mssn, String maddress, String memail,
			String mphone, int maccount, Date msignindate, Date msecessiondate, String msecessionyn, String mgrade,
			String memailchecked, String mbirthmonth, String mbirthday, String mpostcode,
			String mdetailaddress, String mextraaddress, String mgender) {
		super();
		this.midx = midx;
		this.mid = mid;
		this.mname = mname;
		this.mpwd = mpwd;
		this.mssn = mssn;
		this.maddress = maddress;
		this.memail = memail;
		this.mphone = mphone;
		this.maccount = maccount;
		this.msignindate = msignindate;
		this.msecessiondate = msecessiondate;
		this.msecessionyn = msecessionyn;
		this.mgrade = mgrade;
		this.memailchecked = memailchecked;
		this.mbirthmonth = mbirthmonth;
		this.mbirthday = mbirthday;
		this.mpostcode = mpostcode;
		this.mdetailaddress = mdetailaddress;
		this.mextraaddress = mextraaddress;
		this.mgender = mgender;
	}
	
	
	

	
	
	


	
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
