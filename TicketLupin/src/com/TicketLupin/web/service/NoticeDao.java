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
	
	public int insertNotice(String ntitle, String ncontent, String ncategory) {
		
		int value = 0;
		
		String sql = "INSERT INTO NOTICE(NIDX, NTITLE, NCONTENT, MIDX, NREGDATE, NHIT, NIMAGE, NFILES, NPUB, NGOOD, NDELYN, NCATEGORY)" + 
					 "VALUES(NIDX_SEQ.NEXTVAL, ?, ?, 1, SYSTIMESTAMP, 1, 'test', 'test', 'Y', 0, 'N', ?)";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ntitle);
			pstmt.setString(2, ncontent);
			pstmt.setString(3, ncategory);
			
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
			
			ResultSet rs = pstmt.executeQuery();
			
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
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
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
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNhit(rs.getInt("NHIT"));
				nv.setNimage(rs.getString("NIMAGE"));
				nv.setNfiles(rs.getString("NFILES"));
				nv.setNpub(rs.getString("NPUB"));
				nv.setNgood(rs.getInt("NGOOD"));
				nv.setNdelyn(rs.getString("NDELYN"));
				nv.setNcategory(rs.getString("NCATEGORY"));
				
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
	
	
	public NoticeVo getNoticeListOne(int nidx) {
		
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
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNhit(rs.getInt("NHIT"));
				nv.setNimage(rs.getString("NIMAGE"));
				nv.setNfiles(rs.getString("NFILES"));
				nv.setNpub(rs.getString("NPUB"));
				nv.setNgood(rs.getInt("NGOOD"));
				nv.setNdelyn(rs.getString("NDELYN"));
				nv.setNcategory(rs.getString("NCATEGORY"));
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
	
	public NoticeVo getNoticeListOnePrev(int nidx){
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM NOTICE WHERE NDELYN = 'N' AND NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nidx-1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				nv = new NoticeVo();
				
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNhit(rs.getInt("NHIT"));
				nv.setNimage(rs.getString("NIMAGE"));
				nv.setNfiles(rs.getString("NFILES"));
				nv.setNpub(rs.getString("NPUB"));
				nv.setNgood(rs.getInt("NGOOD"));
				nv.setNdelyn(rs.getString("NDELYN"));
				nv.setNcategory(rs.getString("NCATEGORY"));
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
	
	public NoticeVo getNoticeListOneNext(int nidx){
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM NOTICE WHERE NDELYN = 'N' AND NIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nidx+1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				nv = new NoticeVo();
				
				nv.setNidx(rs.getInt("NIDX"));
				nv.setNtitle(rs.getString("NTITLE"));
				nv.setNcontent(rs.getString("NCONTENT"));
				nv.setMidx(rs.getInt("MIDX"));
				nv.setNregdate(rs.getDate("NREGDATE"));
				nv.setNhit(rs.getInt("NHIT"));
				nv.setNimage(rs.getString("NIMAGE"));
				nv.setNfiles(rs.getString("NFILES"));
				nv.setNpub(rs.getString("NPUB"));
				nv.setNgood(rs.getInt("NGOOD"));
				nv.setNdelyn(rs.getString("NDELYN"));
				nv.setNcategory(rs.getString("NCATEGORY"));
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
	
	
	public int getNoticeListCount(int page, String keyword, String ncategory){
		
		int count = 0;
		
		String sql = "SELECT COUNT(NIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NTITLE LIKE ? AND NCATEGORY LIKE ? AND NDELYN = 'N' ORDER BY NREGDATE DESC) N)";
		
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
	
	public int getNoticeListCountAll(int page){
		
		int count = 0;
		
		String sql = "SELECT COUNT(NIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE NDELYN = 'N' ORDER BY NREGDATE DESC) N)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
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
