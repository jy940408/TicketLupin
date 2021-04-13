package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TicketLupin.web.DBconn.DBconn;

public class DibsDao {

	public int insertDibs(int sidx, int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value = 0;
		String sql = "INSERT INTO DIBS(SIDX, MIDX) VALUES(?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
		
		
	}
	
	public int getDibsCheck(int sidx, int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value = 0;
		String sql = "SELECT COUNT(DIDX) DIDX FROM DIBS WHERE SIDX = ? AND MIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value = rs.getInt("DIDX");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
		
	}
	
	public int deleteDibs(int sidx, int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value = 0;
		String sql = "DELETE FROM DIBS WHERE SIDX = ? AND MIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}
	
	public ArrayList<DibsListVo> getDibsList(int midx_, int page){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<DibsListVo> list = new ArrayList<>();
		String sql = "SELECT D.* FROM "
				+ "(SELECT SHOW1.SIDX, SHOW1.STITLE, SHOW1.SOPENDATE, SHOW1.SENDDATE, DIBS.MIDX, DIBS.DIDX "
				+ "FROM SHOW1 INNER JOIN DIBS "
				+ "ON SHOW1.SIDX = DIBS.SIDX WHERE DIBS.MIDX= ?) D "
				+ "ORDER BY DIDX DESC LIMIT ?,10";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx_);
			pstmt.setInt(2, (page-1)*10);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DibsListVo dlv = new DibsListVo();
				
				dlv.setSidx(rs.getInt("SIDX"));
				dlv.setStitle(rs.getString("STITLE"));
				dlv.setSopendate(rs.getDate("SOPENDATE"));
				dlv.setSenddate(rs.getDate("SENDDATE"));
				dlv.setMidx(rs.getInt("MIDX"));
				
				list.add(dlv);
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getDibsListCount(int midx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String sql = "SELECT COUNT(*) COUNT FROM "
				+ "(SELECT SHOW1.SIDX, SHOW1.STITLE, SHOW1.SOPENDATE, SHOW1.SENDDATE, DIBS.MIDX FROM "
						+ "SHOW1 INNER JOIN DIBS ON SHOW1.SIDX = DIBS.SIDX "
						+ "WHERE SHOW1.SDELYN = 'N' AND DIBS.MIDX = ?) A";

		
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
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}
}
