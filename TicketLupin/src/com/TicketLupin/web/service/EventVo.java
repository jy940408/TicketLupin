package com.TicketLupin.web.service;

import java.sql.Date;

public class EventVo {

	private int eidx;
	private String etitle;
	private String econtent;
	private int midx;
	private Date eregdate;
	private int ehit;
	private String eimage;
	private String efiles;
	private String epub;
	private int egood;
	private String edelyn;
	private Date eopendate;
	private Date eenddate;
	
	public EventVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventVo(int eidx, String etitle, String econtent, int midx, Date eregdate, int ehit, String eimage,
			String efiles, String epub, int egood, String edelyn, Date eopendate, Date eenddate) {
		super();
		this.eidx = eidx;
		this.etitle = etitle;
		this.econtent = econtent;
		this.midx = midx;
		this.eregdate = eregdate;
		this.ehit = ehit;
		this.eimage = eimage;
		this.efiles = efiles;
		this.epub = epub;
		this.egood = egood;
		this.edelyn = edelyn;
		this.eopendate = eopendate;
		this.eenddate = eenddate;
	}

	public int getEidx() {
		return eidx;
	}

	public void setEidx(int eidx) {
		this.eidx = eidx;
	}

	public String getEtitle() {
		return etitle;
	}

	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}

	public String getEcontent() {
		return econtent;
	}

	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	public Date getEregdate() {
		return eregdate;
	}

	public void setEregdate(Date eregdate) {
		this.eregdate = eregdate;
	}

	public int getEhit() {
		return ehit;
	}

	public void setEhit(int ehit) {
		this.ehit = ehit;
	}

	public String getEimage() {
		return eimage;
	}

	public void setEimage(String eimage) {
		this.eimage = eimage;
	}

	public String getEfiles() {
		return efiles;
	}

	public void setEfiles(String efiles) {
		this.efiles = efiles;
	}

	public String getEpub() {
		return epub;
	}

	public void setEpub(String epub) {
		this.epub = epub;
	}

	public int getEgood() {
		return egood;
	}

	public void setEgood(int egood) {
		this.egood = egood;
	}

	public String getEdelyn() {
		return edelyn;
	}

	public void setEdelyn(String edelyn) {
		this.edelyn = edelyn;
	}

	public Date getEopendate() {
		return eopendate;
	}

	public void setEopendate(Date eopendate) {
		this.eopendate = eopendate;
	}

	public Date getEenddate() {
		return eenddate;
	}

	public void setEenddate(Date eenddate) {
		this.eenddate = eenddate;
	}

	@Override
	public String toString() {
		return "EventVo [eidx=" + eidx + ", etitle=" + etitle + ", econtent=" + econtent + ", midx=" + midx
				+ ", eregdate=" + eregdate + ", ehit=" + ehit + ", eimage=" + eimage + ", efiles=" + efiles + ", epub="
				+ epub + ", egood=" + egood + ", edelyn=" + edelyn + ", eopendate=" + eopendate + ", eenddate="
				+ eenddate + "]";
	}
	
	
	
}
