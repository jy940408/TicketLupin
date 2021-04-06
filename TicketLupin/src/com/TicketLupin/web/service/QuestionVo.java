package com.TicketLupin.web.service;

import java.sql.Date;

public class QuestionVo {

	private String mname;
	private String mid;
	private int num;
	private int Qidx;
	private String Qtitle;
	private String Qcontent;
	private String Qtype;
	private int Midx;
	private Date Qregdate;
	private int Qhit;
	private String Qimage;
	private String Qfiles;
	private String Qpub;
	private String Qdelyn;
	private String Qstate;
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getQidx() {
		return Qidx;
	}
	public void setQidx(int qidx) {
		Qidx = qidx;
	}
	public String getQtitle() {
		return Qtitle;
	}
	public void setQtitle(String qtitle) {
		Qtitle = qtitle;
	}
	public String getQcontent() {
		return Qcontent;
	}
	public void setQcontent(String qcontent) {
		Qcontent = qcontent;
	}
	public String getQtype() {
		return Qtype;
	}
	public void setQtype(String qtype) {
		Qtype = qtype;
	}
	public int getMidx() {
		return Midx;
	}
	public void setMidx(int midx) {
		Midx = midx;
	}
	public Date getQregdate() {
		return Qregdate;
	}
	public void setQregdate(Date qregdate) {
		Qregdate = qregdate;
	}
	public int getQhit() {
		return Qhit;
	}
	public void setQhit(int qhit) {
		Qhit = qhit;
	}
	public String getQimage() {
		return Qimage;
	}
	public void setQimage(String qimage) {
		Qimage = qimage;
	}
	public String getQfiles() {
		return Qfiles;
	}
	public void setQfiles(String qfiles) {
		Qfiles = qfiles;
	}
	public String getQpub() {
		return Qpub;
	}
	public void setQpub(String qpub) {
		Qpub = qpub;
	}
	public String getQdelyn() {
		return Qdelyn;
	}
	public void setQdelyn(String qdelyn) {
		Qdelyn = qdelyn;
	}
	public String getQstate() {
		return Qstate;
	}
	public void setQstate(String qstate) {
		Qstate = qstate;
	}
}
