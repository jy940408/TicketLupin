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
	
	
	
//�������� -> �� ���� ������ ȸ������Ʈ	
	public ArrayList<MemberVo> getUserBuyList(int sidx, int page){
		
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		String sql = "select * from (select @rownum:=@rownum+1 num, A.* from "
				+ "(select reservationidx.riidx, member.mname, member.mid, reservationidx.sidx, member.midx "
				+ "from member inner join reservationidx on member.midx=reservationidx.midx, (select @rownum:=0) tmp "
				+ "where reservationidx.sidx=? and reservationidx.ridelyn = 'N') A) B where num between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				
				mv.setNum(rs.getInt("num"));
				mv.setRidx(rs.getInt("riidx"));
				mv.setMname(rs.getString("mname"));
				mv.setMid(rs.getString("mid"));
				mv.setSidx(rs.getInt("sidx"));
				mv.setMidx(rs.getInt("midx"));
				list.add(mv);
			}
			
		}catch (SQLException e) {			
			e.printStackTrace();
			
		}
	
		return list;
	}
	
//�������� -> �� ���� ���� ȸ�� ��
	public int getBuyListCount(int sidx){
		
		int count = 0;
		
		String sql = "select count(*) from (select reservationidx.riidx, member.mname, member.mid, "
				+ "reservationidx.sidx, member.midx from member inner join reservationidx "
				+ "on member.midx=reservationidx.midx where ridelyn='N' and reservationidx.sidx=?) count";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sidx);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count(*)");
							
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}
	
	public Show1Vo getShowImg(int sidx) {
		
		Show1Vo s1v = new Show1Vo();
		
		String sql = "select show1.stitle, show2.stitleimage, show1.sidx from show1 inner join show2 on show1.sidx=show2.sidx where show1.sidx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				s1v = new Show1Vo();
				s1v.setStitle(rs.getString("stitle"));
				s1v.setStitleimage(rs.getString("stitleimage"));
				s1v.setSidx(rs.getInt("sidx"));
			}
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return s1v;
	}
		
	
	public ArrayList<ReservationVo> UserReservationList(int midx, int page){
		
		ArrayList<ReservationVo> alist = new ArrayList<>();
		
//		String sql = "SELECT * FROM (SELECT @ROWNUM:=@ROWNUM+1 NUM, A.* FROM (SELECT R.RIDX, R.SRDATE, R.RREGDATE, S.STITLE, R.RSEAT, R.RPRICE " + 
//				" FROM RESERVATION R INNER JOIN SHOW1 S ON R.SIDX=S.SIDX, (SELECT @ROWNUM:=0) TMP "
//				+ "WHERE RDELYN='N' AND R.MIDX=? ORDER BY R.RIDX DESC) A ) B WHERE NUM BETWEEN ? AND ?";
		
		String sql = "SELECT * FROM  RESERVATION R INNER JOIN SHOW1 S ON R.SIDX=S.SIDX WHERE RDELYN='N' AND R.MIDX=? ORDER BY R.RIDX DESC LIMIT ? , ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
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
			
		}
		return alist;
	}
	
	public int getUserBuyListCount(int midx){
		
		int count = 0;
		
		String sql = "select count(*) count from "
				+ "(select r.ridx, r.srdate, r.rregdate, s.stitle, r.rseat, r.rprice "
				+ "from reservation r inner join show1 s on r.sidx=s.sidx where r.midx=?) count";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("count");
							
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}
	
	public ArrayList<FaqVo> UserQnaList(int midx, int page){
		
		ArrayList<FaqVo> alist = new ArrayList<FaqVo>();
		
		String sql = "select * from (select @rownum:=@rownum+1 num, A.* from (SELECT FIDX, FTYPE, FTITLE FROM FAQ, "
				+ "(SELECT @ROWNUM:=0) TMP WHERE MIDX=?) A ) B LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, 1+(page-1)*0);
			pstmt.setInt(3, page*10);
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
	
	public ArrayList<CommentAVo> UserCommentList(int midx, int page){
		
		ArrayList<CommentAVo> alist = new ArrayList<CommentAVo>();
		
		String sql = "select * from(select @rownum:=@rownum+1 num, A.* from "
				+ "(SELECT C_IDX, C_SORT, C_CONTENT, C_REGDATE, midx FROM C_COMMENT, (SELECT @ROWNUM:=0) TMP WHERE MIDX=? and C_DELYN = 'N'  order by c_idx desc) "
				+ "A) B where num between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				CommentAVo cv = new CommentAVo();
				cv.setNum(rs.getInt("num"));
				cv.setC_idx(rs.getInt("c_idx"));
				cv.setC_sort(rs.getString("c_sort"));
				cv.setC_content(rs.getString("c_content"));
				cv.setC_regdate(rs.getDate("c_regdate"));
				cv.setMidx(rs.getInt("midx"));
				
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
	
	public int UserCommentDelete(int midx, int c_idx) {
		
		int result = 0;
		
		String sql ="update c_comment set C_DELYN = 'Y' where midx = ? and c_idx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, midx);
			pstmt.setInt(2, c_idx);
			int rs = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int UserBuyDelete(int ridx) {
		
		int result = 0;
		
		String sql ="update reservation set rdelyn = 'Y' where ridx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ridx);
			int rs = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	   public ArrayList<ReservationVo> UserReservationList2(int midx, int sidx){
		      
		      ArrayList<ReservationVo> alist = new ArrayList<>();
		      
		      String sql = "SELECT * FROM RESERVATION WHERE RDELYN='N' AND MIDX=? AND SIDX=? ORDER BY RIDX DESC";
		      
		      try {
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, midx);
		         pstmt.setInt(2, sidx);
		         ResultSet rs = pstmt.executeQuery();
		         
		         
		         while(rs.next()) {
		            
		            ReservationVo rv = new ReservationVo();
		         
		            rv.setRidx(rs.getInt("ridx"));
		            rv.setSrdate(rs.getString("srdate"));
		            rv.setSrround(rs.getString("srround"));
		            rv.setRseat(rs.getString("rseat"));
		            rv.setRprice(rs.getInt("rprice"));
		            rv.setRregdate(rs.getDate("rregdate"));
		            
		            alist.add(rv);
		         }
		         
		      }catch(SQLException e) {
		         e.printStackTrace();
		         
		      }
		      return alist;
		   }
		   

}