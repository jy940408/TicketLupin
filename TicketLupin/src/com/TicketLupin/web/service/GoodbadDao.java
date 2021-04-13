package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.TicketLupin.web.DBconn.DBconn;

public class GoodbadDao {

	public int insertgoodbad(int midx, int c_idx, String lsort, int origin_c_idx ) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value= 0;
		String sql="INSERT INTO C_LIKE(LIDX,MIDX,C_IDX,ORIGIN_C_IDX,LSORT) VALUES(LIDX_SEQ.NEXTVAL,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			pstmt.setInt(3, origin_c_idx);
			pstmt.setString(4, lsort);
			value = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			
		e.printStackTrace();
		
		}

		return value;
	}
	
	public int checkGood(int midx, int c_idx, int origin_c_idx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value= 0;
		String sql="SELECT COUNT(*) cnt FROM C_LIKE WHERE MIDX = ? AND C_IDX= ? AND ORIGIN_C_IDX= ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			pstmt.setInt(3, origin_c_idx);		
			rs = pstmt.executeQuery();
			
			rs.next();
			
			value = rs.getInt("cnt");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			
		e.printStackTrace();
		
		}

		return value;

	}
}
	

	

