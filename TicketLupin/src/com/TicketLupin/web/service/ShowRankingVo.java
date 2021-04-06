package com.TicketLupin.web.service;

import java.sql.Date;

public class ShowRankingVo extends Show1Vo{

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

	public ShowRankingVo(int sidx, String stitleimage, String sround, String sprice, String snotice, String sdiscount,
			String sinfo, String scompany, String sroundimage, String spriceimage, String snoticeimage,
			String sdiscountimage, String sinfoimage, String scompanyimage, String sdelyn, int cnt) {
		super(sidx, stitleimage, sround, sprice, snotice, sdiscount, sinfo, scompany, sroundimage, spriceimage, snoticeimage,
				sdiscountimage, sinfoimage, scompanyimage, sdelyn);
		this.cnt = cnt;
	}

	
	
	
}
