package com.TicketLupin.web.service;

import java.sql.Date;

public class WinnerVo {

	private int iidx;
	private String ititle;
	private String icontent;
	private int midx;
	private Date iregdate;
	private int ihit;
	private String iimage;
	private String ifiles;
	private String ipub;
	private int igood;
	private String idelyn;
	
	
	
	public WinnerVo() {
		
	}

	public WinnerVo(int iidx, String ititle, String icontent, int midx, Date iregdate, int ihit, String iimage,
			String ifiles, String ipub, int igood, String idelyn) {

		this.iidx = iidx;
		this.ititle = ititle;
		this.icontent = icontent;
		this.midx = midx;
		this.iregdate = iregdate;
		this.ihit = ihit;
		this.iimage = iimage;
		this.ifiles = ifiles;
		this.ipub = ipub;
		this.igood = igood;
		this.idelyn = idelyn;
	}

	public int getIidx() {
		return iidx;
	}

	public void setIidx(int iidx) {
		this.iidx = iidx;
	}

	public String getItitle() {
		return ititle;
	}

	public void setItitle(String ititle) {
		this.ititle = ititle;
	}

	public String getIcontent() {
		return icontent;
	}

	public void setIcontent(String icontent) {
		this.icontent = icontent;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	public Date getIregdate() {
		return iregdate;
	}

	public void setIregdate(Date iregdate) {
		this.iregdate = iregdate;
	}

	public int getIhit() {
		return ihit;
	}

	public void setIhit(int ihit) {
		this.ihit = ihit;
	}

	public String getIimage() {
		return iimage;
	}

	public void setIimage(String iimage) {
		this.iimage = iimage;
	}

	public String getIfiles() {
		return ifiles;
	}

	public void setIfiles(String ifiles) {
		this.ifiles = ifiles;
	}

	public String getIpub() {
		return ipub;
	}

	public void setIpub(String ipub) {
		this.ipub = ipub;
	}

	public int getIgood() {
		return igood;
	}

	public void setIgood(int igood) {
		this.igood = igood;
	}

	public String getIdelyn() {
		return idelyn;
	}

	public void setIdelyn(String idelyn) {
		this.idelyn = idelyn;
	}

	@Override
	public String toString() {
		return "WinnerVo [iidx=" + iidx + ", ititle=" + ititle + ", icontent=" + icontent + ", midx=" + midx
				+ ", iregdate=" + iregdate + ", ihit=" + ihit + ", iimage=" + iimage + ", ifiles=" + ifiles + ", ipub="
				+ ipub + ", igood=" + igood + ", idelyn=" + idelyn + "]";
	}
	
	
}
