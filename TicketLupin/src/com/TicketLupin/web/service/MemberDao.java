package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	
	public int memberLogin(String mId, String mPwd) {
		
		String sql ="select count(*) as cnt from member where mid= ? and mpwd = ?;";
		int value = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPwd);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				value = rs.getInt("cnt");
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return value; 
		
	}
	
	public int getMember(String mid) {
		
		String sql ="";
		int value =1;
		
		return value;
	}
	
	public int insertMember(String mId, String mName, String mPwd, String mAddress, String mEmail, String mPhone) {
		int exec = 0;
		
		try {
			String sql = "insert into member(midx, mid, mname, mpwd, maddress, memail, mphone)"
					+ "values(midx_seq.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				pstmt.setString(2, mName);
				pstmt.setString(3, mPwd);
				pstmt.setString(4, mAddress);
				pstmt.setString(5, mEmail);
				pstmt.setString(6, mPhone);
			exec = pstmt.executeUpdate();
			
			conn.commit();
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
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	
	
	
	
	
	public String getMemberEmail(String mId) {
		
		String sql ="select memail from member where mId = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
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
		
		return null; //데이터베이스오류
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
		
		return false; //데이터베이스오류
		
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
		
		return false; //데이터베이스오류
		
	}
	
	
	
	
	
}
