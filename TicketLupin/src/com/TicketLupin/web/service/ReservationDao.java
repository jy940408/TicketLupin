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
		
		String sql = "INSERT INTO RESERVATION(RIDX, SIDX, MIDX, RSEAT, RPRICE, RDISCOUNT, SRDATE, SRROUND, RREGDATE)"
					+"VALUES(RESERVATION_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rv.getSidx());
			pstmt.setInt(2, rv.getMidx());
			pstmt.setString(3, rv.getRseat());
			pstmt.setInt(4, rv.getRprice());
			pstmt.setString(5, rv.getRdiscount());
			pstmt.setString(6, rv.getSrdate());
			pstmt.setString(7, rv.getSrround());
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public ArrayList<ReservationShowVo> getReservationList(int idx, int page){
		ArrayList<ReservationShowVo> list = new ArrayList<>();

		String sql = "SELECT NUM, STITLE, MYLIST.* FROM SHOW INNER JOIN (SELECT ROWNUM NUM, RESERVATION.* FROM RESERVATION WHERE MIDX = ? ORDER BY RREGDATE DESC) MYLIST ON MYLIST.SIDX = SHOW.SIDX WHERE NUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, 1+(page-1)*15);
			pstmt.setInt(3, page*15);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String stitle = rs.getString("STITLE");
				int ridx = rs.getInt("RIDX");
				int sidx = rs.getInt("SIDX");
				int midx = rs.getInt("MIDX");
				String rseat = rs.getString("RSEAT");
				int price = rs.getInt("RPRICE");
				String rdiscount = rs.getNString("RDISCOUNT");
				String srdate = rs.getString("SRDATE");
				String srround = rs.getString("SRROUND");
				Date rregdate = rs.getDate("RREGDATE");
				int num = rs.getInt("NUM");
				
				ReservationShowVo rsv = new ReservationShowVo(stitle, ridx, sidx, midx, rseat, price, rdiscount, srdate, srround, rregdate, num);
				
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
		String sql = "SELECT COUNT(*) CNT FROM SHOW INNER JOIN (SELECT * FROM RESERVATION WHERE MIDX = ? ORDER BY RREGDATE DESC) MYLIST ON MYLIST.SIDX = SHOW.SIDX";
		
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
	
}
