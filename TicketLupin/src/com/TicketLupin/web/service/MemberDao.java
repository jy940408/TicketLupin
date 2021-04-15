package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TicketLupin.web.DBconn.DBconn;

import domain.SearchCriteria;
import util.DatabaseUtil;

public class MemberDao {
	
	
	public ArrayList memberLogin(String mid, String mpwd) {
	      
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	      String sql = "SELECT MGRADE, MIDX, MSECESSIONYN FROM MEMBER WHERE MID = ? AND MPWD = ?";
	      ArrayList value = new ArrayList();
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mid);
	         pstmt.setString(2, mpwd);
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            value.add(rs.getString("MGRADE"));
	            value.add(rs.getInt("MIDX"));
	            value.add(rs.getString("msecessionyn"));

	         }
	         
	        rs.close();
			pstmt.close();
			conn.close();
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return value; 
	      
	   }
	
	public int LoginCnt(String mid, String mpwd) {
	      
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	      String sql = "SELECT count(*) as cnt FROM MEMBER WHERE MID = ? AND MPWD = ?";
	      int value_ = 0;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mid);
	         pstmt.setString(2, mpwd);
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	        	 value_ = rs.getInt("cnt");
	         }   
	         
	         rs.close();
			pstmt.close();
			conn.close();
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return value_; 
	      
	   }

	
	public int insertMember(String mid,  String mpwd, String mname, String maddress, String memail, String mphone, String mssn, String mbirthmonth, String mbirthday, String mpostcode, String mdetailaddress, String mextraaddress, String mgender, String msignindate) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int exec = 0;
		
		try {
			String sql = "insert into member(mid, mpwd,  mname, maddress, memail, mphone, mssn, mbirthmonth, mbirthday, mpostcode, mdetailaddress, mextraaddress, mgender, msignindate)"
					+ "values(?,?,?,?,?,?, ?,?,?,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				pstmt.setString(2, mpwd);
				pstmt.setString(3, mname);
				pstmt.setString(4, maddress);
				pstmt.setString(5, memail);
				pstmt.setString(6, mphone);
				pstmt.setString(7, mssn);
				pstmt.setString(8, mbirthmonth);
				pstmt.setString(9, mbirthday);
				pstmt.setString(10, mpostcode);
				pstmt.setString(11, mdetailaddress);
				pstmt.setString(12, mextraaddress);
				pstmt.setString(13, mgender);
			exec = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			//conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return exec;
	}
	
	
	public String findId(String mname, String memail) throws SQLException{
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String mid = null;
		String sql = "select mid from member where mname=? and memail=? and MSECESSIONYN = 'N' ";
		
		try {
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, mname);
			pstmt.setString(2, memail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mid = rs.getString("mid");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mid;
	}
	
	public String findPwd(String mid, String memail, String mname) throws SQLException {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String mpwd = null;
		String sql = "select mpwd from member where mname=? and mid=? and memail=?  and MSECESSIONYN = 'N' ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mname);
			pstmt.setString(2, mid);
			pstmt.setString(3, memail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mpwd = rs.getString("mpwd");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mpwd;
	}
	
	public int changePwd(String mpwd, String mid) throws SQLException {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql = "update member set mpwd=? where mid=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mpwd);
			pstmt.setString(2, mid);
			
			value = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return value;
		
		
	}
	
	public MemberVo getMember(String mid, String mpwd) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo mv = null;
		String sql = "select * from member where mid = ? and mpwd = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMid(rs.getString("mid"));
				mv.setMpwd(rs.getString("mpwd"));
				mv.setMpostcode(rs.getString("mpostcode"));
				mv.setMaddress(rs.getString("maddress"));
				mv.setMdetailaddress(rs.getString("mdetailaddress"));
				mv.setMextraaddress(rs.getString("mextraaddress"));
				mv.setMemail(rs.getString("memail"));
				mv.setMphone(rs.getString("mphone"));
				mv.setMname(rs.getString("mname"));
				mv.setMgrade(rs.getString("mgrade"));
				mv.setMssn(rs.getString("mssn"));
				mv.setMbirthmonth(rs.getString("mbirthmonth"));
				mv.setMbirthday(rs.getString("mbirthday"));
				mv.setMidx(rs.getInt("midx"));
			}
		
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return mv;
	}
	
	public boolean isExistId(String mid) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean b = false;
		String sql = "select mid from member where mid=? and msecessionyn = 'N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			b = rs.next();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("error : "+e);
		}
		return b;
	}
	
	
	public ArrayList<MemberVo> memberSelectAll(SearchCriteria scri){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		String sql = "select * from (select @rownum:=@rownum+1 num, A.* from "
				+ "(select * from member, (SELECT @ROWNUM:=0) TMP where mgrade='G' and msecessionyn='N' and "
				+ "(mname like ? or mid like ?) order by midx asc) "
				+ "A) B where num between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setString(2, "%"+scri.getKeyword()+"%");
			pstmt.setInt(3, (scri.getPage()-1)*0);
			pstmt.setInt(4, scri.getPage()*10); 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setNum(rs.getInt("num"));
				mv.setMidx(rs.getInt("midx"));
				mv.setMname(rs.getString("mname"));
				mv.setMid(rs.getString("mid"));
				mv.setMphone(rs.getString("mphone"));
				mv.setMssn(rs.getString("mssn"));
				mv.setMsignindate(rs.getDate("msignindate"));
				alist.add(mv);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
				e.printStackTrace();
				
		}
			return alist;
	}
	
	
	public int memberTotal(String keyword) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = 0;
		String sql = "select count(*) as cnt from member where mgrade='G' and msecessionyn='N' and (mname like ? or mid like ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			cnt = rs.getInt("cnt");
			}			
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
				
		return cnt;		
	}
	
	//관리자-회원관리-회원클릭시
	public MemberVo memberSelectOne(int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo mv = null;
		String sql = "select * from member where midx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMid(rs.getString("mid"));
				mv.setMname(rs.getString("mname"));
				mv.setMphone(rs.getString("mphone"));
				mv.setMssn(rs.getString("mssn"));
				mv.setMbirthmonth(rs.getString("mbirthmonth"));
				mv.setMbirthday(rs.getString("mbirthday"));
				mv.setMaddress(rs.getString("maddress"));
				mv.setMemail(rs.getString("memail"));
				mv.setMpostcode(rs.getString("mpostcode"));
				mv.setMdetailaddress(rs.getString("mdetailaddress"));
				mv.setMextraaddress(rs.getString("mextraaddress"));
				mv.setMgender(rs.getString("mgender"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return mv;
	}
	
	//관리자가 회원정지 시킬 때
	public int MemberDelete(int midx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		String sql = "update member set MSECESSIONYN = 'Y' , MSECESSIONDATE = sysdate where midx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int MemberModify(String mid, String mpwd, String mname, String mssn, String mbirthmonth, String mbirthday, String mpostcode, String maddress, String mdetailaddress, String mextraaddress, String mgender, String memail, String mphone) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql = "update member set mname=?, mssn=?, mbirthmonth=?, mbirthday=?, mpostcode=?, maddress=?, mdetailaddress=?, mextraaddress=?, mgender=?, memail=?, mphone=?, mpwd=? where mid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mname);
			pstmt.setString(2, mssn);
			pstmt.setString(3, mbirthmonth);
			pstmt.setString(4, mbirthday);
			pstmt.setString(5, mpostcode);
			pstmt.setString(6, maddress);
			pstmt.setString(7, mdetailaddress);
			pstmt.setString(8, mextraaddress);
			pstmt.setString(9, mgender);
			pstmt.setString(10, memail);
			pstmt.setString(11, mphone);
			pstmt.setString(12, mpwd);
			pstmt.setString(13, mid);
			value = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	//본인이 탈퇴할 때
	public int userDelete(String mpwd) {
					
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		
		int value = 0;
		String sql = "update member set MSECESSIONYN = 'Y', MSECESSIONDATE = NOW() where mpwd = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mpwd);
			value = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
					
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return value;
	}
	
	
	
	//회원정보 수정할 때 불러오기
	public MemberVo memberSelectOne2(String mid) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo mv = null;
		String sql = "select * from member where mid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMid(rs.getString("mid"));
				mv.setMname(rs.getString("mname"));
				mv.setMphone(rs.getString("mphone"));
				mv.setMssn(rs.getString("mssn"));
				mv.setMbirthmonth(rs.getString("mbirthmonth"));
				mv.setMbirthday(rs.getString("mbirthday"));
				mv.setMaddress(rs.getString("maddress"));
				mv.setMemail(rs.getString("memail"));
				mv.setMpostcode(rs.getString("mpostcode"));
				mv.setMdetailaddress(rs.getString("mdetailaddress"));
				mv.setMextraaddress(rs.getString("mextraaddress"));
				mv.setMgender(rs.getString("mgender"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return mv;
	}
	
	
}
