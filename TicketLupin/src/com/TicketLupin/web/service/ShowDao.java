package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.TicketLupin.web.DBconn.DBconn;

public class ShowDao {

	private	Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ShowDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public int insertShow(Show1Vo sv) {
		int result = 0;
		//타占쏙옙틀, 占썲르, 占쌜쇽옙占쏙옙짜, 占쏙옙占쏙옙, 占싱뱄옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占승놂옙짜, 占쏙옙占쏙옙占쏙옙 占쏙옙짜, 占쏙옙占�, 회占쏙옙, 占쏙옙占쏙옙占싫�, 占쏙옙占싸몌옙占쌍쇽옙, 占쏙옙占쏙옙占쌍쇽옙, 占쏙옙占쌍쇽옙, 占쏙옙占쏙옙占쌓몌옙
		String sql = "INSERT INTO SHOW1 (STITLE, SGENRE, SREGDATE, SOPENDATE, SENDDATE, SRATING, SPOSTCODE, SROADADDRESS, SJIBUNADDRESS, SDETAILADDRESS, SEXTRAADDRESS, "
				+ "MIDX, STICKETINGDATE, SVIPPRICE, SRPRICE, SSPRICE, SAPRICE, SDELYN) "
				+ "VALUES(?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N')";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sv.getStitle());
			pstmt.setString(2, sv.getSgenre());
			pstmt.setDate(3, sv.getSopendate());
			pstmt.setDate(4, sv.getSenddate());
			pstmt.setString(5, sv.getSrating());
			pstmt.setInt(6, sv.getSpostcode());
			pstmt.setString(7, sv.getSroadaddress());
			pstmt.setString(8, sv.getSjibunaddress());
			pstmt.setString(9, sv.getSdetailaddress());
			pstmt.setString(10, sv.getSextraaddress());
			pstmt.setInt(11, sv.getMidx());
			pstmt.setDate(12, sv.getSticketingdate());
			pstmt.setInt(13, sv.getSvipprice());
			pstmt.setInt(14, sv.getSrprice());
			pstmt.setInt(15, sv.getSsprice());
			pstmt.setInt(16, sv.getSaprice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertShow2(Show2Vo sv) {
		int result = 0;
		//타占쏙옙틀, 占썲르, 占쌜쇽옙占쏙옙짜, 占쏙옙占쏙옙, 占싱뱄옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占승놂옙짜, 占쏙옙占쏙옙占쏙옙 占쏙옙짜, 占쏙옙占�, 회占쏙옙, 占쏙옙占쏙옙占싫�, 占쏙옙占싸몌옙占쌍쇽옙, 占쏙옙占쏙옙占쌍쇽옙, 占쏙옙占쌍쇽옙, 占쏙옙占쏙옙占쌓몌옙
		String sql = "INSERT INTO SHOW2 (SIDX, SROUND, SPRICE, SNOTICE, SDISCOUNT, SINFO, SCOMPANY, "
				+ "STITLEIMAGE, SROUNDIMAGE, SPRICEIMAGE, SNOTICEIMAGE, SDISCOUNTIMAGE, SINFOIMAGE, SCOMPANYIMAGE) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sv.getSidx());
			pstmt.setString(2, sv.getSround());
			pstmt.setString(3, sv.getSprice());
			pstmt.setString(4, sv.getSnotice());
			pstmt.setString(5, sv.getSdiscount());
			pstmt.setString(6, sv.getSinfo());
			pstmt.setString(7, sv.getScompany());
			pstmt.setString(8, sv.getStitleimage());
			pstmt.setString(9, sv.getSroundimage());
			pstmt.setString(10, sv.getSpriceimage());
			pstmt.setString(11, sv.getSnoticeimage());
			pstmt.setString(12, sv.getSdiscountimage());
			pstmt.setString(13, sv.getSinfoimage());
			pstmt.setString(14, sv.getScompanyimage());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Show1Vo> getShowList(String query, String setting, String array, int page){
		
		ArrayList<Show1Vo> list = new ArrayList<>();

//		String sql ="SELECT * FROM "
//				+ "(SELECT @ROWNUM:=@ROWNUM+1 as NUM, S.* FROM "
//				+ "(SELECT SHOW1.*, SHOW2.STITLEIMAGE FROM "
//				+ "SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX WHERE "
//				+ "STITLE LIKE ? AND SHOW1.SDELYN = 'N' "
//				+ "ORDER BY SREGDATE DESC) S WHERE (@ROWNUM:=0)=0) A "
//				+ "WHERE SDELYN = 'N' "
//				+ "WHERE NUM >= 1 AND  NUM <= 10";
		
		String sql = "SELECT SHOW1.*, SHOW2.STITLEIMAGE FROM SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX " + 
				"WHERE  STITLE LIKE ? AND  SHOW1.SDELYN = 'N' ORDER BY SREGDATE DESC limit ?,?";
	

		System.out.println("sql:"+sql);
		System.out.println("query::"+query);
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 12*(page-1));
			pstmt.setInt(3, 12);
			
			ResultSet rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				Show1Vo sv = new Show1Vo();
				
				sv.setSidx(rs.getInt("SIDX"));
				sv.setStitle(rs.getString("STITLE"));
				sv.setSgenre(rs.getString("SGENRE"));
				sv.setSregdate(rs.getDate("SREGDATE"));
				sv.setSopendate(rs.getDate("SOPENDATE"));
				sv.setSenddate(rs.getDate("SENDDATE"));
				sv.setSrating(rs.getString("SRATING"));
				sv.setSpostcode(rs.getInt("SPOSTCODE"));
				sv.setSroadaddress(rs.getString("SROADADDRESS"));
				sv.setSjibunaddress(rs.getString("SJIBUNADDRESS"));
				sv.setSdetailaddress(rs.getString("SDETAILADDRESS"));
				sv.setSextraaddress(rs.getString("SEXTRAADDRESS"));
				sv.setMidx(rs.getInt("MIDX"));
				sv.setSticketingdate(rs.getDate("STICKETINGDATE"));
				sv.setSvipprice(rs.getInt("SVIPPRICE"));
				sv.setSrprice(rs.getInt("SRPRICE"));
				sv.setSsprice(rs.getInt("SSPRICE"));
				sv.setSaprice(rs.getInt("SAPRICE"));
				sv.setSdelyn(rs.getString("SDELYN"));
				sv.setStitleimage(rs.getString("STITLEIMAGE"));
				list.add(sv);
				i++;
			}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		System.out.println("list 테스트: "+list);
		return list;
		
	}
	
	public JSONArray getShowListAJAX(ArrayList genre, ArrayList place, String setting, String array, int page, int count){
		
		JSONArray objList = new JSONArray();
		
		String genreSql = null;
		String placeSql = null;
		String soldSql = null;
		
		if(genre.size() == 0) {
			genreSql = "";
		}else if(genre.size() == 1) {
			genreSql = "AND (SGENRE LIKE '%" + genre.get(0) + "%') ";
		}else{
			for(int i = 0 ; i < genre.size() ; i++) {
				if(i == 0) {
					genreSql = "AND (SGENRE LIKE '%" + genre.get(i) + "%' ";
				}else if(i == (genre.size()-1)) {
					genreSql += "OR SGENRE LIKE '%" +  genre.get(i) +  "%') ";
				}else{
					genreSql += "OR SGENRE LIKE '%" +  genre.get(i) +  "%' ";
				}
			}
		}
		
		if(place.size() == 0) {
			placeSql = "";
		}else if(place.size() == 1) {
			placeSql = "AND (SROADADDRESS LIKE '%" + place.get(0) + "%') ";
		}else{
			for(int i = 0 ; i < place.size() ; i++) {
				if(i == 0) {
					placeSql = "AND (SROADADDRESS LIKE '%" + place.get(i) + "%' ";
				}else if(i == (place.size()-1)) {
					placeSql += "OR SROADADDRESS LIKE '%" +  place.get(i) +  "%') ";
				}else{
					placeSql += "OR SROADADDRESS LIKE '%" +  place.get(i) +  "%' ";
				}
			}
		}
		
		System.out.println("sql 테스트: " + genreSql + placeSql);
		
		String sql = "SELECT S.* FROM " 
				+ "(SELECT SHOW1.*, SHOW2.STITLEIMAGE FROM "
				+ "SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX " 
				+ "WHERE STITLE LIKE '%%' AND SHOW1.SDELYN = 'N' "
				+ genreSql
				+ placeSql
				+ "ORDER BY " + setting + " " + array + ") S "
				+ "LIMIT ?, 12";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 12*(page-1));
			ResultSet rs = pstmt.executeQuery();
			
			JSONObject obj = new JSONObject();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				
				int i = 0;
				JSONObject obj_ = new JSONObject();
				obj_.put("sidx", rs.getInt("SIDX"));
				obj_.put("stitle", rs.getString("STITLE"));
				obj_.put("sopendate", transFormat.format(rs.getDate("SOPENDATE")));
				obj_.put("senddate", transFormat.format(rs.getDate("SENDDATE")));
				obj_.put("sdetailaddress", rs.getString("SDETAILADDRESS"));
				obj_.put("stitleimage", rs.getString("STITLEIMAGE"));
				obj_.put("sticketingdate", transFormat.format(rs.getDate("STICKETINGDATE")));
				obj_.put("count", count);
				obj_.put("page", page);
				objList.add(obj_);
				
				i++;
			}
			
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return objList;
		
	}
	
	public int getShowListAJAXCount(ArrayList genre, ArrayList place, String setting, String array){
		
		int count = 0;
		
		String genreSql = null;
		String placeSql = null;
		String soldSql = null;
		
		if(genre.size() == 0) {
			genreSql = "";
		}else if(genre.size() == 1) {
			genreSql = "AND (SGENRE LIKE '%" + genre.get(0) + "%') ";
		}else{
			for(int i = 0 ; i < genre.size() ; i++) {
				if(i == 0) {
					genreSql = "AND (SGENRE LIKE '%" + genre.get(i) + "%' ";
				}else if(i == (genre.size()-1)) {
					genreSql += "OR SGENRE LIKE '%" +  genre.get(i) +  "%') ";
				}else{
					genreSql += "OR SGENRE LIKE '%" +  genre.get(i) +  "%' ";
				}
			}
		}
		
		if(place.size() == 0) {
			placeSql = "";
		}else if(place.size() == 1) {
			placeSql = "AND (SROADADDRESS LIKE '%" + place.get(0) + "%') ";
		}else{
			for(int i = 0 ; i < place.size() ; i++) {
				if(i == 0) {
					placeSql = "AND (SROADADDRESS LIKE '%" + place.get(i) + "%' ";
				}else if(i == (place.size()-1)) {
					placeSql += "OR SROADADDRESS LIKE '%" +  place.get(i) +  "%') ";
				}else{
					placeSql += "OR SROADADDRESS LIKE '%" +  place.get(i) +  "%' ";
				}
			}
		}
		
		System.out.println("place.size(): " + place.size());
		System.out.println("sql 테스트: " + genreSql + placeSql);
		
		String sql = "SELECT COUNT(*) COUNT FROM " 
				+ "(SELECT SHOW1.*, SHOW2.STITLEIMAGE FROM "
				+ "SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX " 
				+ "WHERE STITLE LIKE '%%' AND SHOW1.SDELYN = 'N' "
				+ genreSql
				+ placeSql
				+ "ORDER BY " + setting + " " + array + ") S";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			JSONObject obj = new JSONObject();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				
				count = rs.getInt("COUNT");
				
			}
			
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		System.out.println("리스트 카운트 테스트: " + count);
		return count;
		
	}
	
	public int getShowListCount(String query, String setting){
		
		int count = 0;
		
		String sql = "SELECT COUNT(*) COUNT FROM SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX " + 
				"WHERE  STITLE LIKE ? AND SHOW1.SDELYN = 'N' ORDER BY SREGDATE DESC";

		
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
		System.out.println("count 테스트: " + count);
		
		return count;
	}

	public Show1Vo getShowDetail(int idx) {
		Show1Vo sv = new Show1Vo();
		
		String sql = "SELECT * FROM SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX WHERE SHOW1.SIDX =  ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			sv.setSidx(rs.getInt("SIDX"));
			sv.setStitle(rs.getString("STITLE"));
			sv.setSgenre(rs.getString("sgenre"));
			sv.setSregdate(rs.getDate("SREGDATE"));
			sv.setSopendate(rs.getDate("SOPENDATE"));
			sv.setSenddate(rs.getDate("SENDDATE"));
			sv.setSrating(rs.getString("SRATING"));
			sv.setSpostcode(rs.getInt("SPOSTCODE"));
			sv.setSroadaddress(rs.getString("SROADADDRESS"));
			sv.setSjibunaddress(rs.getString("SJIBUNADDRESS"));
			sv.setSdetailaddress(rs.getString("SDETAILADDRESS"));
			sv.setSextraaddress(rs.getString("SEXTRAADDRESS"));
			sv.setMidx(rs.getInt("MIDX"));
			sv.setSticketingdate(rs.getDate("STICKETINGDATE"));
			sv.setSvipprice(rs.getInt("SVIPPRICE"));
			sv.setSrprice(rs.getInt("SRPRICE"));
			sv.setSsprice(rs.getInt("SSPRICE"));
			sv.setSaprice(rs.getInt("SAPRICE"));
			sv.setSdelyn(rs.getString("SDELYN"));
			sv.setSround(rs.getString("SROUND"));
			sv.setSprice(rs.getString("SPRICE"));
			sv.setSnotice(rs.getString("SNOTICE"));
			sv.setSdiscount(rs.getString("SDISCOUNT"));
			sv.setSinfo(rs.getString("SINFO"));
			sv.setScompany(rs.getString("SCOMPANY"));
			sv.setStitleimage(rs.getString("STITLEIMAGE"));
			sv.setSroundimage(rs.getString("SROUNDIMAGE"));
			sv.setSpriceimage(rs.getString("SPRICEIMAGE"));
			sv.setSnoticeimage(rs.getString("SNOTICEIMAGE"));
			sv.setSdiscountimage(rs.getString("SDISCOUNTIMAGE"));
			sv.setSinfoimage(rs.getString("SINFOIMAGE"));
			sv.setScompanyimage(rs.getString("SCOMPANYIMAGE"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sv;
	}

	
	public Show1Vo getRecentShowDetail() {
		
		Show1Vo sv = new Show1Vo();
		
		String sql = "SELECT * FROM SHOW1 WHERE SIDX = (SELECT MAX(SIDX) FROM SHOW1)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			sv.setSidx(rs.getInt("SIDX"));
			sv.setSopendate(rs.getDate("SOPENDATE"));
			sv.setSenddate(rs.getDate("SENDDATE"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sv;
		
	}
	
	public ArrayList<ShowRankingVo> getShowRankingList(String startdate) {
		
		ArrayList<ShowRankingVo> result = new ArrayList<>();
		
		String sql = "SELECT SHOWCOM.* FROM "
				+ "(SELECT SHOW1.*, SHOW2.STITLEIMAGE FROM "
				+ "SHOW1 INNER JOIN SHOW2 ON SHOW1.SIDX = SHOW2.SIDX WHERE SHOW1.SDELYN = 'N') SHOWCOM INNER JOIN "
				+ "(SELECT COUNT(*) CNT, SIDX FROM RESERVATION "
				+ "WHERE RDELYN = 'N' AND RREGDATE BETWEEN STR_TO_DATE('"+ startdate +"', '%Y-%m-%d %H:%i:%s') AND STR_TO_DATE(NOW(), '%Y-%m-%d %H:%i:%s') "
				+ "GROUP BY SIDX ORDER BY CNT DESC) CNT "
				+ "ON SHOWCOM.SIDX = CNT.SIDX "
				+ "WHERE SHOWCOM.SENDDATE >= STR_TO_DATE(NOW(), '%Y-%m-%d %H:%i:%s') "
				+ "ORDER BY CNT.CNT DESC LIMIT 0, 10";

		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShowRankingVo srv = new ShowRankingVo();
				srv.setSidx(rs.getInt("SIDX"));
				srv.setStitle(rs.getString("STITLE"));
				srv.setSopendate(rs.getDate("SOPENDATE"));
				srv.setSenddate(rs.getDate("SENDDATE"));
				srv.setSdetailaddress(rs.getString("SDETAILADDRESS"));
				srv.setStitleimage(rs.getString("STITLEIMAGE"));
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
		String sql = "UPDATE SHOW1 SET SDELYN = 'Y' WHERE SIDX = ?";
		
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
	
	public int modifyShow(Show1Vo sv) {
		int result = 0;
		String sql = "UPDATE SHOW1 SET STITLE = ?, SGENRE = ?, SRATING = ?, SOPENDATE = ?, SENDDATE = ?, STICKETINGDATE = ?, "
				+ "SPOSTCODE = ?, SROADADDRESS = ?, SJIBUNADDRESS = ?, SDETAILADDRESS = ?, SEXTRAADDRESS = ?, "
				+ "SVIPPRICE = ?, SRPRICE = ?, SSPRICE = ?, SAPRICE = ? WHERE SIDX = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sv.getStitle());
			pstmt.setString(2, sv.getSgenre());
			pstmt.setString(3, sv.getSrating());
			pstmt.setDate(4, sv.getSopendate());
			pstmt.setDate(5, sv.getSenddate());
			pstmt.setDate(6, sv.getSticketingdate());
			pstmt.setInt(7, sv.getSpostcode());
			pstmt.setString(8, sv.getSroadaddress());
			pstmt.setString(9, sv.getSjibunaddress());
			pstmt.setString(10, sv.getSdetailaddress());
			pstmt.setString(11, sv.getSextraaddress());
			pstmt.setInt(12, sv.getSvipprice());
			pstmt.setInt(13, sv.getSrprice());
			pstmt.setInt(14, sv.getSsprice());
			pstmt.setInt(15, sv.getSaprice());
			pstmt.setInt(16, sv.getSidx());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int modifyShow2(Show2Vo sv) {
		int result = 0;
		String sql = "UPDATE SHOW2 SET SROUND = ?, SPRICE = ?, SNOTICE = ?, SDISCOUNT = ?, SINFO = ?, SCOMPANY = ?, " + 
				"STITLEIMAGE = ?, SROUNDIMAGE = ?, SPRICEIMAGE = ?, SNOTICEIMAGE = ?, SDISCOUNTIMAGE = ?, " + 
				"SINFOIMAGE = ?, SCOMPANYIMAGE = ? WHERE SIDX = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sv.getSround());
			pstmt.setString(2, sv.getSprice());
			pstmt.setString(3, sv.getSnotice());
			pstmt.setString(4, sv.getSdiscount());
			pstmt.setString(5, sv.getSinfo());
			pstmt.setString(6, sv.getScompany());
			pstmt.setString(7, sv.getStitleimage());
			pstmt.setString(8, sv.getSroundimage());
			pstmt.setString(9, sv.getSpriceimage());
			pstmt.setString(10, sv.getSnoticeimage());
			pstmt.setString(11, sv.getSdiscountimage());
			pstmt.setString(12, sv.getSinfoimage());
			pstmt.setString(13, sv.getScompanyimage());
			pstmt.setInt(14, sv.getSidx());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void closeDBconn() {
		
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
