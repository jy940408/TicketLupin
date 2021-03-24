package com.TicketLupin.web.service;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class ReservationDao {

	Connection conn;
	PreparedStatement pstmt;
	
	
	public ReservationDao(){
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();		
	}
	
	public int insertReservation(ReservationVo rv) {
		
		int value = 0;
		
		String sql = "INSERT INTO RESERVATION(RIDX, SIDX, MIDX, RSEAT, RPRICE, RDISCOUNT, SRDATE, SRROUND, RREGDATE, RDELYN, "
				+ "RPICK, RNAME, RTEL, REMAIL, RPAYMETHOD, RCARD, RQUOTA)"
					+"VALUES(RESERVATION_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, 'N', ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rv.getSidx());
			pstmt.setInt(2, rv.getMidx());
			pstmt.setString(3, rv.getRseat());
			pstmt.setInt(4, rv.getRprice());
			pstmt.setString(5, rv.getRdiscount());
			pstmt.setString(6, rv.getSrdate());
			pstmt.setString(7, rv.getSrround());
			pstmt.setString(8, rv.getRpick());
			pstmt.setString(9, rv.getRname());
			pstmt.setString(10, rv.getRtel());
			pstmt.setString(11, rv.getRemail());
			pstmt.setString(12, rv.getRpaymethod());
			pstmt.setString(13, rv.getRcard());
			pstmt.setString(14, rv.getRquota());
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public ArrayList<ReservationShowVo> getReservationList(int idx, int page){
		ArrayList<ReservationShowVo> list = new ArrayList<>();

		String sql = "SELECT SHOW1.STITLE, MYLIST.* FROM (SELECT ROWNUM NUM, LISTNUM.* FROM (SELECT RESERVATION.* FROM RESERVATION WHERE MIDX = ? AND RDELYN = 'N' ORDER BY RREGDATE DESC) LISTNUM) MYLIST INNER JOIN SHOW1 ON MYLIST.SIDX = SHOW1.SIDX WHERE NUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, 1+(page-1)*15);
			pstmt.setInt(3, page*15);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationShowVo rsv = new ReservationShowVo();
				
				rsv.setStitle(rs.getString("STITLE"));
				rsv.setRidx(rs.getInt("RIDX"));
				rsv.setSidx(rs.getInt("SIDX"));
				rsv.setMidx(rs.getInt("MIDX"));
				rsv.setRseat(rs.getString("RSEAT"));
				rsv.setRprice(rs.getInt("RPRICE"));
				rsv.setRdiscount(rs.getNString("RDISCOUNT"));
				rsv.setSrdate(rs.getString("SRDATE"));
				rsv.setSrround(rs.getString("SRROUND"));
				rsv.setRregdate(rs.getDate("RREGDATE"));
				rsv.setNum(rs.getInt("NUM"));
				
				list.add(rsv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public int getReservationCount(int idx) {
		int count = 0;
		String sql = "SELECT COUNT(*) CNT FROM SHOW1 INNER JOIN (SELECT * FROM RESERVATION WHERE MIDX = ? AND RDELYN = 'N' ORDER BY RREGDATE DESC) MYLIST ON MYLIST.SIDX = SHOW1.SIDX";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count = rs.getInt("CNT");
							
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	public ArrayList<ReservationShowVo> getDelReservationList(int idx, int page){
		ArrayList<ReservationShowVo> list = new ArrayList<>();

		String sql = "SELECT NUM, SHOW1.STITLE, MYLIST.* FROM (SELECT ROWNUM NUM, RESERVATION.* FROM RESERVATION WHERE MIDX = ? AND RDELYN = 'Y' ORDER BY RREGDATE DESC) MYLIST INNER JOIN SHOW1 ON MYLIST.SIDX = SHOW1.SIDX WHERE NUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, 1+(page-1)*15);
			pstmt.setInt(3, page*15);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationShowVo rsv = new ReservationShowVo();
				
				rsv.setStitle(rs.getString("STITLE"));
				rsv.setRidx(rs.getInt("RIDX"));
				rsv.setSidx(rs.getInt("SIDX"));
				rsv.setMidx(rs.getInt("MIDX"));
				rsv.setRseat(rs.getString("RSEAT"));
				rsv.setRprice(rs.getInt("RPRICE"));
				rsv.setRdiscount(rs.getNString("RDISCOUNT"));
				rsv.setSrdate(rs.getString("SRDATE"));
				rsv.setSrround(rs.getString("SRROUND"));
				rsv.setRregdate(rs.getDate("RREGDATE"));
				rsv.setNum(rs.getInt("NUM"));
				
				list.add(rsv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public int getDelReservationCount(int idx) {
		int count = 0;
		String sql = "SELECT COUNT(*) CNT FROM SHOW INNER JOIN (SELECT * FROM RESERVATION WHERE MIDX = ? AND RDELYN = 'Y' ORDER BY RREGDATE DESC) MYLIST ON MYLIST.SIDX = SHOW.SIDX";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count = rs.getInt("CNT");
							
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	public ReservationShowVo getReservationDetail(int ridx, int midx) {
		ReservationShowVo rsv = new ReservationShowVo();
		String sql = "SELECT R_TITLE.*, SHOW2.STITLEIMAGE FROM (SELECT RESERVATION.*, SHOW1.STITLE FROM RESERVATION INNER JOIN SHOW1 ON RESERVATION.SIDX = SHOW1.SIDX WHERE RESERVATION.RIDX = ? AND RESERVATION.MIDX = ? ORDER BY RESERVATION.RREGDATE DESC) R_TITLE INNER JOIN SHOW2 ON R_TITLE.SIDX = SHOW2.SIDX";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ridx);
			pstmt.setInt(2, midx);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			rsv.setRidx(rs.getInt("RIDX"));
			rsv.setSidx(rs.getInt("SIDX"));
			rsv.setMidx(rs.getInt("MIDX"));
			rsv.setRseat(rs.getString("RSEAT"));
			rsv.setRprice(rs.getInt("RPRICE"));
			rsv.setRdiscount(rs.getString("RDISCOUNT"));
			rsv.setSrdate(rs.getString("SRDATE"));
			rsv.setSrround(rs.getString("SRROUND"));
			rsv.setRregdate(rs.getDate("RREGDATE"));
			rsv.setStitle(rs.getNString("STITLE"));
			rsv.setStitleimage(rs.getString("STITLEIMAGE"));
			rsv.setRpick(rs.getString("RPICK"));
			rsv.setRname(rs.getString("RNAME"));
			rsv.setRtel(rs.getString("RTEL"));
			rsv.setRemail(rs.getString("REMAIL"));
			rsv.setRpaymethod(rs.getString("RPAYMETHOD"));
			rsv.setRcard(rs.getString("RCARD"));
			rsv.setRquota(rs.getString("RQUOTA"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rsv;
	}
	
	public int deleteReservation(int ridx, int midx) {
		System.out.println("메소드 들어오는지 확인");
		System.out.println("ridx: " + ridx);
		System.out.println("midx: " + midx);
		int value = 0;
		String sql = "UPDATE RESERVATION SET RDELYN = 'Y' WHERE RIDX = ? AND MIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ridx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("value값: " + value);
		return value;
	}
}
