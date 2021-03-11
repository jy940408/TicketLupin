package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public int getDibsCheck(int sidx, int midx) {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
		
	}
	
	public int deleteDibs(int sidx, int midx) {
		
		int value = 0;
		String sql = "DELETE FROM DIBS WHERE SIDX = ? AND MIDX = ?";
		
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
	
	public ArrayList<DibsListVo> getDibsList(int midx_, int page){
		ArrayList<DibsListVo> list = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, D.* FROM (SELECT SHOW.SIDX, SHOW.STITLE, SHOW.SOPENDATE, SHOW.SENDDATE, DIBS.MIDX FROM SHOW INNER JOIN DIBS ON SHOW.SIDX = DIBS.SIDX WHERE DIBS.MIDX = ?) D) WHERE NUM BETWEEN ? AND ? ORDER BY NUM DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx_);
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("NUM");
				int sidx = rs.getInt("SIDX");
				String stitle = rs.getString("STITLE");
				Date sopendate = rs.getDate("SOPENDATE");
				Date senddate = rs.getDate("SENDDATE");
				int midx = rs.getInt("MIDX");
				
				DibsListVo dlv = new DibsListVo(num, sidx, stitle, sopendate, senddate, midx);
				
				list.add(dlv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getDibsListCount(int midx){
		
		int count = 0;
		
		String sql = "SELECT COUNT(*) COUNT FROM (SELECT SHOW.SIDX, SHOW.STITLE, SHOW.SOPENDATE, SHOW.SENDDATE, DIBS.MIDX FROM SHOW INNER JOIN DIBS ON SHOW.SIDX = DIBS.SIDX WHERE DIBS.MIDX = ?)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
							
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}
}