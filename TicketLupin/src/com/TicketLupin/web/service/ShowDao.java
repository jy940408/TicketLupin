package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TicketLupin.web.DBconn.DBconn;

public class ShowDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	
	public ShowDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public int insertShow(ShowVo sv) {
		int result = 0;
		//타占쏙옙틀, 占썲르, 占쌜쇽옙占쏙옙짜, 占쏙옙占쏙옙, 占싱뱄옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占승놂옙짜, 占쏙옙占쏙옙占쏙옙 占쏙옙짜, 占쏙옙占�, 회占쏙옙, 占쏙옙占쏙옙占싫�, 占쏙옙占싸몌옙占쌍쇽옙, 占쏙옙占쏙옙占쌍쇽옙, 占쏙옙占쌍쇽옙, 占쏙옙占쏙옙占쌓몌옙
		String sql = "INSERT INTO SHOW (SIDX, STITLE, SGENRE, SREGDATE, "
				+ "SROUND, SPRICE, SNOTICE, SDISCOUNT, SINFO, SCOMPANY, SROUNDIMAGE, SPRICEIMAGE, SNOTICEIMAGE, SDISCOUNTIMAGE, SINFOIMAGE, SCOMPANYIMAGE, "
				+ "SDELYN, SOPENDATE, SENDDATE, SRATING, SPOSTCODE, SROADADDRESS, SJIBUNADDRESS, SDETAILADDRESS, SEXTRAADDRESS, MIDX, STICKETINGDATE) "
				+ "VALUES(SHOW_SEQUENCE.NEXTVAL, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sv.getStitle());
			pstmt.setString(2, sv.getSgenre());
			pstmt.setString(3, sv.getSround());
			pstmt.setString(4, sv.getSprice());
			pstmt.setString(5, sv.getSnotice());
			pstmt.setString(6, sv.getSdiscount());
			pstmt.setString(7, sv.getSinfo());
			pstmt.setString(8, sv.getScompany());
			pstmt.setString(9, sv.getSroundimage());
			pstmt.setString(10, sv.getSpriceimage());
			pstmt.setString(11, sv.getSnoticeimage());
			pstmt.setString(12, sv.getSdiscountimage());
			pstmt.setString(13, sv.getSinfoimage());
			pstmt.setString(14, sv.getScompanyimage());
			pstmt.setDate(15, sv.getSopendate());
			pstmt.setDate(16, sv.getSenddate());
			pstmt.setString(17, sv.getSrating());
			pstmt.setString(18, sv.getSround());
			pstmt.setInt(19, sv.getSpostcode());
			pstmt.setString(20, sv.getSroadaddress());
			pstmt.setString(21, sv.getSjibunaddress());
			pstmt.setString(22, sv.getSdetailaddress());
			pstmt.setString(23, sv.getSextraaddress());
			pstmt.setInt(24, sv.getMidx());
			pstmt.setDate(25, sv.getSticketingdate());
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<ShowVo> getShowList(String query, String setting, String array, int page){
		
		ArrayList<ShowVo> list = new ArrayList<>();

		String sql = "SELECT * FROM (SELECT ROWNUM NUM, S.* FROM (SELECT * FROM SHOW WHERE STITLE LIKE ? AND SDELYN = 'N' ORDER BY " + setting +  " " + array + ") S) WHERE NUM BETWEEN ? AND ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*12);
			pstmt.setInt(3, page*12);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				int sidx = rs.getInt("SIDX");
				String stitle = rs.getString("STITLE");
				String sgenre = rs.getString("sgenre");
				Date sregdate = rs.getDate("SREGDATE");
				String sdelyn = rs.getString("SDELYN");
				int cidx = rs.getInt("CIDX");
				String sround = rs.getString("SROUND");
				String sprice = rs.getString("SPRICE");
				String snotice = rs.getString("SNOTICE");
				String sdiscount = rs.getString("SDISCOUNT");
				String sinfo = rs.getString("SINFO");
				String scompany = rs.getString("SCOMPANY");
				String sroundimage = rs.getString("SROUNDIMAGE");
				String spriceimage = rs.getString("SPRICEIMAGE");
				String snoticeimage = rs.getString("SNOTICEIMAGE");
				String sdiscountimage = rs.getString("SDISCOUNTIMAGE");
				String sinfoimage = rs.getString("SINFOIMAGE");
				String scompanyimage = rs.getString("SCOMPANYIMAGE");
				int spay = rs.getInt("SPAY");
				Date sopendate = rs.getDate("SOPENDATE");
				Date senddate = rs.getDate("SENDDATE");
				String srating = rs.getString("SRATING");
				int spostcode = rs.getInt("SPOSTCODE");
				String sroadaddress = rs.getString("SROADADDRESS");
				String sjibunaddress = rs.getString("SJIBUNADDRESS");
				String sdetailaddress = rs.getString("SDETAILADDRESS");
				String sextraaddress = rs.getString("sextraaddress");
				int midx = rs.getInt("MIDX");
				Date sticketingdate = rs.getDate("STICKETINGDATE");
				ShowVo sv = new ShowVo(sidx, stitle, sgenre, sregdate, spay, sdelyn, cidx, sopendate, senddate, srating, spostcode, sroadaddress, sjibunaddress, sdetailaddress, sextraaddress, midx, sticketingdate, sround, sprice, snotice, sdiscount, sinfo, scompany, sroundimage, spriceimage, snoticeimage, sdiscountimage, sinfoimage, scompanyimage);
				
				list.add(sv);
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
		
	}
	
	public int getShowListCount(String query, String setting){
		
		int count = 0;
		
		String sql = "SELECT COUNT(SIDX) COUNT FROM (SELECT ROWNUM NUM, S.* FROM (SELECT * FROM SHOW WHERE STITLE LIKE ? ORDER BY ? DESC) S)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setString(2, setting);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
							
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}

	public ShowVo getShowDetail(int idx) {
		ShowVo sv = null;
		
		String sql = "SELECT * FROM SHOW WHERE SIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			int sidx = rs.getInt("SIDX");
			String stitle = rs.getString("STITLE");
			String sgenre = rs.getString("sgenre");
			Date sregdate = rs.getDate("SREGDATE");
			String sdelyn = rs.getString("SDELYN");
			int cidx = rs.getInt("CIDX");
			String sround = rs.getString("SROUND");
			String sprice = rs.getString("SPRICE");
			String snotice = rs.getString("SNOTICE");
			String sdiscount = rs.getString("SDISCOUNT");
			String sinfo = rs.getString("SINFO");
			String scompany = rs.getString("SCOMPANY");
			String sroundimage = rs.getString("SROUNDIMAGE");
			String spriceimage = rs.getString("SPRICEIMAGE");
			String snoticeimage = rs.getString("SNOTICEIMAGE");
			String sdiscountimage = rs.getString("SDISCOUNTIMAGE");
			String sinfoimage = rs.getString("SINFOIMAGE");
			String scompanyimage = rs.getString("SCOMPANYIMAGE");
			int spay = rs.getInt("SPAY");
			Date sopendate = rs.getDate("SOPENDATE");
			Date senddate = rs.getDate("SENDDATE");
			String srating = rs.getString("SRATING");
			int spostcode = rs.getInt("SPOSTCODE");
			String sroadaddress = rs.getString("SROADADDRESS");
			String sjibunaddress = rs.getString("SJIBUNADDRESS");
			String sdetailaddress = rs.getString("SDETAILADDRESS");
			String sextraaddress = rs.getString("sextraaddress");
			int midx = rs.getInt("MIDX");
			Date sticketingdate = rs.getDate("STICKETINGDATE");
			
			sv = new ShowVo(sidx, stitle, sgenre, sregdate, spay, sdelyn, cidx, sopendate, senddate, srating, spostcode, sroadaddress, sjibunaddress, sdetailaddress, sextraaddress, midx, sticketingdate, sround, sprice, snotice, sdiscount, sinfo, scompany, sroundimage, spriceimage, snoticeimage, sdiscountimage, sinfoimage, scompanyimage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sv;
	}

	
	public ShowVo getRecentShowDetail() {
		
		ShowVo sv = null;
		
		String sql = "SELECT * FROM SHOW WHERE SIDX = (SELECT MAX(SIDX) FROM SHOW)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			int sidx = rs.getInt("SIDX");
			String stitle = rs.getString("STITLE");
			String sgenre = rs.getString("sgenre");
			Date sregdate = rs.getDate("SREGDATE");
			String sdelyn = rs.getString("SDELYN");
			int cidx = rs.getInt("CIDX");
			String sround = rs.getString("SROUND");
			String sprice = rs.getString("SPRICE");
			String snotice = rs.getString("SNOTICE");
			String sdiscount = rs.getString("SDISCOUNT");
			String sinfo = rs.getString("SINFO");
			String scompany = rs.getString("SCOMPANY");
			String sroundimage = rs.getString("SROUNDIMAGE");
			String spriceimage = rs.getString("SPRICEIMAGE");
			String snoticeimage = rs.getString("SNOTICEIMAGE");
			String sdiscountimage = rs.getString("SDISCOUNTIMAGE");
			String sinfoimage = rs.getString("SINFOIMAGE");
			String scompanyimage = rs.getString("SCOMPANYIMAGE");
			int spay = rs.getInt("SPAY");
			Date sopendate = rs.getDate("SOPENDATE");
			Date senddate = rs.getDate("SENDDATE");
			String srating = rs.getString("SRATING");
			int spostcode = rs.getInt("SPOSTCODE");
			String sroadaddress = rs.getString("SROADADDRESS");
			String sjibunaddress = rs.getString("SJIBUNADDRESS");
			String sdetailaddress = rs.getString("SDETAILADDRESS");
			String sextraaddress = rs.getString("sextraaddress");
			int midx = rs.getInt("MIDX");
			Date sticketingdate = rs.getDate("STICKETINGDATE");
			
			sv = new ShowVo(sidx, stitle, sgenre, sregdate, spay, sdelyn, cidx, sopendate, senddate, srating, spostcode, sroadaddress, sjibunaddress, sdetailaddress, sextraaddress, midx, sticketingdate, sround, sprice, snotice, sdiscount, sinfo, scompany, sroundimage, spriceimage, snoticeimage, sdiscountimage, sinfoimage, scompanyimage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sv;
		
	}
	
	public ArrayList<ShowRankingVo> getShowRankingList(String startdate) {
		
		ArrayList<ShowRankingVo> result = new ArrayList<>();
		
		String sql = "SELECT * FROM SHOW INNER JOIN "
				+ "(SELECT COUNT(*) CNT, SIDX FROM RESERVATION "
				+ "WHERE RREGDATE BETWEEN TO_DATE('"+ startdate +"', 'YYYY-MM-DD') AND TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS.FF9')"
				+ " GROUP BY SIDX ORDER BY CNT DESC) CNT "
				+ "ON SHOW.SIDX = CNT.SIDX "
				+ "WHERE SHOW.SENDDATE >= TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS.FF9') ORDER BY CNT.CNT DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int sidx = rs.getInt("SIDX");
				String stitle = rs.getString("STITLE");
				Date sopendate = rs.getDate("SOPENDATE");
				Date senddate = rs.getDate("SENDDATE");
				String sdetailaddress = rs.getString("SDETAILADDRESS");
				String simage = rs.getString("SIMAGE");
				int cnt = rs.getInt("CNT");
				Date n = null;
				
				ShowRankingVo srv = new ShowRankingVo(sidx, stitle, "", n, "", simage, 0, "", 0, sopendate, senddate, "", "", 0, "", "", sdetailaddress, "", 0, n, cnt);
				
				result.add(srv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int getShowDelete(int sidx) {
		
		int value = 0;
		String sql = "UPDATE SHOW SET SDELYN = 'Y' WHERE SIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
		
	}
	
}
