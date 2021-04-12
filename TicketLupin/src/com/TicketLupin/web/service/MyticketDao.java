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
	private	Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MyticketDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public List<ReservationShowVo> getReservationList(int midx){
		
		List<ReservationShowVo> list = new ArrayList<ReservationShowVo>();
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 NUM, B.RIREGDATE, B.SRDATE, B.SRROUND, C.STITLE FROM RESERVATIONIDX B, SHOW1 C, (SELECT @ROWNUM := 0) TMP WHERE B.SIDX = C.SIDX AND B.MIDX = ? ORDER BY B.RIREGDATE DESC) A WHERE NUM <= 5";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationShowVo rsv = new ReservationShowVo();
				
				rsv.setNum(rs.getInt("NUM"));
				rsv.setRregdate(rs.getDate("RIREGDATE"));
				rsv.setSrdate(rs.getString("SRDATE"));
				rsv.setSrround(rs.getString("SRROUND"));
				rsv.setStitle(rs.getString("STITLE"));
				
				list.add(rsv);
				
			}
			
		}catch(Exception e) {
				
			e.printStackTrace();
				
		}finally {
				
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
			
		return list;
	}
	
	public int getReservationCount(int midx) {
		
		int count = 0;
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.RIREGDATE, B.SRDATE, C.STITLE FROM RESERVATIONIDX B, SHOW1 C, (SELECT @ROWNUM := 0) TMP WHERE B.SIDX = C.SIDX AND B.MIDX = ? ORDER BY B.SRDATE DESC) A";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count = rs.getInt("COUNT");
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
	
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return count;
	}
	
	public MemberVo getName(int midx) {
		
		MemberVo mv = null;
		ResultSet rs = null;
		
		String sql = "SELECT MNAME FROM MEMBER WHERE MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mv = new MemberVo();
				
				mv.setMname(rs.getString("MNAME"));
			}
			
		}catch(Exception e){
				
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return mv;
	}
	
	public List<QuestionVo> getQuestionList(int midx){
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		
		//String sql = "SELECT NUM, QIDX, QTITLE, QSTATE, QREGDATE FROM (SELECT ROWNUM NUM, N.* FROM (SELECT QIDX, QTITLE, QSTATE, QREGDATE FROM QUESTION WHERE MIDX = ? ORDER BY QREGDATE DESC) N) WHERE NUM <= 5";
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 NUM, B.QIDX, B.QTITLE, B.QSTATE, B.QREGDATE FROM QUESTION B, (SELECT @ROWNUM := 0) TMP WHERE MIDX = ? ORDER BY QREGDATE DESC) A WHERE NUM <= 5";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				QuestionVo qv = new QuestionVo();
				
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQstate(rs.getString("QSTATE"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				
				list.add(qv);	
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return list;
	}
	
}
