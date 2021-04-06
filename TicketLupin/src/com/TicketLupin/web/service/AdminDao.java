package com.TicketLupin.web.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
		
		String sql= "select * from show1 inner join show2 on show1.sidx=show2.sidx where show1.sdelyn='N' and show1.stitle like ? order by show1.sidx desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Show1Vo sv = new Show1Vo();
				
				sv.setSidx(rs.getInt("sidx"));
				sv.setStitleimage(rs.getString("stitleimage"));
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
	
	
	
	
	public ArrayList<MemberVo> getUserBuyList(int sidx){
		
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		String sql = "select reservationidx.riidx, member.mname, member.mid, reservationidx.sidx, member.midx from member inner join reservationidx "
				+ "on member.midx=reservationidx.midx where reservationidx.ridelyn = 'N' AND sidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				
				mv.setRidx(rs.getInt("riidx"));
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
				+ "FROM RESERVATION R INNER JOIN SHOW1 S ON R.SIDX=S.SIDX WHERE R.MIDX=?";
		
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
	
	public int getShowtitle(int sidx_, String stitle) {
		
		int value = 0;
		
		String sql = "select show1.stitle from reservation inner join show1 on reservation.sidx=show1.sidx where sidx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx_);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getInt("value");
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return value;
	}
	
}