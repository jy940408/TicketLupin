package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

import util.DatabaseUtil;

public class MemberDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	
	public ArrayList memberLogin(String mId, String mPwd) {
		
		String sql = "SELECT MGRADE, MIDX FROM MEMBER WHERE MID = ? AND MPWD = ?";
		ArrayList value = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPwd);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				value.add(rs.getString("MGRADE"));
				value.add(rs.getInt("MIDX"));
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return value; 
		
	}
	
	public String getMember(String mid, String mpwd) {
		
		String sql ="select mname from member where mid=? and mpwd=?";
		String name = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpwd);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				name = rs.getString("mname");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	public int insertMember(String MID, String MNAME, String MPWD, String MADDRESS, String MEMAIL, String MPHONE) {
		int exec = 0;
		
		try {
			String sql = "insert into member(midx, MID, MNAME, MPWD,  MADDRESS, MEMAIL, MPHONE)"
					+ "values('',?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, MID);
				pstmt.setString(2, MNAME);
				pstmt.setString(3, MPWD);
				pstmt.setString(4, MADDRESS);
				pstmt.setString(5, MEMAIL);
				pstmt.setString(6, MPHONE);
			exec = pstmt.executeUpdate();
			
			//conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				if(pstmt != null) {pstmt.close(); pstmt=null;}
				if(conn != null) {conn.close(); conn=null;}
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return exec;
	}
	
	
	public String findId(String mname, String memail) throws SQLException{
		
		String mid = null;
		
		String sql = "select mid from member where mname=? and memail=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			pstmt.setString(1, mname);
			pstmt.setString(2, memail);
			
			rs = pstmt.executeQuery();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mid;
	}
	
	public String findPwd(String mid, String memail, String mname) {
		String mpwd = null;
		String sql = "select mpwd from member where mid=? and memail=? and mname=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, memail);
			pstmt.setString(3, mname);
			
			rs = pstmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mpwd;
	}
	
	
	
	
	
	public String getMemberEmail(String mId) {
		
		String sql ="select memail from member where mId = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getString(1);
			}
			
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try{if(conn != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(pstmt != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(rs != null) conn.close();}catch (Exception e) {e.printStackTrace();}
		}
		
		return null; 
	}
	
	
	public boolean getMemberEmailChecked(String mId) {
		
		String sql ="select memail from member where mId = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getBoolean(1);
			}
			
			
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try{if(conn != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(pstmt != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(rs != null) conn.close();}catch (Exception e) {e.printStackTrace();}
		}
		
		return false; 
		
	}
	
	
	public boolean setMemberEmailChecked(String mId) {
		
		String sql ="update member set mEmailChecked = true where mId=?";
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.executeUpdate();			
			return true;
			
		}catch(Exception e) {
				e.printStackTrace();
				
		}finally {
			try{if(conn != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(pstmt != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(rs != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			
		}
		
		return false; 
		
	}
	
	
	public ArrayList<MemberVo> getMemberList(){
		
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		
		String sql = "select * from member order by midx desc";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMname(rs.getString("mname"));
				mv.setMid(rs.getString("mid"));
				mv.setMsignindate(rs.getDate("MSIGNINDATE"));
				alist.add(mv);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	
	
}
