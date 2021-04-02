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
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   public MyticketDao() {
      DBconn dbconn = new DBconn();
      this.conn = dbconn.getConnection();
   }
   
   public List<MyticketVo> getMyeventList(int midx){
      
      List<MyticketVo> elist = new ArrayList<MyticketVo>();
      String sql ="select * from(select rownum num, n.* from(select row_number() over (order by a.eregdate) no, a.etitle, a.eregdate from event a, c_comment b where a.eidx=b.eidx(+)and b.midx= ? )n)where num between 1 and 7";

      try {
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, midx);
         ResultSet rs = pstmt.executeQuery();    
         
         while(rs.next()) {
            
            MyticketVo mv = new MyticketVo();
            mv.setNo(rs.getInt("NO"));
            mv.setEtitle(rs.getString("ETITLE"));
            mv.setEregdate(rs.getDate("EREGDATE"));
            elist.add(mv);
         }
         
      }catch(SQLException e){
         
         e.printStackTrace();
         
      }finally {
         
         if (rs != null) try { rs.close(); } catch(Exception e) {}
         if (pstmt != null) try { rs.close(); } catch(Exception e) {}
         if (conn != null) try { rs.close(); } catch(Exception e) {}
      }
    
      return elist;      
   }
   
   public MemberVo getName(int midx) {
      
      MemberVo mv = null;
      ResultSet rs = null;
      
      String sql = "SELECT MNAME FROM MEMBER WHERE MIDX = ?";
      
      try {
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, midx);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            
            mv = new MemberVo();
            
            mv.setMname(rs.getString("MNAME"));
         }
         
      }catch(Exception e){
            
         e.printStackTrace();
         
      }finally {
         
         if (rs != null) try { rs.close(); } catch(Exception e) {}
         if (pstmt != null) try { rs.close(); } catch(Exception e) {}
         if (conn != null) try { rs.close(); } catch(Exception e) {}
      }
      
      return mv;
   }
   
   public List<QuestionVo> getQuestionList(int midx){
      
      List<QuestionVo> list = new ArrayList<QuestionVo>();
      
      String sql = "SELECT NUM, QIDX, QTITLE, QSTATE, QREGDATE FROM (SELECT ROWNUM NUM, N.* FROM (SELECT QIDX, QTITLE, QSTATE, QREGDATE FROM QUESTION WHERE MIDX = ? ORDER BY QREGDATE DESC) N) WHERE NUM <= 7";
      
      try{
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, midx);
         
         ResultSet rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            QuestionVo qv = new QuestionVo();
            
            qv.setNum(rs.getInt("NUM"));
            qv.setQidx(rs.getInt("QIDX"));
            qv.setQtitle(rs.getString("QTITLE"));
            qv.setQstate(rs.getString("QSTATE"));
            qv.setQregdate(rs.getDate("QREGDATE"));
            
            list.add(qv);      
         }
         
      }catch(Exception e) {
         
         e.printStackTrace();
         
      }finally {
         
         if (rs != null) try { rs.close(); } catch(Exception e) {}
         if (pstmt != null) try { rs.close(); } catch(Exception e) {}
         if (conn != null) try { rs.close(); } catch(Exception e) {}
      }
      
      return list;
   }
   
   
   
}