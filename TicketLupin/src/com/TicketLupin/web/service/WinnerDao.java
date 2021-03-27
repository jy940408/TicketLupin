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
	private ResultSet rs;
	
	public WinnerDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
		ResultSet rs;
	}
	
	public List<WinnerVo> getWinnerList(String query, int page){
		
		List<WinnerVo> list = new ArrayList<WinnerVo>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM WINNER WHERE ITITLE LIKE ? AND IPUB = 'Y' AND IDELYN = 'N' ORDER BY IREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			
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
				
				WinnerVo wv = new WinnerVo(iidx, ititle, icontent, midx, iregdate, ihit, iimage, ifiles, ipub, igood, idelyn, iopendate, ienddate);
				
				list.add(wv);
				}

		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}		
	
	public int getWinnerListCount(String query, int p){
		
		int count = 0;
		
		String sql = "SELECT COUNT(IIDX) COUNT FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM WINNER WHERE ITITLE LIKE ? ORDER BY IREGDATE DESC) N)";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt("COUNT");
							
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}		
	
	public WinnerVo getWinnerDetail(int idx) {
		
		WinnerVo winnervo = new WinnerVo();
		
		String sql = "SELECT * FROM WINNER WHERE IIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
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
			
			winnervo = new WinnerVo(iidx, ititle, icontent, midx, iregdate, ihit, iimage, ifiles, ipub, igood, idelyn, iopendate, ienddate);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return winnervo;
	}
	
	public int insertWinner(WinnerVo wv) {
		int result = 0;
		//인덱스, 타이틀, 내용, 작성자인덱스, 작성일, 조회수, 이미지 첨부, 파일 첨부, 공개여부, 좋아요, 삭제여부, 시작 날짜, 끝 날짜
		String sql = "INSERT INTO WINNER VALUES('', ?, ?, ?, SYSDATE, 1, ?, '123', ?, 1, 'N', ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, wv.getItitle());
			pstmt.setString(2, wv.getIcontent());
			pstmt.setInt(3, wv.getMidx());
			pstmt.setString(4, wv.getIimage());
			pstmt.setString(5, wv.getIpub());
			pstmt.setDate(6, wv.getIopendate());
			pstmt.setDate(7, wv.getIenddate());
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
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
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteWinner(int idx) {
		int result = 0;
		
		String sql = "UPDATE WINNER SET IDELYN = 'Y' WHERE IIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
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
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(wv.getIhit());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
