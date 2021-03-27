package com.TicketLupin.web.service;

import java.sql.*;
import java.util.ArrayList;
import com.TicketLupin.web.DBconn.DBconn;


	public class AdminDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AdminDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}

	
	public ArrayList<Show1Vo> ShowSelectAll(String query) {
		
		ArrayList<Show1Vo> alist = new ArrayList<Show1Vo>();
		
		String sql= "SELECT SHOW1.*, SHOW2.STITLEIMAGE FROM SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX "
				+ "WHERE SHOW1.SDELYN='N' AND STITLE LIKE ? ORDER BY SHOW1.SIDX DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Show1Vo sv = new Show1Vo();
				
				sv.setSidx(rs.getInt("sidx"));
				sv.setStitleimage(rs.getString("simage"));
				sv.setStitle(rs.getString("stitle"));
				sv.setSopendate(rs.getDate("sopendate"));
				sv.setSenddate(rs.getDate("senddate"));
				sv.setSdetailaddress(rs.getString("sdetailaddress"));
				sv.setSextraaddress(rs.getString("sextraaddress"));
				
				alist.add(sv);
			}						
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
		}
		
		return alist;
	}
	
	
	public ArrayList<MemberVo> getUserList(int sidx){
		
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		String sql = "select r.ridx, m.mname, m.mid, r.sidx, m.midx from member m inner join reservation r on m.midx=r.midx where sidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				
				mv.setRidx(rs.getInt("ridx"));
				mv.setMname(rs.getString("mname"));
				mv.setMid(rs.getString("mid"));
				mv.setSidx(rs.getInt("sidx"));
				mv.setMidx(rs.getInt("midx"));
				list.add(mv);
			}
			
		}catch (SQLException e) {			
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return list;
	}
	
	public ArrayList<ReservationVo> UserReservationList(int midx){
		
		ArrayList<ReservationVo> alist = new ArrayList<>();
		
		String sql = "SELECT R.RIDX, R.SRDATE, R.RREGDATE, S.STITLE, R.RSEAT, R.RPRICE " 
				+ "FROM RESERVATION R INNER JOIN SHOW S ON R.SIDX=S.SIDX WHERE R.MIDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				ReservationVo rv = new ReservationVo();
				
				
				rv.setRidx(rs.getInt("ridx"));
				rv.setSrdate(rs.getString("srdate"));
				rv.setRregdate(rs.getDate("rregdate"));
				rv.setStitle(rs.getString("stitle"));
				rv.setRseat(rs.getString("rseat"));
				rv.setRprice(rs.getInt("rprice"));
				
				alist.add(rv);
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
	
	public ArrayList<FaqVo> UserQnaList(int midx){
		
		ArrayList<FaqVo> alist = new ArrayList<FaqVo>();
		
		String sql = "SELECT FIDX, FTYPE, FTITLE FROM FAQ WHERE MIDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				FaqVo fv = new FaqVo();
				fv.setFidx(rs.getInt("fidx"));
				fv.setFtitle(rs.getString("ftitle"));
				fv.setFtype(rs.getString("ftype"));
				

				alist.add(fv);
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
	
	public ArrayList<C_commentVo> UserCommentList(int midx){
		
		ArrayList<C_commentVo> alist = new ArrayList<C_commentVo>();
		
		String sql = "SELECT CIDX, CCCATEGORY, CCCONTENT, CCREGDATE FROM C_COMMENT WHERE MIDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				C_commentVo cv = new C_commentVo();
				cv.setCidx(rs.getInt("cidx"));
				cv.setCccategory(rs.getString("cccategory"));
				cv.setCccontent(rs.getString("cccontent"));
				cv.setCcregdate(rs.getDate("ccregdate"));
				
				alist.add(cv);
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
	
	
	


	
	
}