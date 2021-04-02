package com.TicketLupin.web.service;

import java.sql.Date;

public class ManagerVo {
//공통
	private String mid;
	private int num;
	private int no;
//	회원정보
	private int midx;
	private String mname;
	private Date msignindate;
// QNA
	private int qidx;
	private String qtitle;
	private String qtype;
	private Date qregdate;
// 댓글	
	private int c_idx;
	private String c_content;
	private Date c_regdate;
//예매정보
	private int pidx;
	private String stitle;
	private Date pdate;
	private int ppay;
	private Date riregdate;
	private int riidx;
	
	
	
	
	
	public int getRiidx() {
		return riidx;
	}
	public void setRiidx(int riidx) {
		this.riidx = riidx;
	}
	public Date getRiregdate() {
		return riregdate;
	}
	public void setRiregdate(Date riregdate) {
		this.riregdate = riregdate;
	}
	public int getNum() {
		return num;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public Date getMsignindate() {
		return msignindate;
	}
	public void setMsignindate(Date msignindate) {
		this.msignindate = msignindate;
	}
	public int getQidx() {
		return qidx;
	}
	public void setQidx(int qidx) {
		this.qidx = qidx;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public Date getQregdate() {
		return qregdate;
	}
	public void setQregdate(Date qregdate) {
		this.qregdate = qregdate;
	}
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public Date getC_regdate() {
		return c_regdate;
	}
	public void setC_regdate(Date c_regdate) {
		this.c_regdate = c_regdate;
	}
	public int getPidx() {
		return pidx;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public int getPpay() {
		return ppay;
	}
	public void setPpay(int ppay) {
		this.ppay = ppay;
	}
	
	
	
	
	
	
	
}
