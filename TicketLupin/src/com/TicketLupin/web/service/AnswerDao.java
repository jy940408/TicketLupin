package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TicketLupin.web.DBconn.DBconn;

public class AnswerDao {

	public int insertAnswer(String acontent, int qidx, int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value = 0;
		
		String sql = "INSERT INTO ANSWER(QIDX, ACONTENT, MIDX, AREGDATE, ADELYN)"
					+"VALUES(?, ?, ?, NOW(), 'N')";
		
		String sql2 = "UPDATE QUESTION SET QSTATE = '완료' WHERE QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qidx);
			pstmt.setString(2, acontent);
			pstmt.setInt(3, midx);
			
			value = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, qidx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return value;
	}
	
	public int modifyAnswer(String acontent, int qidx, int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value = 0;
		
		String sql = "UPDATE ANSWER SET ACONTENT = ? WHERE QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, acontent);
			pstmt.setInt(2, qidx);
			pstmt.setInt(3, midx);
			
			value = pstmt.executeUpdate();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return value;
	}
	
	public AnswerVo getAnswerListOne(int qidx, int midx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		AnswerVo av = null;
		
		String sql = "SELECT * FROM ANSWER WHERE ADELYN = 'N' AND QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qidx);
			pstmt.setInt(2, midx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				av = new AnswerVo();
				
				av.setAidx(rs.getInt("AIDX"));
				av.setQidx(rs.getInt("QIDX"));
				av.setAcontent(rs.getString("ACONTENT"));
				av.setMidx(rs.getInt("MIDX"));
				av.setAregdate(rs.getDate("AREGDATE"));
				av.setAdelyn(rs.getString("ADELYN"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return av;
	}
}
