package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.TicketLupin.web.DBconn.DBconn;

public class GoodbadDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	
	public GoodbadDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}

	public int insertgoodbad(int midx, int c_idx, String lsort, int origin_c_idx ) {
		int value= 0;
		String sql="INSERT INTO C_LIKE(LIDX,MIDX,C_IDX,ORIGIN_C_IDX,LSORT) VALUES(LIDX_SEQ.NEXTVAL,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			pstmt.setInt(3, origin_c_idx);
			pstmt.setString(4, lsort);
			value = pstmt.executeUpdate();
			
			
		}catch(Exception e){
			
		e.printStackTrace();
		
		}

		return value;
	}
	
	public int checkGood(int midx, int c_idx, int origin_c_idx) {
		int value= 0;

		String sql="SELECT COUNT(*) cnt FROM C_LIKE WHERE MIDX = ? AND C_IDX= ? AND ORIGIN_C_IDX= ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			pstmt.setInt(3, origin_c_idx);		
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			value = rs.getInt("cnt");
			
			
		}catch(Exception e){
			
		e.printStackTrace();
		
		}

		return value;

	}
}
	

	

