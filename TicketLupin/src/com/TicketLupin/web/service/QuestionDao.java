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
		
		String sql = "INSERT INTO QUESTION(QTITLE, QCONTENT, QTYPE, QSTATE, MIDX, QREGDATE, QDELYN)"
					+"VALUES(?, ?, ?, '대기', ?, NOW(), 'N')";
		
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
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM QUESTION B, (SELECT @ROWNUM := 0) TMP WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A WHERE NUM BETWEEN ? AND ?";
		
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
				
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
		
		//String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM QUESTION B,(SELECT @ROWNUM := 0) TMP WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A";
		
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
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
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM = ?";
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM QUESTION B, (SELECT @ROWNUM := 0) TMP WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A WHERE NUM = ?";
		
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
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
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM = ?";
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM QUESTION B, (SELECT @ROWNUM := 0) TMP WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A WHERE NUM = ?";
		
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));			
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
		
		//String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N) WHERE NUM = ?";
		
		String sql = "SELECT A.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM QUESTION B, (SELECT @ROWNUM := 0) TMP WHERE QSTATE LIKE ? AND MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A WHERE NUM = ?";
		
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
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
		
		//String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION WHERE MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) N)";
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM QUESTION B,(SELECT @ROWNUM := 0) TMP WHERE MIDX = ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A";
		
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
	
	// 관리자
	
	public List<QuestionVo> getQuestionList2(String keyword, String searchType, String qtype, String qstate, int page){
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		String sql = "";

		//문의 대기, 문의 완료를 먼저 정렬하고, 가장 먼저 문의한 순으로 정렬 
		
		if(searchType.equals("제목")){
			
			sql = "SELECT @ROWNUM := @ROWNUM + 1 NUM, A.* FROM (SELECT QUESTION.*, MEMBER.MID, MEMBER.MNAME, MEMBER.MIDX MMIDX FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE QTITLE LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY FIELD(QSTATE, '완료', '대기') DESC, QREGDATE ASC) A WHERE (@ROWNUM := 0) = 0 ORDER BY NUM LIMIT ?,?";
			
		}else if(searchType.equals("성함")) {
			
			sql = "SELECT @ROWNUM := @ROWNUM + 1 NUM, A.* FROM (SELECT QUESTION.*, MEMBER.MID, MEMBER.MNAME, MEMBER.MIDX MMIDX FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE MNAME LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY FIELD(QSTATE, '완료', '대기') DESC, QREGDATE ASC) A WHERE (@ROWNUM := 0) = 0 ORDER BY NUM LIMIT ?,?";
			
		}else{
			
			sql = "SELECT @ROWNUM := @ROWNUM + 1 NUM, A.* FROM (SELECT QUESTION.*, MEMBER.MID, MEMBER.MNAME, MEMBER.MIDX MMIDX FROM QUESTION INNER JOIN MEMBER ON QUESTION.MIDX = MEMBER.MIDX WHERE MID LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY FIELD(QSTATE, '완료', '대기') DESC, QREGDATE ASC) A WHERE (@ROWNUM := 0) = 0 ORDER BY NUM LIMIT ?,?";
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+qtype+"%");
			pstmt.setString(3, "%"+qstate+"%");
			pstmt.setInt(4, (page-1)*10);
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
				
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
			
			sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.*, C.MIDX AS CMIDX, C.MID, C.MNAME, C.MPWD FROM QUESTION AS B INNER JOIN MEMBER AS C ON B.MIDX = C.MIDX, (SELECT @ROWNUM:= 0) AS D WHERE (QTITLE || MID || MNAME) LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A";
			
		}else if(searchType.equals("아이디")) {
			
			sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.*, C.MIDX AS CMIDX, C.MID, C.MNAME, C.MPWD FROM QUESTION AS B INNER JOIN MEMBER AS C ON B.MIDX = C.MIDX, (SELECT @ROWNUM:= 0) AS D WHERE MID LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A";
			
		}else if(searchType.equals("성함")) {
			
			sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.*, C.MIDX AS CMIDX, C.MID, C.MNAME, C.MPWD FROM QUESTION AS B INNER JOIN MEMBER AS C ON B.MIDX = C.MIDX, (SELECT @ROWNUM:= 0) AS D WHERE MNAME LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A";
			
		}else{
			
			sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.*, C.MIDX AS CMIDX, C.MID, C.MNAME, C.MPWD FROM QUESTION AS B INNER JOIN MEMBER AS C ON B.MIDX = C.MIDX, (SELECT @ROWNUM:= 0) AS D WHERE QTITLE LIKE ? AND QTYPE LIKE ? AND QSTATE LIKE ? AND QDELYN = 'N' ORDER BY QREGDATE DESC) A";
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
		
		String sql = "SELECT COUNT(NUM) COUNT FROM (SELECT (@ROWNUM := @ROWNUM + 1) AS NUM, B.*, C.MIDX AS CMIDX, C.MID, C.MNAME, C.MPWD FROM QUESTION AS B INNER JOIN MEMBER AS C ON B.MIDX = C.MIDX, (SELECT @ROWNUM:= 0) AS D WHERE QDELYN = 'N' ORDER BY QREGDATE DESC) A";
		
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
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
				qv.setQstate(rs.getString("QSTATE"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQdelyn(rs.getString("QDELYN"));
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
