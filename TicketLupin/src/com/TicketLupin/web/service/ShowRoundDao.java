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
	
	public ArrayList<ShowRoundVo> getShowRoundList(int idx) {
		
		ArrayList<ShowRoundVo> result = new ArrayList<>();
		
		String sql = "SELECT SRIDX, SIDX, SRDATE, SRROUND1, SRROUND2, SRROUND3, SRROUND4 FROM SHOWROUND WHERE SIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int sridx = rs.getInt("SRIDX");
				int sidx = rs.getInt("SIDX");
				String srdate = rs.getString("SRDATE");
				String srround1 = rs.getString("SRROUND1");
				String srround2 = rs.getString("SRROUND2");
				String srround3 = rs.getString("SRROUND3");
				String srround4 = rs.getString("SRROUND4");
				
				ShowRoundVo srv = new ShowRoundVo(sridx, sidx, srdate, srround1, srround2, srround3, srround4);
				
				result.add(srv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ShowRoundVo getShowRoundDetail(int idx, String date){
		
		ShowRoundVo srv = null;
		
		String sql = "SELECT SRIDX, SIDX, SRDATE, SRROUND1, SRROUND2, SRROUND3, SRROUND4 FROM SHOWROUND WHERE SRDATE = '" + date + "' AND SIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			int sridx = rs.getInt("SRIDX");
			int sidx = rs.getInt("SIDX");
			String srdate = rs.getString("SRDATE");
			String srround1 = rs.getString("SRROUND1");
			String srround2 = rs.getString("SRROUND2");
			String srround3 = rs.getString("SRROUND3");
			String srround4 = rs.getString("SRROUND4");
			
			srv = new ShowRoundVo(sridx, sidx, srdate, srround1, srround2, srround3, srround4);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return srv;
		
	}
		
}
