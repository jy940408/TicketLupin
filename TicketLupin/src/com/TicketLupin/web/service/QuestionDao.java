package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class QuestionDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public QuestionDao(){
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();		
	}
	
	public int insertQuestion(String qtitle, String qcontent, String qtype, int midx) {
		
		int value = 0;
		
		String sql = "INSERT INTO QUESTION(QIDX, QTITLE, QCONTENT, QTYPE, MIDX, QREGDATE, QHIT, QIMAGE, QFILES, QPUB, QDELYN, QSTATE)"
					+"VALUES(QIDX_SEQ.NEXTVAL, ?, ?, ?, ?, SYSTIMESTAMP, 0, 'test', 'test', 'Y', 'N', '���')";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qtitle);
			pstmt.setString(2, qcontent);
			pstmt.setString(3, qtype);
			pstmt.setInt(4, midx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {

			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public int modifyQuestion(String qtitle, String qcontent, String qtype, int qidx, int midx) {
		
		int value = 0;
		
		String sql = "UPDATE QUESTION SET QTITLE = ?, QCONTENT = ?, QTYPE = ? WHERE QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qtitle);
			pstmt.setString(2, qcontent);
			pstmt.setString(3, qtype);
			pstmt.setInt(4, qidx);
			pstmt.setInt(5, midx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public int deleteQuestion(int qidx, int midx) {
		
		int value = 0;
		
		String sql = "UPDATE QUESTION SET QDELYN = 'Y' WHERE QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qidx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return value;
	}
	
	public List<QuestionVo> getQuestionList(String state, int page, int midx){
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+state+"%");
			pstmt.setInt(2, midx);
			pstmt.setInt(3, 1+(page-1)*10);
			pstmt.setInt(4, page*10);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				QuestionVo qv = new QuestionVo();
				
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
				
				list.add(qv);
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
	
	public int getQuestionListCount(String state, int page, int midx){
		
		int count = 0;
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+state+"%");
			pstmt.setInt(2, midx);
			
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
	
	public QuestionVo getQuestionOne(int qidx, int midx){
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM QUESTION WHERE QDELYN = 'N' AND QIDX = ? AND MIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qidx);
			pstmt.setInt(2, midx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				qv = new QuestionVo();
				
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}

	
	
	
	
	
	
	
	public QuestionVo getQuestionListOne(String state, int num, int midx) {
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+state+"%");
			pstmt.setInt(2, midx);
			pstmt.setInt(3, num);
			
			
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				
				qv = new QuestionVo();
							
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}					
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}
	
	public QuestionVo getQuestionListOnePrev(String state, int num, int midx){
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+state+"%");
			pstmt.setInt(2, midx);
			pstmt.setInt(3, num+1);
			
			
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				
				qv = new QuestionVo();
							
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}					
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}
	
	public QuestionVo getQuestionListOneNext(String state, int num, int midx){
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+state+"%");
			pstmt.setInt(2, midx);
			pstmt.setInt(3, num-1);
		
			
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				
				qv = new QuestionVo();
							
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}					
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}
	
	public int getQuestionListCountAll(int page, int midx){
		
		int count = 0;
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			
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
	
	// ������
	
	public List<QuestionVo> getQuestionList2(String keyword, String searchType, String qtype, String qstate, int page){
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		String sql = "";
		
		if(searchType.equals("")) {
			
			sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE (QTITLE || MID || MNAME) LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
			
		}else if(searchType.equals("���̵�")) {
			
			sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE MID LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
			
		}else if(searchType.equals("����")) {
			
			sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE MNAME LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
			
		}else{
			
			sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE QTITLE LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+qtype+"%");
			pstmt.setString(3, "%"+qstate+"%");
			pstmt.setInt(4, 1+(page-1)*10);
			pstmt.setInt(5, page*10);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				QuestionVo qv = new QuestionVo();
				
				qv.setMid(rs.getString("MID"));
				qv.setMname(rs.getString("MNAME"));
				qv.setNum(rs.getInt("NUM"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
				
				list.add(qv);
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
	
	public int getQuestionListCount2(String keyword, String searchType, String qtype, String qstate, int page){
		
		int count = 0;
		String sql = "";
		
		if(searchType.equals("")) {
			
			sql = "SELECT COUNT(QIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE (QTITLE || MID || MNAME) LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
			
		}else if(searchType.equals("���̵�")) {
			
			sql = "SELECT COUNT(QIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE MID LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
			
		}else if(searchType.equals("����")) {
			
			sql = "SELECT COUNT(QIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE MNAME LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
			
		}else{
			
			sql = "SELECT COUNT(QIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE QTITLE LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
		}
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+qtype+"%");
			pstmt.setString(3, "%"+qstate+"%");
			
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
	
	public int getQuestionListCountAll2(int page){
		
		int count = 0;
		
		String sql = "SELECT COUNT(QIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
		
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
	
	public QuestionVo getQuestionListOne2(int qidx, int midx){
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX AND MEMBER.MIDX = ? WHERE QIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			pstmt.setInt(2, qidx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				qv = new QuestionVo();
				
				qv.setMid(rs.getString("MID"));
				qv.setMname(rs.getString("MNAME"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}

	public QuestionVo getQuestionListOnePrev2(int qidx, int midx){
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX AND MEMBER.MIDX = ? WHERE QIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			pstmt.setInt(2, qidx-1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				qv = new QuestionVo();
				
				qv.setMid(rs.getString("MID"));
				qv.setMname(rs.getString("MNAME"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}
	
	public QuestionVo getQuestionListOneNext2(int qidx, int midx){
		
		QuestionVo qv = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX AND MEMBER.MIDX = ? WHERE QIDX = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			pstmt.setInt(2, qidx+1);		
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				qv = new QuestionVo();
				
				qv.setMid(rs.getString("MID"));
				qv.setMname(rs.getString("MNAME"));
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setQtype(rs.getString("QTYPE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				qv.setQstate(rs.getString("QSTATE"));
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (pstmt != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { rs.close(); } catch(Exception e) {}
		}
		
		return qv;
	}
}