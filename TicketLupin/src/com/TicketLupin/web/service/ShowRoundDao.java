package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TicketLupin.web.DBconn.DBconn;

public class ShowRoundDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	
	public ShowRoundDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public ShowRoundVo insertShowRound(ShowRoundVo srv) {
		ShowRoundVo result = null;
		//타占쏙옙틀, 占썲르, 占쌜쇽옙占쏙옙짜, 占쏙옙占쏙옙, 占싱뱄옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占승놂옙짜, 占쏙옙占쏙옙占쏙옙 占쏙옙짜, 占쏙옙占�, 회占쏙옙, 占쏙옙占쏙옙占싫�, 占쏙옙占싸몌옙占쌍쇽옙, 占쏙옙占쏙옙占쌍쇽옙, 占쏙옙占쌍쇽옙, 占쏙옙占쏙옙占쌓몌옙
		String sql = "INSERT INTO SHOWROUND(SIDX, SRIDX, SRDATE, SRROUND1, SRROUND2, SRROUND3, SRROUND4)"
				+ "VALUES(?, SHOWROUND_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, srv.getSidx());
			pstmt.setString(2, srv.getSrdate());
			pstmt.setString(3, srv.getSrround1());
			pstmt.setString(4, srv.getSrround2());
			pstmt.setString(5, srv.getSrround3());
			pstmt.setString(6, srv.getSrround4());
			ResultSet rs = pstmt.executeQuery();
			System.out.println("ShowRoundDao 들어오는 것 확인");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
