package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TicketLupin.web.DBconn.DBconn;

public class AnswerDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AnswerDao(){
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();		
	}
	
	public int insertAnswer(String acontent, int qidx, int midx) {
		
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
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public int modifyAnswer(String acontent, int qidx, int midx) {
		
		int value = 0;
		
		String sql = "UPDATE ANSWER SET ACONTENT = ? WHERE QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, acontent);
			pstmt.setInt(2, qidx);
			pstmt.setInt(3, midx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public AnswerVo getAnswerListOne(int qidx, int midx){
		
		AnswerVo av = null;
		ResultSet rs = null;
		
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
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return av;
	}
}
