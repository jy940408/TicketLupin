package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;
import com.TicketLupin.web.service.MyticketVo;

public class MyticketDao {

	public List<ReservationShowVo> getReservationList(int midx){
		
		List<ReservationShowVo> list = new ArrayList<ReservationShowVo>();
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 NUM, B.RIREGDATE, B.SRDATE, B.SRROUND, B.RIIDX, C.STITLE FROM RESERVATIONIDX B, SHOW1 C, (SELECT @ROWNUM := 0) TMP WHERE B.SIDX = C.SIDX AND B.MIDX = ? AND RIDELYN = 'N' ORDER BY B.RIREGDATE DESC) A WHERE NUM <= 5";
		
		try {
			
			DBconn dbconn = new DBconn();
			Connection conn = dbconn.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationShowVo rsv = new ReservationShowVo();
				
				rsv.setNum(rs.getInt("NUM"));
				rsv.setRregdate(rs.getDate("RIREGDATE"));
				rsv.setSrdate(rs.getString("SRDATE"));
				rsv.setSrround(rs.getString("SRROUND"));
				rsv.setStitle(rs.getString("STITLE"));
				rsv.setRiidx(rs.getInt("RIIDX"));
				
				list.add(rsv);
				
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
				
			e.printStackTrace();
				
		}
			
		return list;
	}
	
	public int getReservationCount(int midx) {
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.RIREGDATE, B.SRDATE, C.STITLE FROM RESERVATIONIDX B, SHOW1 C, (SELECT @ROWNUM := 0) TMP WHERE B.SIDX = C.SIDX AND B.MIDX = ? AND RIDELYN = 'N' ORDER BY B.SRDATE DESC) A";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count = rs.getInt("COUNT");
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return count;
	}
	
	public MemberVo getName(int midx) {
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo mv = null;
		String sql = "SELECT MNAME FROM MEMBER WHERE MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mv = new MemberVo();
				
				mv.setMname(rs.getString("MNAME"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			
		}catch(Exception e){
				
			e.printStackTrace();
			
		}
		
		return mv;
	}
	
	public List<QuestionVo> getQuestionList(int midx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 NUM, B.QIDX, B.QTITLE, B.QSTATE, B.QREGDATE FROM QUESTION B, (SELECT @ROWNUM := 0) TMP WHERE MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A WHERE NUM <= 5";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				QuestionVo qv = new QuestionVo();
				
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQstate(rs.getString("QSTATE"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				
				list.add(qv);	
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return list;
	}
	
}
