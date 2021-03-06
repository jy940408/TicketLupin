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
		//Ÿ��Ʋ, �帣, �ۼ���¥, ����, �̹���, ��������, ���³�¥, ������ ��¥, ���, ȸ��, �����ȣ, ���θ��ּ�, �����ּ�, ���ּ�, �����׸�
		String sql = "INSERT INTO SHOW(STITLE, SGENRE, SREGDATE, SCONTENT, SIMAGE, SDELYN, SOPENDATE, SENDDATE, SRATING, SROUND, SPOSTCODE, SROADADDRESS, SJIBUNADDRESS, SDETAILADDRESS, SEXTRAADDRESS, MIDX, STICKETINGDATE)"
				+ "VALUES(?, ?, sysdate, ?, ?, 'N', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sv.getStitle());
			pstmt.setString(2, sv.getSgenre());
			pstmt.setString(3, sv.getScontent());
			pstmt.setString(4, sv.getSimage());
			pstmt.setDate(5, sv.getSopendate());
			pstmt.setDate(6, sv.getSenddate());
			pstmt.setString(7, sv.getSrating());
			pstmt.setString(8, sv.getSround());
			pstmt.setInt(9, sv.getSpostcode());
			pstmt.setString(10, sv.getSroadaddress());
			pstmt.setString(11, sv.getSjibunaddress());
			pstmt.setString(12, sv.getSdetailaddress());
			pstmt.setString(13, sv.getSextraaddress());
			pstmt.setInt(14, sv.getMidx());
			pstmt.setDate(15, sv.getSticketingdate());
			ResultSet rs = pstmt.executeQuery();
			System.out.println("ShowDAO �׽�Ʈ: " + sv.getStitle());
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
				String scontent = rs.getString("SCONTENT");
				String simage = rs.getString("SIMAGE");
				int spay = rs.getInt("SPAY");
				String sdelyn = rs.getString("SDELYN");
				int cidx = rs.getInt("CIDX");
				Date sopendate = rs.getDate("SOPENDATE");
				Date senddate = rs.getDate("SENDDATE");
				String srating = rs.getString("SRATING");
				String sround = rs.getString("SROUND");
				int spostcode = rs.getInt("SPOSTCODE");
				String sroadaddress = rs.getString("SROADADDRESS");
				String sjibunaddress = rs.getString("SJIBUNADDRESS");
				String sdetailaddress = rs.getString("SDETAILADDRESS");
				String sextraaddress = rs.getString("sextraaddress");
				int midx = rs.getInt("MIDX");
				Date sticketingdate = rs.getDate("STICKETINGDATE");
				
				ShowVo sv = new ShowVo(sidx, stitle, sgenre, sregdate, scontent, simage, spay, sdelyn, cidx, sopendate, senddate, srating, sround, spostcode, sroadaddress, sjibunaddress, sdetailaddress, sextraaddress, midx, sticketingdate);
				
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
}
