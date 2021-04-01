package com.TicketLupin.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TicketLupin.web.DBconn.DBconn;
import com.TicketLupin.web.service.MyticketVo;

public class MyticketDao {
	private	Connection conn;
	private PreparedStatement pstmt;
	
	public MyticketDao() {
		DBconn dbconn = new DBconn();
		this.conn = dbconn.getConnection();
	}
	


	public List<MyticketVo> getMycommentList(int midx){
		
		List<MyticketVo> clist = new ArrayList<MyticketVo>();
			String sql ="select * from(select rownum num, n.* from(select row_number() over (order by a.eregdate) no, a.etitle, a.eregdate from event a, c_comment b where a.eidx=b.eidx(+)and b.midx= 4 and b.c_delyn='N' )n)where num between 1 and 7";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			ResultSet rs = pstmt.executeQuery();
			 
			
			while(rs.next()) {
				
				MyticketVo mv = new MyticketVo();
				mv.setNo(rs.getInt("no"));
				mv.setEtitle(rs.getString("etitle"));
				mv.setEregdate(rs.getDate("eregdate"));
				clist.add(mv);
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	 
		return clist;		
	}
}
