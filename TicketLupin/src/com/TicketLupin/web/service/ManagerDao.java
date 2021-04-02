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
		
		List<ManagerVo> plist = new ArrayList<ManagerVo>();
			String sql = " select * from(select rownum num, n.* from(select row_number() over (order by a.riidx) no, c.stitle, b.mid, a.riregdate, "+
						" a.riidx from reservationidx a, member b, show c where a.midx= b.midx(+) and a.sidx =c.sidx(+) order by no desc)n)where num between 1 and 8 ";
			try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setStitle(rs.getString("stitle"));
				mv.setMid(rs.getString("mid"));
				mv.setRiregdate(rs.getDate("riregdate"));
				mv.setRiidx(rs.getInt("riidx"));
				plist.add(mv);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return plist;				
		
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
			String sql =    "	select * from(select rownum num, n.* from (select row_number() over (order by a.Qidx) "+
							"	no, a.qidx, a.qtype, substr(a.qtitle,0,16) as qtitle,count(distinct b.qidx) as cnt "+
							"	, a.qregdate from question a, answer b where a.qidx = b.qidx(+) "+
							"	group by  a.qidx, a.qtype,qtitle, a.qregdate order by no desc)n)where cnt = 0 and  num between 1 and 8 ";
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
	public int getCount(){
		
		int count = 0;
		
		String sql =  "select count (*)from(select qtitle, a.qidx, a.qregdate ,count(distinct b.qidx) as cnt from question a, answer b where a.qidx = b.qidx(+) \r\n" + 
					" group by qtitle, a.qidx, a.qregdate)n where cnt=0";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count");
				
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return	count;
	}

}
