package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import com.TicketLupin.web.DBconn.DBconn;

import domain.SearchCriteria;
import util.DatabaseUtil;

public class MemberDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	
	public ArrayList memberLogin(String mid, String mpwd) {
	      
	      String sql = "SELECT MGRADE, MIDX FROM MEMBER WHERE MID = ? AND MPWD = ?";
	      ArrayList value = new ArrayList();
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mid);
	         pstmt.setString(2, mpwd);
	         ResultSet rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            value.add(rs.getString("MGRADE"));
	            value.add(rs.getInt("MIDX"));
	         }   
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return value; 
	      
	   }

	
	public int insertMember(String MID,  String MPWD, String MNAME, String MADDRESS, String MEMAIL, String MPHONE, String MSSN, String MBIRTHMONTH, String MBIRTHDAY) {
		int exec = 0;
		
		try {
			String sql = "insert into member(midx, MID, MPWD,  MNAME, MADDRESS, MEMAIL, MPHONE, MSSN, MBIRTHMONTH, MBIRTHDAY)"
					+ "values(midx_seq.nextval,?,?,?,?,?,?, ?,?,?)";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, MID);
				pstmt.setString(2, MPWD);
				pstmt.setString(3, MNAME);
				pstmt.setString(4, MADDRESS);
				pstmt.setString(5, MEMAIL);
				pstmt.setString(6, MPHONE);
				pstmt.setString(7, MSSN);
				pstmt.setString(8, MBIRTHMONTH);
				pstmt.setString(9, MBIRTHDAY);
			exec = pstmt.executeUpdate();
			
			//conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				if(pstmt != null) {pstmt.close(); pstmt=null;}
				if(conn != null) {conn.close(); conn=null;}
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return exec;
	}
	
	
	public String findId(String mname, String memail) throws SQLException{
		
		String mid = null;
		
		String sql = "select mid from member where mname=? and memail=?";
		
		try {
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, mname);
			pstmt.setString(2, memail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mid = rs.getString("mid");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return mid;
	}
	
	public String findPwd(String mid, String memail, String mname) throws SQLException {
		
		String mpwd = null;
		
		String sql = "select mpwd from member where mname=? and mid=? and memail=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mname);
			pstmt.setString(2, mid);
			pstmt.setString(3, memail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mpwd = rs.getString("mpwd");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return mpwd;
	}
	
	public int changePwd(String mpwd, String mid) throws SQLException {
		
		int value = 0;
		
		String sql = "update member set mpwd=? where mid=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mpwd);
			pstmt.setString(2, mid);
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("value : "+value);
		return value;
		
		
	}
	
	public MemberVo getMember(String mid) {
		
		MemberVo mv = null;
		ResultSet rs = null;
		
		String sql = "select * from member where mid = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMid(rs.getString("mid"));
				mv.setMpwd(rs.getString("mpwd"));
				mv.setMaddress(rs.getString("maddress"));
				mv.setMemail(rs.getString("memail"));
				mv.setMphone(rs.getString("mphone"));
				mv.setMname(rs.getString("mname"));
				mv.setMgrade(rs.getString("mgrade"));
				mv.setMssn(rs.getString("mssn"));
				mv.setMidx(rs.getInt("midx"));
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}
	
	
	
	
	
	public String getMemberEmail(String mId) {
		
		String sql ="select memail from member where mId = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getString(1);
			}
			
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try{if(conn != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(pstmt != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(rs != null) conn.close();}catch (Exception e) {e.printStackTrace();}
		}
		
		return null; 
	}
	
	
	public boolean getMemberEmailChecked(String mId) {
		
		String sql ="select memail from member where mId = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getBoolean(1);
			}
			
			
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try{if(conn != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(pstmt != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(rs != null) conn.close();}catch (Exception e) {e.printStackTrace();}
		}
		
		return false; 
		
	}
	
	
	public boolean setMemberEmailChecked(String mId) {
		
		String sql ="update member set mEmailChecked = true where mId=?";
		 
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.executeUpdate();			
			return true;
			
		}catch(Exception e) {
				e.printStackTrace();
				
		}finally {
			try{if(conn != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(pstmt != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			try{if(rs != null) conn.close();}catch (Exception e) {e.printStackTrace();}
			
		}
		
		return false; 
		
	}
	
	
	
	public boolean isExistId(String mid) {
		
		boolean b = false;
		
		String sql = "select mid from member where mid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();
			b = rs.next();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("error : "+e);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("°á°ú : "+b);
		return b;
	}
	
	
	public ArrayList<MemberVo> memberSelectAll(SearchCriteria scri){
		
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		
		//String sql = "select * from member where mgrade='A' order by midx desc";
		String sql = "select B.* from (select rownum as rnum, A.* from  ("
				+ "select * from member where mgrade='A' and msecessionyn='N' and "
				+ "(mname like ? or mid like ? or mphone like ? or mssn like ?)"
				+ "order by midx desc) A where rownum <= ?) B where B.rnum >= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setString(2, "%"+scri.getKeyword()+"%");
			pstmt.setString(3, "%"+scri.getKeyword()+"%");
			pstmt.setString(4, "%"+scri.getKeyword()+"%");
			pstmt.setInt(5, scri.getPage()*10);
			pstmt.setInt(6, (scri.getPage()-1)*10+1);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMname(rs.getString("mname"));
				mv.setMid(rs.getString("mid"));
				mv.setMphone(rs.getString("mphone"));
				mv.setMssn(rs.getString("mssn"));
				alist.add(mv);
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			return alist;
	}
	
	public int memberTotal(String keyword) {
		
		int cnt = 0;
		ResultSet rs = null;
		
		String sql = "select count(*) as cnt from member where mgrade='A' and msecessionyn='N' and (mname like ? or mid like ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			cnt = rs.getInt("cnt");
			}			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
				
		return cnt;		
	}
	
	public MemberVo memberSelectOne(int midx) {
		
		MemberVo mv = null;
		ResultSet rs = null;
		
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
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}
	
	public int MemberDelete(int midx) {
		
		int result = 0;
		
		String sql = "update member set MSECESSIONYN = 'Y' where midx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	


	
	
	
	
	
	
	
}
