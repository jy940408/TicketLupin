package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class FaqDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public FaqDao(){
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();		
	}
	
	public int insertFaq(String ftitle, String ftype, String fcontent) {
		
		int value = 0;
		
		String sql = "INSERT INTO FAQ(FIDX, FTITLE, FTYPE, FCONTENT, MIDX, FREGDATE, FDELYN)"
					+"VALUES(FIDX_SEQ.NEXTVAL, ?, ?, ?, 1, SYSTIMESTAMP, 'N')";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ftitle);
			pstmt.setString(2, ftype);
			pstmt.setString(3, fcontent);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;	
	}
	
	public int modifyFaq(String ftitle, String ftype, String fcontent, int fidx) {
		
		int value = 0;
		
		String sql = "UPDATE FAQ SET FTITLE = ?, FTYPE = ?, FCONTENT = ? WHERE FIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ftitle);
			pstmt.setString(2, ftype);
			pstmt.setString(3, fcontent);
			pstmt.setInt(4, fidx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {

			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public int deleteFaq(int fidx) {
		
		int value = 0;
		
		String sql = "UPDATE FAQ SET FDELYN = 'Y' WHERE FIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fidx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public List<FaqVo> getFaqList(int page, String type, String keyword){
		
		List<FaqVo> list = new ArrayList<FaqVo>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM FAQ WHERE FTITLE LIKE ? AND FTYPE LIKE ? AND FDELYN = 'N' ORDER BY FREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+type+"%");
			pstmt.setInt(3, 1+(page-1)*10);
			pstmt.setInt(4, page*10);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				FaqVo fv = new FaqVo();
				
				fv.setNum(rs.getInt("NUM"));
				fv.setFidx(rs.getInt("FIDX"));
				fv.setFtitle(rs.getString("FTITLE"));
				fv.setFtype(rs.getString("FTYPE"));
				fv.setFcontent(rs.getString("FCONTENT"));
				fv.setMidx(rs.getInt("MIDX"));
				fv.setFregdate(rs.getDate("FREGDATE"));
				fv.setFdelyn(rs.getString("FDELYN"));
				
				list.add(fv);
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return list;	
	}
	
	public FaqVo getFaqListOne(int fidx){
		
		FaqVo fv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM FAQ WHERE FDELYN = 'N' AND FIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fidx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				fv = new FaqVo();
				
				fv.setFidx(rs.getInt("FIDX"));
				fv.setFtitle(rs.getString("FTITLE"));
				fv.setFtype(rs.getString("FTYPE"));
				fv.setFcontent(rs.getString("FCONTENT"));
				fv.setMidx(rs.getInt("MIDX"));
				fv.setFregdate(rs.getDate("FREGDATE"));
				fv.setFdelyn(rs.getString("FDELYN"));
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return fv;
	}
	
	public int getFaqListCount(int page, String type, String keyword){
		
		int count = 0;
		
		String sql = "SELECT COUNT(FIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM FAQ WHERE FTITLE LIKE ? AND FTYPE LIKE ? AND FDELYN = 'N' ORDER BY FREGDATE DESC) N)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+type+"%");
			
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
