package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class WinnerDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	
	public WinnerDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public List<WinnerVo> getWinnerList(String query, int page){
		
		List<WinnerVo> list = new ArrayList<WinnerVo>();
		
//		String sql = "SELECT * FROM "
//				+ "(SELECT @ROWNUM:=@ROWNUM+1 NUM, N.* FROM (SELECT * FROM "
//				+ "WINNER WHERE ITITLE LIKE ? AND IPUB = 'Y' AND IDELYN = 'N' "
//				+ "ORDER BY IREGDATE DESC) N WHERE (@ROWNUM:=0)=0) A "
//				+ "WHERE NUM BETWEEN ? AND ?";
		
		String sql = "SELECT * FROM "
				+ "(SELECT @ROWNUM:=@ROWNUM + 1 NUM, WINNER.* FROM "
				+ "WINNER, (SELECT @ROWNUM:=0) TMP "
				+ "WHERE ITITLE LIKE ? AND IPUB = 'Y' "
				+ "AND IDELYN = 'N' ORDER BY IREGDATE) SUB "
				+ "ORDER BY SUB.NUM DESC LIMIT ?, 10";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 10*(page-1));
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				int iidx = rs.getInt("IIDX");
				String ititle = rs.getString("ITITLE");
				String icontent = rs.getString("ICONTENT");
				int midx = rs.getInt("MIDX");
				Date iregdate = rs.getDate("IREGDATE");
				int ihit = rs.getInt("IHIT");
				String iimage = rs.getString("IIMAGE");
				String ifiles = rs.getString("IFILES");
				String ipub = rs.getString("IPUB");
				int igood = rs.getInt("IGOOD");
				String idelyn = rs.getString("IDELYN");
				Date iopendate = rs.getDate("IOPENDATE");
				Date ienddate = rs.getDate("IENDDATE");
				int num = rs.getInt("NUM");
				
				WinnerVo wv = new WinnerVo(iidx, ititle, icontent, midx, iregdate, ihit, iimage, ifiles, ipub, igood, idelyn, iopendate, ienddate, num);
				
				list.add(wv);
				}

		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return list;
	}		
	
	public int getWinnerListCount(String query){
		
		int count = 0;
		
		String sql = "SELECT COUNT(IIDX) COUNT FROM "
				+ "(SELECT @ROWNUM:=@ROWNUM+1 NUM, N.* FROM "
				+ "(SELECT * FROM WINNER WHERE ITITLE LIKE ? AND IDELYN = 'N' AND (@ROWNUM:=0)=0 "
				+ "ORDER BY IREGDATE DESC) N) A";

		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
							
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return count;
	}		
	
	public WinnerVo getWinnerDetail(int idx) {
		
		WinnerVo wv = new WinnerVo();
		
		String sql = "SELECT * FROM WINNER WHERE IIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			wv.setIidx(rs.getInt("IIDX"));
			wv.setItitle(rs.getString("ITITLE"));
			wv.setIcontent(rs.getString("ICONTENT"));
			wv.setMidx(rs.getInt("MIDX"));
			wv.setIregdate(rs.getDate("IREGDATE"));
			wv.setIhit(rs.getInt("IHIT"));
			wv.setIimage(rs.getString("IIMAGE"));
			wv.setIfiles(rs.getString("IFILES"));
			wv.setIpub(rs.getString("IPUB"));
			wv.setIgood(rs.getInt("IGOOD"));
			wv.setIdelyn(rs.getString("IDELYN"));
			wv.setIopendate(rs.getDate("IOPENDATE"));
			wv.setIenddate(rs.getDate("IENDDATE"));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wv;
	}
	
	public int insertWinner(WinnerVo wv) {
		int result = 0;
		//�ε���, Ÿ��Ʋ, ����, �ۼ����ε���, �ۼ���, ��ȸ��, �̹��� ÷��, ���� ÷��, ��������, ���ƿ�, ��������, ���� ��¥, �� ��¥
		String sql = "INSERT INTO WINNER (ITITLE, ICONTENT, MIDX, IREGDATE, IHIT, IIMAGE, IFILES, IPUB, IGOOD, IDELYN, IOPENDATE, IENDDATE) "
				+ "VALUES (?, ?, ?, NOW(), 0, ?, ?, 'Y', 0, 'N', ?, ?)";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, wv.getItitle());
			pstmt.setString(2, wv.getIcontent());
			pstmt.setInt(3, wv.getMidx());
			pstmt.setString(4, wv.getIimage());
			pstmt.setString(5, wv.getIpub());
			pstmt.setDate(6, wv.getIopendate());
			pstmt.setDate(7, wv.getIenddate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int modifyWinner(WinnerVo wv) {
		int result = 0;
		
		String sql = "UPDATE WINNER SET ITITLE = ?, ICONTENT = ?, IIMAGE = ?, IPUB = ?, IOPENDATE = ?, IENDDATE = ? WHERE IIDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, wv.getItitle());
			pstmt.setString(2, wv.getIcontent());
			pstmt.setString(3, wv.getIimage());
			pstmt.setString(4, wv.getIpub());
			pstmt.setDate(5, wv.getIopendate());
			pstmt.setDate(6, wv.getIenddate());
			pstmt.setInt(7, wv.getIidx());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteWinner(int idx) {
		int result = 0;
		
		String sql = "UPDATE WINNER SET IDELYN = 'Y' WHERE IIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int countWinnerView(int iidx) {
		int result = 0;
		WinnerVo wv = new WinnerVo();
		
		String sql = "UPDATE WINNER SET IHIT = IHIT + 1 WHERE IIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, iidx);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
