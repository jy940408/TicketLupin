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
	ResultSet rs;
	
	public ReservationDao(){
		
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();		
	}
	
	//예매하기
	public int insertReservationIdx(ReservationIdxVo riv) {
		int value = 0;
		
		String sql = "INSERT INTO RESERVATIONIDX(SIDX, MIDX, RIREGDATE, "
				+ "SRDATE, SRROUND, RIBASIC, RIDISCOUNT, RIVAT, RIDELIVERY, "
				+ "RIPAYMENT, RIDELYN, RIDELDATE) "
				+ "VALUES(?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, 'N', NOW())";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, riv.getSidx());
			pstmt.setInt(2, riv.getMidx());
			pstmt.setString(3, riv.getSrdate());
			pstmt.setString(4, riv.getSrround());
			pstmt.setInt(5, riv.getRibasic());
			pstmt.setInt(6, riv.getRidiscount());
			pstmt.setInt(7, riv.getRivat());
			pstmt.setInt(8, riv.getRidelivery());
			pstmt.setInt(9, riv.getRipayment());
			
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	//내가 한 예매 중 가장 최근 인덱스 불러오기
	public int getReservaionRecentIdx(int sidx, int midx) {
		
		int riv = 0;
		
		String sql = "SELECT MAX(RIIDX) RIIDX FROM RESERVATIONIDX WHERE SIDX = ? AND MIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			riv = rs.getInt("RIIDX");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return riv;
		
	}
	
	//예매 좌석별로 예매 내역 추가하기
	public int insertReservation(ReservationVo rv) {
		
		int value = 0;
		
		String sql = "INSERT INTO RESERVATION(SIDX, MIDX, RSEAT, " 
				+ "RPRICE, RDISCOUNT, SRDATE, SRROUND, RREGDATE, RDELYN, "
				+ "RPICK, RNAME, RTEL, REMAIL, RPAYMETHOD, RCARD, RQUOTA, RIIDX) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, NOW(), 'N', "
				+ "?, ?, ?, ?, ?, ?, ?, ?)";

		
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
			pstmt.setInt(15, rv.getRiidx());
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	public ArrayList<ReservationShowVo> getReservationList(int idx, int page){
		ArrayList<ReservationShowVo> list = new ArrayList<>();

		String sql = "SELECT SHOW1.STITLE, MYLIST.* FROM "
				+ "(SELECT LISTNUM.* FROM "
				+ "(SELECT RESERVATION.* FROM "
				+ "RESERVATION WHERE MIDX = ? AND RDELYN = 'N' "
				+ "ORDER BY RREGDATE DESC) LISTNUM) MYLIST "
				+ "INNER JOIN SHOW1 ON MYLIST.SIDX = SHOW1.SIDX "
				+ "LIMIT ?, 15";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, 15*(page-1));
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
				
				list.add(rsv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	//마이티켓 예매목록 리스트 불러오기
	public ArrayList<ReservationListVo> getReservationIdxList(int idx, int page){
		ArrayList<ReservationListVo> list = new ArrayList<>();

		String sql = "SELECT * FROM " + 
				"(SELECT COMINFO.*, @ROWNUM:=@ROWNUM+1 NUM FROM " + 
				"(SELECT MAININFO.*, SHOW1.STITLE FROM " + 
				"(SELECT RESERVATIONIDX.* FROM " + 
				"RESERVATIONIDX WHERE MIDX = ?) MAININFO " + 
				"INNER JOIN SHOW1 ON MAININFO.SIDX = SHOW1.SIDX " + 
				"WHERE SHOW1.SDELYN = 'N' AND MAININFO.RIDELYN = 'N') COMINFO, " + 
				"(SELECT @ROWNUM:=0) TMP ORDER BY RIIDX ASC) NUMINFO " + 
				"ORDER BY NUM DESC LIMIT ?, 15";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, 15*(page-1));
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationListVo rlv = new ReservationListVo();
				
				rlv.setRiidx(rs.getInt("RIIDX"));
				rlv.setStitle(rs.getString("STITLE"));
				rlv.setSidx(rs.getInt("SIDX"));
				rlv.setMidx(rs.getInt("MIDX"));
				rlv.setSrdate(rs.getString("SRDATE"));
				rlv.setSrround(rs.getString("SRROUND"));
				rlv.setRiregdate(rs.getDate("RIREGDATE"));
				rlv.setNum(rs.getInt("NUM"));
				
				if(!rs.getDate("RIDELDATE").equals("0000-00-00 00:00:00")) {
					rlv.setRideldate(rs.getDate("RIDELDATE"));
				}else {
					rlv.setRideldate(null);
				}
				list.add(rlv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	//예매 된 좌석 리스트 목록 만들기
	public ArrayList<ReservationShowVo> getReservationSeatList(int sidx, String srdate, String srround){
		ArrayList<ReservationShowVo> list = new ArrayList<>();

		String sql = "SELECT @ROWNUM:=@ROWNUM+1 NUM, LISTNUM.RSEAT FROM "
					+ "(SELECT RESERVATION.* FROM "
					+ "RESERVATION WHERE SIDX = ? AND SRDATE = ? AND SRROUND = ? AND RDELYN = 'N' AND (@ROWNUM:=0)=0 "
					+ "ORDER BY RREGDATE DESC) LISTNUM";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			pstmt.setString(2, srdate);
			pstmt.setString(3, srround);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationShowVo rsv = new ReservationShowVo();
				
				rsv.setRseat(rs.getString("RSEAT"));
				rsv.setNum(rs.getInt("NUM"));
				
				list.add(rsv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	//예매 게시글 수
	public int getReservationCount(int idx) {
		int count = 0;
		String sql = "SELECT COUNT(*) CNT FROM SHOW1 "
						+ "INNER JOIN (SELECT * FROM "
						+ "RESERVATIONIDX WHERE MIDX = ? AND RIDELYN = 'N' "
						+ "ORDER BY RIREGDATE DESC) MYLIST "
						+ "ON MYLIST.SIDX = SHOW1.SIDX";
		
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
	
	//마이티켓 예매 취소 목록 리스트 불러오기
	public ArrayList<ReservationListVo> getReservationIdxDelList(int idx, int page){
		ArrayList<ReservationListVo> list = new ArrayList<>();

		String sql = "SELECT * FROM " + 
				"(SELECT COMINFO.*, @ROWNUM:=@ROWNUM+1 NUM FROM " + 
				"(SELECT MAININFO.*, SHOW1.STITLE FROM " + 
				"(SELECT RESERVATIONIDX.* FROM " + 
				"RESERVATIONIDX WHERE MIDX = ?) MAININFO " + 
				"INNER JOIN SHOW1 ON MAININFO.SIDX = SHOW1.SIDX " + 
				"WHERE SHOW1.SDELYN = 'N' AND MAININFO.RIDELYN = 'Y') COMINFO, " + 
				"(SELECT @ROWNUM:=0) TMP ORDER BY RIIDX ASC) NUMINFO " + 
				"ORDER BY NUM DESC LIMIT ?, 15";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, 15*(page-1));
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationListVo rlv = new ReservationListVo();
				
				rlv.setRiidx(rs.getInt("RIIDX"));
				rlv.setStitle(rs.getString("STITLE"));
				rlv.setSidx(rs.getInt("SIDX"));
				rlv.setMidx(rs.getInt("MIDX"));
				rlv.setSrdate(rs.getString("SRDATE"));
				rlv.setSrround(rs.getString("SRROUND"));
				rlv.setRiregdate(rs.getDate("RIREGDATE"));
				rlv.setNum(rs.getInt("NUM"));
				
				list.add(rlv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	//예매취소 게시글 개수
	public int getDelReservationCount(int idx) {
		int count = 0;
		String sql = "SELECT COUNT(*) CNT FROM "
				+ "SHOW1 INNER JOIN "
				+ "(SELECT * FROM RESERVATIONIDX WHERE MIDX = ? AND RIDELYN = 'Y' "
				+ "ORDER BY RIREGDATE DESC) MYLIST "
				+ "ON MYLIST.SIDX = SHOW1.SIDX";

		
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
	
	//예매 내역 자세히 보기
	public ArrayList<ReservationShowVo> getReservationDetail(int midx, int riidx) {
		ArrayList<ReservationShowVo> list = new ArrayList<>();
		String sql = "SELECT * FROM RESERVATIONIDX "
				+ "INNER JOIN RESERVATION ON RESERVATIONIDX.RIIDX = RESERVATION.RIIDX "
				+ "INNER JOIN SHOW1 ON RESERVATION.SIDX = SHOW1.SIDX "
				+ "INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX "
				+ "WHERE RESERVATION.MIDX = ? AND RESERVATION.RIIDX = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, riidx);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				ReservationShowVo rsv = new ReservationShowVo();
					
				rsv.setRiidx(rs.getInt("RIIDX"));
				rsv.setRidx(rs.getInt("RIDX"));
				rsv.setSidx(rs.getInt("SIDX"));
				rsv.setMidx(rs.getInt("MIDX"));
				rsv.setSrdate(rs.getString("SRDATE"));
				rsv.setSrround(rs.getString("SRROUND"));
				rsv.setRregdate(rs.getDate("RREGDATE"));
				rsv.setRseat(rs.getString("RSEAT"));
				rsv.setStitle(rs.getNString("STITLE"));
				rsv.setStitleimage(rs.getString("STITLEIMAGE"));
				rsv.setRpick(rs.getString("RPICK"));
				rsv.setRname(rs.getString("RNAME"));
				rsv.setRtel(rs.getString("RTEL"));
				rsv.setRemail(rs.getString("REMAIL"));
				rsv.setRpaymethod(rs.getString("RPAYMETHOD"));
				rsv.setRcard(rs.getString("RCARD"));
				rsv.setRquota(rs.getString("RQUOTA"));
				rsv.setRiregdate(rs.getDate("RIREGDATE"));
				rsv.setRibasic(rs.getInt("RIBASIC"));
				rsv.setRidiscount(rs.getInt("RIDISCOUNT"));
				rsv.setRivat(rs.getInt("RIVAT"));
				rsv.setRidelivery(rs.getInt("RIDELIVERY"));
				rsv.setRipayment(rs.getInt("RIPAYMENT"));
				rsv.setRseat(rs.getString("RSEAT"));
				rsv.setRdiscount(rs.getString("RDISCOUNT"));
				rsv.setRideldate(rs.getDate("RIDELDATE"));
				
				list.add(rsv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	//예매 취소하기
	public int deleteReservationIDX(int riidx, int midx) {
		int value = 0;
		String sql = "UPDATE RESERVATIONIDX SET RIDELYN = 'Y', RIDELDATE = NOW() "
				+ "WHERE RIIDX = ? AND MIDX = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, riidx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int deleteReservation(int riidx, int midx) {
		int value = 0;
		String sql = "UPDATE RESERVATION SET RDELYN = 'Y' WHERE RIIDX = ? AND MIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, riidx);
			pstmt.setInt(2, midx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	//날짜가 달라진 자리별 예매내역 삭제
	public int deleteUpdateReservation1(int sidx) {
		int value = 0;
		String sql ="UPDATE RESERVATION, "
						+ "(SELECT R.RIDX FROM RESERVATION R INNER JOIN SHOW1 S "
						+ "ON R.SIDX = S.SIDX "
						+ "AND R.SIDX = ? "
						+ "WHERE STR_TO_DATE(R.SRDATE, '%Y-%m-%d') < STR_TO_DATE(S.SOPENDATE, '%Y-%m-%d') " 
						+ "OR STR_TO_DATE(R.SRDATE, '%Y-%m-%d') > STR_TO_DATE(S.SENDDATE, '%Y-%m-%d')) SUM "
						+ "SET RESERVATION.RDELYN = 'Y' "
						+ "WHERE RESERVATION.RIDX = SUM.RIDX";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	//날짜가 달라진 예매내역 묶음 삭제
	public int deleteUpdateReservationIDX1(int sidx) {
		int value = 0;
		String sql = "UPDATE RESERVATIONIDX, "
				+ "(SELECT R.RIIDX FROM RESERVATIONIDX R INNER JOIN SHOW1 S "
						+ "ON R.SIDX = S.SIDX "
						+ "AND R.SIDX = ? "
						+ "WHERE STR_TO_DATE(R.SRDATE, '%Y-%m-%d') < STR_TO_DATE(S.SOPENDATE, '%Y-%m-%d') "
						+ "OR STR_TO_DATE(R.SRDATE, '%Y-%m-%d') > STR_TO_DATE(S.SENDDATE, '%Y-%m-%d')) SUM "
						+ "SET RESERVATIONIDX.RIDELYN = 'Y', RESERVATIONIDX.RIDELDATE = NOW() "
						+ "WHERE RESERVATIONIDX.RIIDX = SUM.RIIDX";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	//회차가 달라진 자리별 예매내역 삭제
	public int deleteUpdateReservation2(int sidx) {
		int value = 0;
		String sql = "UPDATE RESERVATION, "
				+ "(SELECT R.RIDX FROM RESERVATION R INNER JOIN SHOWROUND S "
						+ "ON R.SIDX = S.SIDX "
						+ "AND R.SIDX = ? "
						+ "AND R.SRDATE = S.SRDATE "
						+ "AND (S.SRROUND1 IS NULL OR NOT R.SRROUND = S.SRROUND1) "
						+ "AND (S.SRROUND2 IS NULL OR NOT R.SRROUND = S.SRROUND2) "
						+ "AND (S.SRROUND3 IS NULL OR NOT R.SRROUND = S.SRROUND3) "
						+ "AND (S.SRROUND4 IS NULL OR NOT R.SRROUND = S.SRROUND4)) SUM "
						+ "SET RESERVATION.RDELYN = 'Y' "
						+ "WHERE RESERVATION.RIDX = SUM.RIDX";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	//회차가 달라진 예매내역 묶음 삭제
	public int deleteUpdateReservationIDX2(int sidx) {
		int value = 0;
		String sql = "UPDATE RESERVATIONIDX, "
				+ "(SELECT R.RIIDX FROM RESERVATIONIDX R INNER JOIN SHOWROUND S "
						+ "ON R.SIDX = S.SIDX "
						+ "AND R.SIDX = ? "
						+ "AND R.SRDATE = S.SRDATE "
						+ "AND (S.SRROUND1 IS NULL OR NOT R.SRROUND = S.SRROUND1) "
						+ "AND (S.SRROUND2 IS NULL OR NOT R.SRROUND = S.SRROUND2) "
						+ "AND (S.SRROUND3 IS NULL OR NOT R.SRROUND = S.SRROUND3) "
						+ "AND (S.SRROUND4 IS NULL OR NOT R.SRROUND = S.SRROUND4) "
						+ "WHERE R.RIDELYN = 'N') SUM "
						+ "SET RESERVATIONIDX.RIDELYN = 'Y', RESERVATIONIDX.RIDELDATE = NOW() "
						+ "WHERE RESERVATIONIDX.RIIDX = SUM.RIIDX";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	public ArrayList<ReservationIdxVo> deleteUpdateReservationIDX2List(int sidx){
		
		ArrayList<ReservationIdxVo> list = new ArrayList<ReservationIdxVo>();
		String sql = "SELECT DELRES.RIIDX, DELRES.SIDX, DELRES.MIDX, DELRES.SRDATE, DELRES.SRROUND, DELRES.RIBASIC, "
				+ "DELRES.RIDISCOUNT, DELRES.RIVAT, DELRES.RIDELIVERY, "
				+ "DELRES.RIPAYMENT, DELRES.RIDELDATE, DELRES.RNAME, DELRES.RTEL, DELRES.REMAIL, DELRES.RPAYMETHOD, "
				+ "SHOW1.STITLE FROM "
				+ "(SELECT ALLRES.RIIDX, ALLRES.SIDX, ALLRES.MIDX, ALLRES.SRDATE, ALLRES.SRROUND, ALLRES.RIBASIC, "
				+ "ALLRES.RIDISCOUNT, ALLRES.RIVAT, ALLRES.RIPAYMENT, ALLRES.RIDELIVERY, ALLRES.RIDELDATE, RESGROUP.RNAME, RESGROUP.RTEL, "
				+ "RESGROUP.REMAIL, RESGROUP.RPAYMETHOD FROM "
				+ "(SELECT R.RIIDX, R.SIDX, R.MIDX, R.SRDATE, R.SRROUND, R.RIBASIC, R.RIDISCOUNT, R.RIVAT, R.RIDELIVERY, R.RIPAYMENT, RIDELDATE "
				+ "FROM RESERVATIONIDX R INNER JOIN SHOWROUND S "
				+ "ON R.SIDX = S.SIDX "
				+ "AND R.SIDX = ? "
				+ "AND R.SRDATE = S.SRDATE "
				+ "AND R.RIDELYN = 'Y' "
				+ "AND (S.SRROUND1 IS NULL OR NOT R.SRROUND = S.SRROUND1) "
				+ "AND (S.SRROUND2 IS NULL OR NOT R.SRROUND = S.SRROUND2) "
				+ "AND (S.SRROUND3 IS NULL OR NOT R.SRROUND = S.SRROUND3) "
				+ "AND (S.SRROUND4 IS NULL OR NOT R.SRROUND = S.SRROUND4) "
				+ "WHERE R.RIDELDATE = (SELECT MAX(RIDELDATE) FROM RESERVATIONIDX)) ALLRES "
				+ "INNER JOIN (SELECT SIDX, RNAME, RTEL, REMAIL, RPAYMETHOD, RCARD, RIIDX "
				+ "FROM RESERVATION "
				+ "GROUP BY RIIDX, SIDX, RNAME, RTEL, REMAIL, RPAYMETHOD, RCARD, RIIDX) RESGROUP "
				+ "ON ALLRES.RIIDX = RESGROUP.RIIDX) DELRES "
				+ "INNER JOIN SHOW1 ON DELRES.SIDX = SHOW1.SIDX";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReservationShowVo rsv = new ReservationShowVo();
				
				rsv.setRiidx(rs.getInt("RIIDX"));
				rsv.setSidx(rs.getInt("SIDX"));
				rsv.setMidx(rs.getInt("MIDX"));
				rsv.setSrdate(rs.getString("SRDATE"));
				rsv.setSrround(rs.getString("SRROUND"));
				rsv.setRibasic(rs.getInt("RIBASIC"));
				rsv.setRidiscount(rs.getInt("RIDISCOUNT"));
				rsv.setRivat(rs.getInt("RIVAT"));
				rsv.setRidelivery(rs.getInt("RIDELIVERY"));
				rsv.setRipayment(rs.getInt("RIPAYMENT"));
				rsv.setStitle(rs.getString("STITLE"));
				rsv.setRname(rs.getString("RNAME"));
				rsv.setRtel(rs.getString("RTEL"));
				rsv.setRemail(rs.getString("REMAIL"));
				rsv.setRpaymethod(rs.getString("RPAYMETHOD"));
				rsv.setStitle(rs.getString("STITLE"));
				list.add(rsv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public int deleteCancelReservation(int sidx) {
		int value = 0;
		String sql = "UPDATE RESERVATION, "
				+ "(SELECT * FROM RESERVATION WHERE SIDX = 1) SUM "
				+ "SET RESERVATION.RDELYN = 'Y' "
				+ "WHERE RESERVATION.RIDX = SUM.RIDX";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
}
