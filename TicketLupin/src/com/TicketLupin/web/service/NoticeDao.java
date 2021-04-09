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
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public NoticeDao() {
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public int insertNotice(String ntitle, String ncontent, String ncategory, int midx) {
		
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
			
		} catch (SQLException e) {			
			
			e.printStackTrace();
			
		}finally {

			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}

		return value;
	}
	
	public int noticeModify(int nidx, String ntitle, String ncontent, String ncategory) {
		
		int value = 0;

		String sql = "UPDATE NOTICE SET NTITLE = ?, NCONTENT = ?, NCATEGORY = ? WHERE NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ntitle);
			pstmt.setString(2, ncontent);
			pstmt.setString(3, ncategory);
			pstmt.setInt(4, nidx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
	
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public int noticeDelete(int nidx) {
		
		int value = 0;
		
		String sql = "UPDATE NOTICE SET NDELYN = 'Y' WHERE NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nidx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public List<NoticeVo> getNoticeList(int page, String keyword, String ncategory){
		
		List<NoticeVo> list = new ArrayList<NoticeVo>();
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM NOTICE B,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A WHERE NUM BETWEEN ? AND ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+ncategory+"%");
			pstmt.setInt(3, 1+(page-1)*10);
			pstmt.setInt(4, page*10);
			
			ResultSet rs = pstmt.executeQuery();
		
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
		
		}catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return list;
	}
	
	
	public NoticeVo getNoticeOne(int nidx) {
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return nv;
	}	
	
	public int getNoticeListCount(int page, String keyword, String ncategory){
		
		int count = 0;
		
		//String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N)";
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, N.* FROM NOTICE N,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+ncategory+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count = rs.getInt("COUNT");
			}
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return count;
	}	
	
	public NoticeVo getNoticeListOne(String keyword, String searchType, int num) {
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N) WHERE NUM = ?";
		
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return nv;
	}
	
	public NoticeVo getNoticeListOnePrev(String keyword, String searchType, int num){
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N) WHERE NUM = ?";
		
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
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return nv;
	}
	
	public NoticeVo getNoticeListOneNext(String keyword, String searchType, int num){
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N) WHERE NUM = ?";
		
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
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return nv;
	}
	
	public int getNoticeListCountAll(String keyword, String searchType){
		
		int count = 0;
		
		//String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N)";
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, N.* FROM NOTICE N,(SELECT @ROWNUM := 0) TMP WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) A";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+searchType+"%");
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
							
			}
		
		}catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return count;
	}
	
}
