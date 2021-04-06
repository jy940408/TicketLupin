package com.TicketLupin.web.service;

import java.util.Date;

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
	private String estart;
	private String eend;
	private String ethumbnail;
	private String ecategory;
	
	
	public EventVo() {
		
	}
	
	public EventVo(int eidx, String etitle, String econtent, int midx, Date eregdate, int ehit, String eimage,
			String efiles, String epub, int egood, String edelyn, String estart, String eend, String ethumbnail, String ecategory) {
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
		this.estart = estart;
		this.eend = eend;
		this.ethumbnail = ethumbnail;
		this.ecategory = ecategory;
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
	public String getEstart() {
		return estart;
	}

	public void setEstart(String estart) {
		this.estart = estart;
	}

	public String getEend() {
		return eend;
	}

	public void setEend(String eend) {
		this.eend = eend;
	}
	public String getEthumbnail() {
		return ethumbnail;
	}

	public void setEthumbnail(String ethumbnail) {
		this.ethumbnail = ethumbnail;
	}
	public String getEcategory() {
		return ecategory;
	}

	public void setEcategory(String ecategory) {
		this.ecategory = ecategory;
	}

	@Override
	public String toString() {
		return "EventVo [eidx=" + eidx + ", etitle=" + etitle + ", econtent=" + econtent + ", midx=" + midx
				+ ", eregdate=" + eregdate + ", ehit=" + ehit + ", eimage=" + eimage + ", efiles=" + efiles + ", epub="
				+ epub + ", egood=" + egood + ", edelyn=" + edelyn + ", estart=" + estart + ", eend=" + eend
				+ ", ethumbnail=" + ethumbnail + ", ecategory=" + ecategory + "]";
	}


	


	
	
}
