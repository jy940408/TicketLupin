package com.TicketLupin.web.service;

import java.sql.Date;

public class QuestionVo {

	private int Qidx;
	private String Qtitle;
	private String Qcontent;
	private int Midx;
	private Date Qregdate;
	private int Qhit;
	private String Qimage;
	private String Qfiles;
	private String Qpub;
	private String Qdelyn;
	
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
}
