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
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM WINNER WHERE ITITLE LIKE ? ORDER BY IREGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";
		
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
				
				WinnerVo wv = new WinnerVo(iidx, ititle, icontent, midx, iregdate, ihit, iimage, ifiles, ipub, igood, idelyn);
				
				list.add(wv);
				}
		
		}catch (SQLException e) {
				e.printStackTrace();
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
		}
		
		return count;
	}		
	
}
