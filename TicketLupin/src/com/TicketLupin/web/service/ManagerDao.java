package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class ManagerDao {
	private	Connection conn;
	private PreparedStatement pstmt;
	
	public ManagerDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public List<ManagerVo> getmemberAMainList(){
		
		List<ManagerVo> mlist = new ArrayList<ManagerVo>();
			String sql ="select * from(select rownum num, n.* from(select row_number() over (order by midx) no,midx, mid, mname, msignindate from member order by no desc)n)where num between 1 and 8";

		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				mv.setNo(rs.getInt("no"));
				mv.setMidx(rs.getInt("midx"));
				mv.setMid(rs.getString("mid"));
				mv.setMname(rs.getString("mname"));
				mv.setMsignindate(rs.getDate("msignindate"));
				mlist.add(mv);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return mlist;				
		
	}
	public List<ManagerVo> getPayAMainList(){
		
		List<ManagerVo> mlist = new ArrayList<ManagerVo>();
			String sql ="select * from(select rownum num, n.* from(select row_number() over (order by c_idx) as no,  c_idx, substr(c_content,0,17)as c_content, c_regdate from c_comment order by no desc)n)where num between 1 and 8";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setC_content(rs.getString("c_content"));
				mv.setC_regdate(rs.getDate("c_regdate"));
				mv.setC_idx(rs.getInt("c_idx"));
				mlist.add(mv);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return mlist;				
		
	}
	public List<ManagerVo> getCommentAMainList(){
		
		List<ManagerVo> clist = new ArrayList<ManagerVo>();
			String sql ="select * from(select rownum num, n.* from(select row_number() over (order by c_idx) as no,  c_idx, substr(c_content,0,17)as c_content, c_regdate from c_comment order by no desc)n)where num between 1 and 8";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setC_content(rs.getString("c_content"));
				mv.setC_regdate(rs.getDate("c_regdate"));
				mv.setC_idx(rs.getInt("c_idx"));
				clist.add(mv);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return clist;				 
		
	}
	public List<ManagerVo> getQnaAMainList(){
		
		List<ManagerVo> qlist = new ArrayList<ManagerVo>();
			String sql ="select * from(select rownum num, n.* from(select row_number() over (order by Qidx) no, qidx, qtype, substr(qtitle,0,16)as qtitle, qregdate from question order by no desc)n)where num between 1 and 8";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setQtitle(rs.getString("qtitle"));
				mv.setQtype(rs.getString("qtype"));
				mv.setQregdate(rs.getDate("qregdate"));
				mv.setQidx(rs.getInt("qidx"));
				qlist.add(mv);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return qlist;				 
		
	}
	public String getName(int midx){
		
		String name = "";
		
		String sql =  "select mname from member where midx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
									
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				name = rs.getString("mname");
				
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return	name;
	}

}
