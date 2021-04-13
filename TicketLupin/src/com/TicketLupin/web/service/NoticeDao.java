package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class NoticeDao {
	
	public int insertNotice(String ntitle, String ncontent, String ncategory, int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql = "INSERT INTO NOTICE(NTITLE, NCONTENT, NCATEGORY, MIDX, NREGDATE, NDELYN)" + 
					 "VALUES(?, ?, ?, ?, NOW(), 'N')";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ntitle);
			pstmt.setString(2, ncontent);
			pstmt.setString(3, ncategory);
			pstmt.setInt(4, midx);
			
			value = pstmt.executeUpdate();	
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {			
			
			e.printStackTrace();
			
		}

		return value;
	}
	
	public int noticeModify(int nidx, String ntitle, String ncontent, String ncategory) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql = "UPDATE NOTICE SET NTITLE = ?, NCONTENT = ?, NCATEGORY = ? WHERE NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ntitle);
			pstmt.setString(2, ncontent);
			pstmt.setString(3, ncategory);
			pstmt.setInt(4, nidx);
			
			value = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return value;
	}
	
	public int noticeDelete(int nidx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql = "UPDATE NOTICE SET NDELYN = 'Y' WHERE NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nidx);
			value = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return value;
	}
	
	public List<NoticeVo> getNoticeList(int page, String keyword, String ncategory){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<NoticeVo> list = new ArrayList<NoticeVo>();
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM NOTICE B,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A WHERE NUM BETWEEN ? AND ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+ncategory+"%");
			pstmt.setInt(3, 1+(page-1)*10);
			pstmt.setInt(4, page*10);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				NoticeVo nv = new NoticeVo();
				
				nv.setNum(rs.getInt("NUM"));
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setNcategory(rs.getString("NCATEGORY"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNdelyn(rs.getString("NDELYN"));
				
				System.out.println(nv);
				
				list.add(nv);
				
			}
		
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return list;
	}
	
	
	public NoticeVo getNoticeOne(int nidx) {
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		NoticeVo nv = null;
		String sql = "SELECT * FROM NOTICE WHERE NDELYN = 'N' AND NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nidx);
			
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				
				nv = new NoticeVo();
				
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setNcategory(rs.getString("NCATEGORY"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNdelyn(rs.getString("NDELYN"));			
			}					
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return nv;
	}	
	
	public int getNoticeListCount(int page, String keyword, String ncategory){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, N.* FROM NOTICE N,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+ncategory+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count = rs.getInt("COUNT");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return count;
	}	
	
	public NoticeVo getNoticeListOne(String keyword, String searchType, int num) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		NoticeVo nv = null;
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM NOTICE B,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A WHERE NUM = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+searchType+"%");
			pstmt.setInt(3, num);
			
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				
				nv = new NoticeVo();
				
				nv.setNum(rs.getInt("NUM"));
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setNcategory(rs.getString("NCATEGORY"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNdelyn(rs.getString("NDELYN"));
			}					
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return nv;
	}
	
	public NoticeVo getNoticeListOnePrev(String keyword, String searchType, int num){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		NoticeVo nv = null;
		String sql = "SELECT A.* FROM(SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM NOTICE B,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A WHERE NUM = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+searchType+"%");
			pstmt.setInt(3, num+1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				nv = new NoticeVo();
				
				nv.setNum(rs.getInt("NUM"));
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setNcategory(rs.getString("NCATEGORY"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNdelyn(rs.getString("NDELYN"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return nv;
	}
	
	public NoticeVo getNoticeListOneNext(String keyword, String searchType, int num){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		NoticeVo nv = null;
		String sql = "SELECT A.* FROM(SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM NOTICE B,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A WHERE NUM = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+searchType+"%");
			pstmt.setInt(3, num-1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				nv = new NoticeVo();
				
				nv.setNum(rs.getInt("NUM"));
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setNcategory(rs.getString("NCATEGORY"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNdelyn(rs.getString("NDELYN"));			
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		return nv;
	}
	
	public int getNoticeListCountAll(String keyword, String searchType){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, N.* FROM NOTICE N,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+searchType+"%");
			
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
