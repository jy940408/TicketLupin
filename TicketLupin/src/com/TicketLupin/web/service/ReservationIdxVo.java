package com.TicketLupin.web.service;

import java.util.Date;

public class ReservationIdxVo extends ReservationVo{

	private int riidx;
	private int sidx;
	private int midx;
	private Date riregdate;
	private String srdate;
	private String srround;
	private int ribasic;
	private int ridiscount;
	private int rivat;
	private int ridelivery;
	private int ripayment;
	private Date rideldate;
	
	public ReservationIdxVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationIdxVo(int riidx, int sidx, int midx, Date riregdate, String srdate, String srround, int ribasic,
			int ridiscount, int rivat, int ridelivery, int ripayment, Date rideldate) {
		super();
		this.riidx = riidx;
		this.sidx = sidx;
		this.midx = midx;
		this.riregdate = riregdate;
		this.srdate = srdate;
		this.srround = srround;
		this.ribasic = ribasic;
		this.ridiscount = ridiscount;
		this.rivat = rivat;
		this.ridelivery = ridelivery;
		this.ripayment = ripayment;
		this.rideldate = rideldate;
	}

	public int getRiidx() {
		return riidx;
	}

	public void setRiidx(int riidx) {
		this.riidx = riidx;
	}

	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	public Date getRiregdate() {
		return riregdate;
	}

	public void setRiregdate(Date riregdate) {
		this.riregdate = riregdate;
	}

	public String getSrdate() {
		return srdate;
	}

	public void setSrdate(String srdate) {
		this.srdate = srdate;
	}

	public String getSrround() {
		return srround;
	}

	public void setSrround(String srround) {
		this.srround = srround;
	}

	public int getRibasic() {
		return ribasic;
	}

	public void setRibasic(int ribasic) {
		this.ribasic = ribasic;
	}

	public int getRidiscount() {
		return ridiscount;
	}

	public void setRidiscount(int ridiscount) {
		this.ridiscount = ridiscount;
	}

	public int getRivat() {
		return rivat;
	}

	public void setRivat(int rivat) {
		this.rivat = rivat;
	}

	public int getRidelivery() {
		return ridelivery;
	}

	public void setRidelivery(int ridelivery) {
		this.ridelivery = ridelivery;
	}

	public int getRipayment() {
		return ripayment;
	}

	public void setRipayment(int ripayment) {
		this.ripayment = ripayment;
	}
	public Date getRideldate() {
		return rideldate;
	}

	public void setRideldate(Date rideldate) {
		this.rideldate = rideldate;
	}

	@Override
	public String toString() {
		return "ReservationIdxVo [riidx=" + riidx + ", sidx=" + sidx + ", midx=" + midx + ", riregdate=" + riregdate
				+ ", srdate=" + srdate + ", srround=" + srround + ", ribasic=" + ribasic + ", ridiscount=" + ridiscount
				+ ", rivat=" + rivat + ", ridelivery=" + ridelivery + ", ripayment=" + ripayment + ", rideldate=" + rideldate + "]";
	}

	
	
}