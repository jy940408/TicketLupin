package com.TicketLupin.web.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;


	public class AdminDao {
	
	public ArrayList<Show1Vo> ShowSelectAll(String query) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
		}
		
		return alist;
	}
	
	
	
//�������� -> �� ���� ������ ȸ������Ʈ	
	public ArrayList<MemberVo> getUserBuyList(int sidx, int page){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		String sql = "select * from (select @rownum:=@rownum+1 num, A.* from "
				+ "(select reservation.ridx, member.mname, member.mid, reservation.sidx, member.midx from member inner join reservation "
				+ "on member.midx=reservation.midx, (SELECT @ROWNUM:=0) TMP where reservation.rdelyn = 'N' AND reservation.sidx=? order by midx desc) "
				+ "A ) B LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, 1+(page-1)*0);
			pstmt.setInt(3, page*10);
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
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (SQLException e) {			
			e.printStackTrace();
			
		}
	
		return list;
	}
	
//�������� -> �� ���� ���� ȸ�� ��
	public int getBuyListCount(int sidx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String sql = "select count(*) count from "
				+ "(select reservation.ridx, member.mname, member.mid, "
				+ "reservation.sidx, member.midx from member inner join reservation "
				+ "on member.midx=reservation.midx where reservation.sidx=? and reservation.rdelyn='N') count";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count");
							
			}
		
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}
		
	
	public ArrayList<ReservationVo> UserReservationList(int midx, int page){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ReservationVo> alist = new ArrayList<>();
		
		String sql = "SELECT * FROM(SELECT @ROWNUM:=@ROWNUM+1 NUM, a.* FROM (SELECT R.RIDX, R.SRDATE, R.RREGDATE, S.STITLE, R.RSEAT, R.RPRICE " + 
				" FROM RESERVATION R INNER JOIN SHOW1 S ON R.SIDX=S.SIDX, (SELECT @ROWNUM:=0) TMP WHERE R.MIDX=? ORDER BY R.RIDX DESC) A ) B LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, 1+(page-1)*0);
			pstmt.setInt(3, page*10);
			rs = pstmt.executeQuery();

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
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return alist;
	}
	
	public int getUserBuyListCount(int midx){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String sql = "select count(*) count from "
				+ "(select r.ridx, r.srdate, r.rregdate, s.stitle, r.rseat, r.rprice "
				+ "from reservation r inner join show1 s on r.sidx=s.sidx where r.midx=?) count";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count");
							
			}
		
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}
	
	public ArrayList<FaqVo> UserQnaList(int midx, int page){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<FaqVo> alist = new ArrayList<FaqVo>();
		
		String sql = "select * from (select @rownum:=@rownum+1 num, A.* from (SELECT FIDX, FTYPE, FTITLE FROM FAQ, "
				+ "(SELECT @ROWNUM:=0) TMP WHERE MIDX=?) A ) B LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, 1+(page-1)*0);
			pstmt.setInt(3, page*10);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				FaqVo fv = new FaqVo();
				fv.setFidx(rs.getInt("fidx"));
				fv.setFtitle(rs.getString("ftitle"));
				fv.setFtype(rs.getString("ftype"));
				

				alist.add(fv);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return alist;
	}
	
	public ArrayList<CommentAVo> UserCommentList(int midx, int page){
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CommentAVo> alist = new ArrayList<CommentAVo>();
		
		String sql = "select * from(select @rownum:=@rownum+1 num, A.* from "
				+ "(SELECT C_IDX, C_SORT, C_CONTENT, C_REGDATE, midx FROM C_COMMENT, (SELECT @ROWNUM:=0) TMP WHERE MIDX=? and C_DELYN = 'N'  order by c_idx desc) "
				+ "A) B LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, 1+(page-1)*0);
			pstmt.setInt(3, page*10);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				CommentAVo cv = new CommentAVo();
				cv.setC_idx(rs.getInt("c_idx"));
				cv.setC_sort(rs.getString("c_sort"));
				cv.setC_content(rs.getString("c_content"));
				cv.setC_regdate(rs.getDate("c_regdate"));
				cv.setMidx(rs.getInt("midx"));
				
				alist.add(cv);
			}
		
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return alist;
	}
	
	public int getShowtitle(int sidx_, String stitle) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value = 0;
		
		String sql = "select show1.stitle from reservation inner join show1 on reservation.sidx=show1.sidx where sidx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx_);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value = rs.getInt("value");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return value;
	}
	
	public int UserCommentDelete(int midx, int c_idx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql ="update c_comment set C_DELYN = 'Y' where midx = ? and c_idx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			rs = pstmt.executeQuery();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int UserBuyDelete(int ridx) {
		
		DBconn dbconn = new DBconn();
		Connection conn = dbconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql ="update reservation set rdelyn = 'Y' where ridx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ridx);
			rs = pstmt.executeQuery();
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}