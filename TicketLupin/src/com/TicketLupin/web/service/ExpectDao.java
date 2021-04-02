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

	private	Connection conn;
	private PreparedStatement pstmt;
	
	public ExpectDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public List<ExpectVo> getExpectList(String setting, String setting2, String setting3, int page,int sidx_){
		
		List<ExpectVo> elist = new ArrayList<ExpectVo>();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT AA.C_IDX , AA.ORIGIN_C_IDX , AA.SIDX , AA.MIDX , AA.C_CONTENT , AA.C_REGDATE , AA.C_DELYN , AA.MID , AA.C_DEPTH ,"+
				"COUNT(DISTINCT BB.MIDX) AS ORIGIN_GOOD, COUNT(DISTINCT CC.MIDX) AS ORIGIN_BAD, COUNT(DISTINCT DD.C_IDX) AS CNT, COUNT(DISTINCT EE.MIDX) AS GOOD, COUNT(DISTINCT FF.MIDX) AS BAD" +
				" FROM (SELECT B.MIDX , ORIGIN_C_IDX , SIDX , C_IDX , MID , C_CONTENT , C_REGDATE , C_DELYN , C_DEPTH FROM MEMBER A , C_COMMENT B "+
				" WHERE A.MIDX = B.MIDX(+) AND C_DELYN = 'N' AND C_SORT=? and sidx=? ) AA,(SELECT * FROM C_LIKE WHERE LSORT='G') BB,(SELECT * FROM C_LIKE WHERE LSORT='B') CC,"+
				" (SELECT * FROM C_COMMENT WHERE C_DEPTH>0 AND C_DELYN='N') DD , (SELECT * FROM C_LIKE WHERE LSORT='G') EE , (SELECT * FROM C_LIKE WHERE LSORT='B') FF"+
				" WHERE AA.ORIGIN_C_IDX = BB.ORIGIN_C_IDX(+) AND AA.ORIGIN_C_IDX = CC.ORIGIN_C_IDX(+) AND"+
				" AA.ORIGIN_C_IDX = DD.ORIGIN_C_IDX(+) AND AA.C_IDX = EE.C_IDX(+) AND AA.C_IDX = FF.C_IDX(+) "+ 
				" GROUP BY AA.C_IDX , AA.SIDX , AA.ORIGIN_C_IDX , AA.MIDX , AA.C_CONTENT , AA.C_REGDATE , AA.C_DELYN , AA.MID , AA.C_DEPTH "+
				" ORDER BY "+setting+" ORIGIN_C_IDX DESC,  C_DEPTH  ASC,"+setting2+" C_IDX DESC)N ) WHERE NUM BETWEEN ? AND ?";
			try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setting3);
			pstmt.setInt(2, sidx_);
			pstmt.setInt(3, 1+(page-1)*10);
			pstmt.setInt(4, page*10);
			
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				int c_idx = rs.getInt("C_IDX");
				int sidx = rs.getInt("SIDX");
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
	
	public int getExpectListCount(String setting3,int sidx,  int p){
		
		int count = 0;
		
		String sql =  "SELECT COUNT(C_IDX) COUNT FROM (SELECT * FROM C_COMMENT WHERE C_DEPTH=0 AND C_SORT=? AND SIDX =? AND C_DELYN='N')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setting3);
			pstmt.setInt(2, sidx);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
				
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return count;
	}		

		
	public int insertExpect(int midx, String c_content, String sort ,int sidx_) {
		int value = 0;
		String sql ="INSERT INTO C_COMMENT(C_IDX,ORIGIN_C_IDX,SIDX,EIDX,MIDX,C_CONTENT,C_REGDATE,C_DELYN,C_DEPTH,C_SORT)"+
					"VALUES(C_IDX_SEQ.NEXTVAL,ORIGIN_C_IDX_SEQ.NEXTVAL,?,0,?,?,SYSDATE,'N',0,?)";
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx_);
			pstmt.setInt(2, midx);
			pstmt.setString(3,c_content);
			pstmt.setString(4,sort);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}
	
	
	
	
	public int deleteExpect(int idx, String setting) {
		int value = 0;
		String sql ="UPDATE C_COMMENT SET C_DELYN = 'Y' WHERE "+setting+"= ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return value;
	}
	
	public int insertExpectComment(int midx, int origin_c_idx, String c_content,String sort,int sidx_) {
		int value = 0;
		String sql ="INSERT INTO C_COMMENT(C_IDX,ORIGIN_C_IDX,SIDX,EIDX,MIDX,C_CONTENT,C_REGDATE,C_DELYN,C_DEPTH,C_SORT)"+
				"VALUES(C_IDX_SEQ.NEXTVAL,?,?,0,?,?,SYSDATE,'N',1,?)";
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, origin_c_idx);
			pstmt.setInt(2, sidx_);
			pstmt.setInt(3, midx);
			pstmt.setString(4,c_content);
			pstmt.setString(5,sort);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}

	
	public int Expectupdate(int midx, int c_idx, String c_content) {
		int value = 0;
		String sql ="UPDATE C_COMMENT SET C_CONTENT=?,C_REGDATE=SYSDATE WHERE C_IDX=? AND MIDX=?";
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,c_content);
			pstmt.setInt(2, c_idx);
			pstmt.setInt(3, midx);
			value = pstmt.executeUpdate();	
			
			
			/* conn.commit(); */
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return value;
	}


}

	