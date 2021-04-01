package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class CommentADao {
	private	Connection conn;
	private PreparedStatement pstmt;
	
	public CommentADao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	public List<CommentAVo> getCommentAList(String setting,String query,int page ){
		
		List<CommentAVo> clist = new ArrayList<CommentAVo>();
			String sql =" select * from (select rownum num, n.* from (select a.c_idx, a.origin_c_idx, a.sidx, a.eidx, a. midx, substr(c_content,0,27)as c_content,"+
						" a.c_regdate, a.c_delyn, a.c_depth, a.c_sort, b.mname, b.mid,c.stitle"+
						" from c_comment a , member b, show c where a.midx=b.midx(+) and a.sidx=c.sidx(+) and a.c_delyn='N' and "
						+setting+" like ? order by c_idx desc)n)where num between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CommentAVo cav = new CommentAVo();
				
				cav.setC_idx(rs.getInt("c_idx"));
				cav.setOrigin_c_idx(rs.getInt("origin_c_idx"));
				cav.setSidx(rs.getInt("sidx"));
				cav.setEidx(rs.getInt("eidx"));
				cav.setMidx(rs.getInt("midx"));
				cav.setC_content(rs.getString("c_content"));
				cav.setC_regdate(rs.getDate("c_regdate"));
				cav.setC_delyn(rs.getString("c_delyn"));
				cav.setC_depth(rs.getInt("c_depth"));
				cav.setC_sort(rs.getString("c_sort"));
				cav.setMname(rs.getString("mname"));
				cav.setMid(rs.getString("mid"));
				cav.setNum(rs.getInt("num"));
				cav.setStitle(rs.getString("stitle"));
			
				
				clist.add(cav);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return clist;				
		
	}
	
	
	public int getCommentListCount(int page, String setting, String query){
		
		int count = 0;
		
		String sql =  "select count(c_idx) count from (select * from c_comment where c_depth=0 and c_delyn='Y')";
		try {
			pstmt = conn.prepareStatement(sql);
									
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
				
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return	count;
	}

	public int deleteComment(int c_idx) {
		int value= 0;
		String sql ="update c_comment set c_delyn = 'y' where c_idx = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_idx);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return value;
	}
	public int deleteCommentall(String keyword) {
		int value= 0;
		String sql ="update c_comment set c_delyn = 'y' where c_idx = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return value;
	}
		
	

	
	public int insertReport(int midx,int c_idx,String sort,String etcval) {
		int value = 0;
		String sql ="INSERT INTO C_REPORT(CRIDX,MIDX,C_IDX,CRSORT,CRCONTENT,CRREGDATE) VALUES(CRIDX_SEQ.NEXTVAL,?,?,?,?,SYSDATE)";
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			pstmt.setString(3,sort);
			pstmt.setString(4,etcval);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}
	
	
		

}
