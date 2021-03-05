package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TicketLupin.web.DBconn.DBconn;

import domain.SearchCriteria;

public class NoticeDao {
	
	private	Connection conn;
	private PreparedStatement pstmt;
	
	public NoticeDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	
	public ArrayList<NoticeVo> getNoticeList(SearchCriteria scri){
		
		ArrayList<NoticeVo> alist = new ArrayList<NoticeVo>();
		
		String sql = "select A.* from (select rownum as rnum, A.* from (select * from notice order by nidx) A where rownum <= ?) A where rnum >= ?";
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scri.getPage()*10);
			pstmt.setInt(2, (scri.getPage()-1)*10+1);
			ResultSet rs = pstmt.executeQuery();
		

			while(rs.next()) {
				
				int nidx = rs.getInt("NIDX");
				String ntitle = rs.getString("NTITLE");
				String ncontent = rs.getString("NCONTENT");
				int midx = rs.getInt("MIDX");
				Date nregdate = rs.getDate("NREGDATE");
				int nhit = rs.getInt("NHIT");
				String nimage = rs.getString("NIMAGE");
				String nfiles = rs.getString("NFILES");
				String npub = rs.getString("NPUB");
				int ngood = rs.getInt("NGOOD");
				String ndelyn = rs.getString("NDELYN");
				String ncategory = rs.getString("NCATEGORY");
				
				NoticeVo nv = new NoticeVo(nidx, ntitle, ncontent, midx, nregdate, nhit, nimage, nfiles, npub, ngood, ndelyn, ncategory);
				
				alist.add(nv);
				}
		
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return alist;
		
	}
	
	public int getNoticeListCount(){
		
		String sql = "";
		int count = 0;
		
		try {
			sql = "select count(*) from notice";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}	
	
	public int insertNotice(String ntitle, String ncontent, String ncategory) {
		int value = 0;
		
		String sql = "insert into notice(NIDX, NTITLE, NCONTENT, MIDX, NCATEGORY)" + 
				"values('', ?, ?, 1, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ntitle);
			pstmt.setString(2, ncontent);
			pstmt.setString(3, ncategory);
			
			value = pstmt.executeUpdate();	
			
		} catch (SQLException e) {			
			
			
			e.printStackTrace();
		}
		
		System.out.println("value:"+value);		
		
		return value;
	}
	
	public NoticeVo noticeSelectOne(int nidx) {
		
		NoticeVo nv = null;
		ResultSet rs = null;
		
		String sql = "select * from notice where nidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nidx);
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				nv = new NoticeVo();
				nv.setNtitle(rs.getString("ntitle"));
				nv.setNcontent(rs.getString("ncontent"));
				nv.setMidx(rs.getInt("midx"));
				nv.setNregdate(rs.getDate("nregdate"));
			}					
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}	
		
		return nv;
		
	}
	
}
