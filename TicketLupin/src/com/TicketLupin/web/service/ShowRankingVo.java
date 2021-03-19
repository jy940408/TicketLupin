package com.TicketLupin.web.service;

import java.sql.Date;

public class ShowRankingVo extends ShowVo{

	private int cnt;

	public ShowRankingVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getCnt() {
		return cnt;
	}



	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public ShowRankingVo(int sidx, String stitle, String sgenre, Date sregdate, String scontent, String simage,
			int spay, String sdelyn, int cidx, Date sopendate, Date senddate, String srating, String sround,
			int spostcode, String sroadaddress, String sjibunaddress, String sdetailaddress, String sextraaddress,
			int midx, Date sticketingdate, int cnt) {
		super(sidx, stitle, sgenre, sregdate, scontent, simage, spay, sdelyn, cidx, sopendate, senddate, srating, sround,
				spostcode, sroadaddress, sjibunaddress, sdetailaddress, sextraaddress, midx, sticketingdate);

		this.cnt = cnt;
	}
	
	
	
}
