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

	Connection conn;
	PreparedStatement pstmt;
	
	
	public QuestionDao(){
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();		
	}
	
	public int insertQuestion(String qtitle, String qcontent) {
		
		int value = 0;
		
		String sql = "INSERT INTO QUESTION(QIDX, QTITLE, QCONTENT, MIDX, QREGDATE, QHIT, QIMAGE, QFILES, QPUB, QDELYN)"
					+"VALUES('', ?, ?, 1, SYSTIMESTAMP, 0, 'test', 'test', 'N', 'Y')";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qtitle);
			pstmt.setString(2, qcontent);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public List<QuestionVo> getQuestionList(int page){
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM QUESTION ORDER BY QREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 1+(page-1)*10);
			pstmt.setInt(2, page*10);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				QuestionVo qv = new QuestionVo();
				
				qv.setQidx(rs.getInt("QIDX"));
				qv.setQtitle(rs.getString("QTITLE"));
				qv.setQcontent(rs.getString("QCONTENT"));
				qv.setMidx(rs.getInt("MIDX"));
				qv.setQregdate(rs.getDate("QREGDATE"));
				qv.setQhit(rs.getInt("QHIT"));
				qv.setQimage(rs.getString("QIMAGE"));
				qv.setQfiles(rs.getString("QFILES"));
				qv.setQpub(rs.getString("QPUB"));
				qv.setQdelyn(rs.getString("QDELYN"));
				
				list.add(qv);
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
		
}
