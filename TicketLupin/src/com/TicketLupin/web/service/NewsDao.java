package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;
import com.TicketLupin.web.service.NewsVo;
public class NewsDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public NewsDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public List<NewsVo> getNewsList(String query, String order, String setting, int page){
		
		List<NewsVo> list = new ArrayList<NewsVo>();
		
		String sql = "SELECT * FROM NEWS WHERE WTITLE LIKE ? AND WCATEGORY LIKE ? AND WDELYN = 'N' " 
				+ "ORDER BY WREGDATE DESC LIMIT ?, 10";

		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setString(2, "%"+setting+"%");
			pstmt.setInt(3, 10*(page-1));
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				int widx = rs.getInt("WIDX");
				String wtitle = rs.getString("WTITLE");
				String wbasicinfo = rs.getString("WBASICINFO");
				String wintroduce = rs.getString("WINTRODUCE");
				String wdiscount = rs.getString("WDISCOUNT");
				String wcompany = rs.getString("WCOMPANY");
				String wcategory = rs.getString("WCATEGORY");
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
				
				NewsVo nv = new NewsVo(widx, wtitle, wbasicinfo, wintroduce, wdiscount, wcompany, wcategory, midx, wregdate, whit, wimage, wfiles, wpub, wgood, wdelyn, wtitleposter, wopendate);
				
				list.add(nv);
			}
		
			
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return list;
	}		
	
	public List<NewsVo> getNewsImageList(){
		
		List<NewsVo> list = new ArrayList<NewsVo>();
		
		String sql = "SELECT * FROM NEWS WHERE WCATEGORY LIKE '티켓오픈일' "
				+ "AND WDELYN = 'N' ORDER BY WOPENDATE DESC LIMIT 0,20";

		
		try {
		
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				NewsVo nv = new NewsVo();
				
				nv.setWidx(rs.getInt("WIDX"));
				nv.setWtitleposter(rs.getString("WTITLEPOSTER"));
				
				list.add(nv);
			}
		
			
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return list;
	}		
	
	public int getNewsListCount(String query, String setting, String order){
		
		int count = 0;
		
		String sql = "SELECT COUNT(*) COUNT FROM "
				+ "(SELECT @ROWNUM:=@ROWNUM+1 NUM, W.* FROM "
				+ "(SELECT * FROM NEWS WHERE WTITLE LIKE  ? "
				+ "AND WCATEGORY LIKE ? AND WDELYN = 'N' "
				+ "ORDER BY " + order + " DESC) W WHERE (@ROWNUM:=0)=0) A";

		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setString(2, "%"+setting+"%");
			
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
			String wbasicinfo = rs.getString("WBASICINFO");
			String wintroduce = rs.getString("WINTRODUCE");
			String wdiscount = rs.getString("WDISCOUNT");
			String wcompany = rs.getString("WCOMPANY");
			String wcategory = rs.getString("WCATEGORY");
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
			
			newsvo = new NewsVo(widx, wtitle, wbasicinfo, wintroduce, wdiscount, wcompany, wcategory, midx, wregdate, whit, wimage, wfiles, wpub, wgood, wdelyn, wtitleposter, wopendate);
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return newsvo;
	}		
	
	public int insertNews(NewsVo nv) {
		int result = 0;
		//占싸듸옙占쏙옙, 타占쏙옙틀, 占썩본 占쏙옙占쏙옙, 占쏙옙占쏙옙琯占쏙옙占�, 占쏙옙毬占승�, 占쏙옙회占쏙옙, 占싱뱄옙占쏙옙, 첨占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占싣울옙 占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占승놂옙짜, 타占쏙옙틀占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쌀곤옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙, 카占쌓곤옙
		String sql = "INSERT INTO NEWS(WTITLE, WBASICINFO, MIDX, WREGDATE, WHIT, WIMAGE, WFILES, "
				+ "WPUB, WGOOD, WDELYN, WOPENDATE, WTITLEPOSTER, WINTRODUCE, "
				+ "WDISCOUNT, WCOMPANY, WCATEGORY) "
				+ "VALUES(?, ?, ?, NOW(), 1, ?, ?, ?, 0, 'N', ?, ?, ?, ?, ?, ?)";

				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nv.getWtitle());
			pstmt.setString(2, nv.getWbasicinfo());
			pstmt.setInt(3, nv.getMidx());
			pstmt.setString(4, nv.getWimage());
			pstmt.setString(5, nv.getWfiles());
			pstmt.setString(6, nv.getWpub());
			pstmt.setDate(7, nv.getWopendate());
			pstmt.setString(8, nv.getWtitleposter());
			pstmt.setString(9, nv.getWintroduce());
			pstmt.setString(10, nv.getWdiscount());
			pstmt.setString(11, nv.getWcompany());
			pstmt.setString(12, nv.getWcategory());
			
			result = pstmt.executeUpdate();
			System.out.println(nv.getWtitle());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteNews(int idx) {
		int result = 0;
		
		String sql = "UPDATE NEWS SET WDELYN = 'Y' WHERE WIDX = ?";
		
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
	public int modifyNews(NewsVo nv) {
		int result = 0;
		//占싸듸옙占쏙옙, 타占쏙옙틀, 占썩본 占쏙옙占쏙옙, 占쏙옙占쏙옙琯占쏙옙占�, 占쏙옙毬占승�, 占쏙옙회占쏙옙, 占싱뱄옙占쏙옙, 첨占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占싣울옙 占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占승놂옙짜, 타占쏙옙틀占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쌀곤옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙, 카占쌓곤옙
		String sql = "UPDATE NEWS SET WTITLE = ?, WBASICINFO = ?, WTITLEPOSTER = ?, "
				+ "WPUB = ?, WOPENDATE = ?, WINTRODUCE = ?, WDISCOUNT = ?, "
						+ "WCOMPANY = ? WHERE WIDX = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nv.getWtitle());
			pstmt.setString(2, nv.getWbasicinfo());
			pstmt.setString(3, nv.getWtitleposter());
			pstmt.setString(4, nv.getWpub());
			pstmt.setDate(5, nv.getWopendate());
			pstmt.setString(6, nv.getWintroduce());
			pstmt.setString(7, nv.getWdiscount());
			pstmt.setString(8, nv.getWcompany());
			pstmt.setInt(9, nv.getWidx());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public int countNewsView(int widx) {
		int result = 0;
		NewsVo nv = new NewsVo();
		
		String sql = "UPDATE NEWS SET WHIT = WHIT + 1 WHERE WIDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, widx);
			
			result = pstmt.executeUpdate();
			System.out.println(nv.getWhit());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
