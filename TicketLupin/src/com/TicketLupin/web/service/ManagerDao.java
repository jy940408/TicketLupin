	package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class ManagerDao {
	
	public List<ManagerVo> getmemberAMainList(){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ManagerVo> mlist = new ArrayList<ManagerVo>();
		String sql ="select * from(select @ROWNUM := @ROWNUM + 1 AS NUM, AA.* FROM ( "+
					"select @ROWNUM := @ROWNUM + 1 AS NO, midx, mid, mname,memail, date_format( msignindate,'%m-%d')as c_date from member A,(SELECT @ROWNUM := 0)B order by MIDX)AA, "+
					"(SELECT @ROWNUM := 0) BB ORDER BY NO DESC)a limit 0,7 ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				mv.setNo(rs.getInt("no"));
				mv.setMidx(rs.getInt("midx"));
				mv.setMid(rs.getString("mid"));
				mv.setMname(rs.getString("mname"));
				mv.setC_date(rs.getString("c_date"));
				mv.setMemail(rs.getString("memail"));
				mlist.add(mv);
				}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return mlist;				
		
	}
	public List<ManagerVo> getPayAMainList(){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ManagerVo> plist = new ArrayList<ManagerVo>();
		String sql = "select * from(select @ROWNUM := @ROWNUM + 1 AS NUM, AA.* FROM ( "+
				"select @ROWNUM := @ROWNUM + 1 AS NO, c.stitle, b.mid, date_format( a.riregdate,'%m-%d')as c_date,a.ripayment, "+
				"a.riidx from reservationidx a LEFT JOIN member b ON a.midx= b.midx LEFT JOIN show1 c ON a.sidx =c.sidx ,(SELECT @ROWNUM := 0)D order by a.riidx"+
				")AA,(SELECT @ROWNUM := 0) BB ORDER BY NO DESC)a limit 0,7" ; 
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		 
		
			while(rs.next()) {
			
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setStitle(rs.getString("stitle"));
				mv.setMid(rs.getString("mid"));
				mv.setC_date(rs.getString("c_date"));
				mv.setRiidx(rs.getInt("riidx"));
				mv.setRipayment(rs.getInt("ripayment"));
				plist.add(mv);
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return plist;				
		
	}
	public List<ManagerVo> getCommentAMainList(){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ManagerVo> clist = new ArrayList<ManagerVo>();
		String sql = "select * from(select @ROWNUM := @ROWNUM + 1 AS NUM, AA.* FROM ( " + 
				"select @ROWNUM := @ROWNUM + 1 AS NO,  c_idx,  c_content, date_format(c_regdate,'%m-%d')as c_date from c_comment order by c_idx " + 
				")AA,(SELECT @ROWNUM := 0) BB ORDER BY NO DESC)a limit 0,7 ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setC_content(rs.getString("c_content"));
				mv.setC_date(rs.getString("c_date"));
				mv.setC_idx(rs.getInt("c_idx"));
				clist.add(mv);
				}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return clist;				 
		
	}
	public List<ManagerVo> getQnaAMainList(){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ManagerVo> qlist = new ArrayList<ManagerVo>();
			String sql ="select * from(select @ROWNUM := @ROWNUM + 1 AS NUM, AA.* FROM ( " + 
						"select @ROWNUM := @ROWNUM + 1 AS NO, a.qidx, a.qtype, A.qtitle,count(distinct b.qidx) as cnt " + 
						", date_format(a.qregdate,'%m-%d')as q_date from question a LEFT JOIN answer b ON a.qidx = b.qidx,(SELECT @ROWNUM := 0) C " + 
						"group by  a.qidx, a.qtype,qtitle, a.qregdate order by a.Qidx " + 
						")AA,(SELECT @ROWNUM := 0) BB ORDER BY NO DESC)a where num between 1 and 8";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				ManagerVo mv = new ManagerVo();
				
				mv.setNo(rs.getInt("no"));
				mv.setQtitle(rs.getString("qtitle"));
				mv.setQtype(rs.getString("qtype"));
				mv.setQ_date(rs.getString("q_date"));
				mv.setQidx(rs.getInt("qidx"));
				qlist.add(mv);
				}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return qlist;				 
		
	}
	public String getName(int midx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = "";
		String sql =  "select mname from member where midx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				name = rs.getString("mname");
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return	name;
	}
	public int UnansweredCount(){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql ="select count(*) as count from(select qtitle, a.qidx, a.qregdate ,count(distinct b.qidx) as cnt from " + 
					"question a LEFT JOIN answer b ON a.qidx = b.qidx group by qtitle, a.qidx, a.qregdate)n where cnt=0"; 
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count");
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return	count;
	}
	
	public int ReportCount (){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;	
		String sql ="select count(*) as count from(select qtitle, a.qidx, a.qregdate ,count(distinct b.qidx) as cnt from question a LEFT JOIN " + 
					"answer b ON a.qidx = b.qidx group by qtitle, a.qidx, a.qregdate)n where cnt=0"; 
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count");
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return	count;
	}


}
