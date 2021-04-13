package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class ExpectDao {

	public List<ExpectVo> getExpectList(String setting, String setting2, String setting3, int page,int sidx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ExpectVo> elist = new ArrayList<ExpectVo>();
		
		String sql = "select n.* from (SELECT (@ROWNUM:=@ROWNUM+1) as NUM, A.* FROM (SELECT aa.*, COUNT(DISTINCT BB.MIDX) AS ORIGIN_GOOD, "+
					"COUNT(DISTINCT CC.MIDX) AS ORIGIN_BAD, COUNT(DISTINCT DD.C_IDX) AS CNT, COUNT(DISTINCT EE.MIDX) AS GOOD, "+
					"COUNT(DISTINCT FF.MIDX) AS BAD FROM (SELECT b.*,MID  FROM MEMBER A left join C_COMMENT B on A.MIDX = B.MIDX "+ 
					"WHERE C_DELYN = 'N' AND C_SORT=? and sidx=?) AA left join (SELECT * FROM C_LIKE WHERE LSORT='G') BB on AA.ORIGIN_C_IDX = BB.ORIGIN_C_IDX left join "+
					"(SELECT * FROM C_LIKE WHERE LSORT='B') CC on AA.ORIGIN_C_IDX = CC.ORIGIN_C_IDX left join (SELECT * FROM C_COMMENT WHERE "+
					"C_DEPTH>0 AND C_DELYN='N') DD on AA.ORIGIN_C_IDX = DD.ORIGIN_C_IDX left join (SELECT * FROM C_LIKE WHERE LSORT='G') EE on "+
					"AA.C_IDX = EE.C_IDX left join (SELECT * FROM C_LIKE WHERE LSORT='B') FF on AA.C_IDX = FF.C_IDX GROUP BY AA.C_IDX , AA.SIDX , "+
					"AA.ORIGIN_C_IDX , AA.MIDX , AA.C_CONTENT , AA.C_REGDATE , AA.C_DELYN , AA.MID , AA.C_DEPTH ORDER BY "+setting+
					"ORIGIN_C_IDX DESC,  C_DEPTH  ASC, "+setting2+" C_IDX DESC)A, (SELECT @ROWNUM:=0) R)n limit ?,10" ;     
			try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setting3);
			pstmt.setInt(2, sidx);
			pstmt.setInt(3, (page-1)*10);
			
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				int c_idx = rs.getInt("C_IDX");
				int midx = rs.getInt("MIDX");
				String c_content = rs.getString("C_CONTENT");
				Date c_regdate = rs.getDate("C_REGDATE");
				int origin_good = rs.getInt("ORIGIN_GOOD");
				int good = rs.getInt("GOOD");
				int origin_bad = rs.getInt("ORIGIN_BAD");
				int bad = rs.getInt("BAD");
				String c_delyn = rs.getString("C_DELYN");
				String mid = rs.getString("MID");
				int cnt = rs.getInt("CNT");
				int c_depth = rs.getInt("C_DEPTH");
				int origin_c_idx = rs.getInt("ORIGIN_C_IDX");
				
				
				ExpectVo ev = new ExpectVo(c_idx,sidx, midx,c_content, c_regdate, origin_good, origin_bad, c_delyn, mid,cnt,c_depth,good,bad, origin_c_idx);
				
				elist.add(ev);
				}

			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try{
		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return elist;
	}
	
	public int getExpectListCount(String setting3 ,int sidx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(C_IDX) COUNT FROM (SELECT * FROM C_COMMENT WHERE C_DEPTH=0 AND C_SORT=? AND SIDX=? AND C_DELYN='N')a";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setting3);
			pstmt.setInt(2, sidx);
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

		
	public int insertExpect(int sidx,int midx, String c_content, String sort) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql ="INSERT INTO C_COMMENT(ORIGIN_C_IDX,SIDX,EIDX,MIDX,C_CONTENT,C_REGDATE,C_DELYN,C_DEPTH,C_SORT)"+
					"VALUES((SELECT IFNULL(MAX(A.ORIGIN_C_IDX),0)+1 FROM C_COMMENT A),?,0,?,?,NOW(),'N',0,?)";
			
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);
			pstmt.setString(3,c_content);
			pstmt.setString(4,sort);
			value = pstmt.executeUpdate();	
			
			pstmt.close();
			conn.close();
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}
	
	
	
	
	public int deleteExpect(int idx, String setting) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql ="UPDATE C_COMMENT SET C_DELYN = 'Y' WHERE "+setting+"= ?"; 
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			value = pstmt.executeUpdate();	
			
			pstmt.close();
			conn.close();
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return value;
	}
	
	public int insertExpectComment(int midx, int origin_c_idx,int sidx, String c_content,String sort) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql ="INSERT INTO C_COMMENT(ORIGIN_C_IDX,SIDX,EIDX,MIDX,C_CONTENT,C_REGDATE,C_DELYN,C_DEPTH,C_SORT)"+
					"VALUES(?,?,0,?,?,now(),'N',1,?)"; 
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, origin_c_idx);
			pstmt.setInt(2, sidx);
			pstmt.setInt(3, midx);
			pstmt.setString(4,c_content);
			pstmt.setString(5,sort);
			value = pstmt.executeUpdate();	
			
			pstmt.close();
			conn.close();
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}

	
	public int Expectupdate(int midx, int c_idx, String c_content) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql ="UPDATE C_COMMENT SET C_CONTENT=?,C_REGDATE=now() WHERE C_IDX=? AND MIDX=?";
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,c_content);
			pstmt.setInt(2, c_idx);
			pstmt.setInt(3, midx);
			value = pstmt.executeUpdate();	
			
			pstmt.close();
			conn.close();
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}


}

	