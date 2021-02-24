package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;

public class NewsDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	
	public NewsDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public List<NewsVo> getNewsList(String query, String setting, int page){
		
		List<NewsVo> list = new ArrayList<NewsVo>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, W.* FROM (SELECT * FROM NEWS WHERE WTITLE LIKE ? ORDER BY " + setting + " DESC) W) WHERE NUM BETWEEN ? AND ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				int widx = rs.getInt("WIDX");
				String wtitle = rs.getString("WTITLE");
				String wcontent = rs.getString("WCONTENT");
				int midx = rs.getInt("MIDX");
				Date wregdate = rs.getDate("WREGDATE");
				int whit = rs.getInt("WHIT");
				String wimage = rs.getString("WIMAGE");
				String wfiles = rs.getString("WFILES");
				String wpub = rs.getString("WPUB");
				int wgood = rs.getInt("WGOOD");
				String wdelyn = rs.getString("WDELYN");
				String wtitleposter = rs.getString("WTITLEPOSTER");
				Date wopendate = rs.getDate("WOPENDATE");
				
				NewsVo nv = new NewsVo(widx, wtitle, wcontent, midx, wregdate, whit, wimage, wfiles, wpub, wgood, wdelyn, wtitleposter, wopendate);
				
				list.add(nv);
				}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return list;
	}		
	
	public int getNewsListCount(String query, String setting){
		
		int count = 0;
		
		String sql = "SELECT COUNT(WIDX) COUNT FROM (SELECT ROWNUM NUM, W.* FROM (SELECT * FROM NEWS WHERE WTITLE LIKE ? ORDER BY ? DESC) W)";
		
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
	
	public NewsVo getNewsDetail(int idx){
		
		NewsVo newsvo = new NewsVo();
		
		String sql = "SELECT * FROM NEWS WHERE WIDX = ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			int widx = rs.getInt("WIDX");
			String wtitle = rs.getString("WTITLE");
			String wcontent = rs.getString("WCONTENT");
			int midx = rs.getInt("MIDX");
			Date wregdate = rs.getDate("WREGDATE");
			int whit = rs.getInt("WHIT");
			String wimage = rs.getString("WIMAGE");
			String wfiles = rs.getString("WFILES");
			String wpub = rs.getString("WPUB");
			int wgood = rs.getInt("WGOOD");
			String wdelyn = rs.getString("WDELYN");
			String wtitleposter = rs.getString("WTITLEPOSTER");
			Date wopendate = rs.getDate("WOPENDATE");
			
			newsvo = new NewsVo(widx, wtitle, wcontent, midx, wregdate, whit, wimage, wfiles, wpub, wgood, wdelyn, wtitleposter, wopendate);
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return newsvo;
	}		
	
}
