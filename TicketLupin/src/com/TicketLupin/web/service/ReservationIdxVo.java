package com.TicketLupin.web.service;

import java.util.Date;

public class ReservationIdxVo extends ReservationVo{

	private int riidx;
	private Date riregdate;
	
	public ReservationIdxVo() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
}