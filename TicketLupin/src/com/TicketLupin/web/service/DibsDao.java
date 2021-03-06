package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TicketLupin.web.DBconn.DBconn;

public class DibsDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DibsDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public int insertDibs(int sidx, int midx) {
		int value = 0;
		String sql = "INSERT INTO DIBS VALUES(DIBS_SEQUENCE.NEXTVAL, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
		
		
	}
	
}
