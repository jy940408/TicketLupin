package com.TicketLupin.web.DBconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconn {

	
/*	
	Connection conn =null;
	
   String coninfo ="jdbc:oracle:thin:@127.0.0.1:1521:xe";
   String idinfo ="TicketLupin";
   String pwdinfo ="1234";
   
   public Connection getConnection() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("after forName");
         conn= DriverManager.getConnection(coninfo, idinfo, pwdinfo);
         System.out.println("DBms connection success");
		 System.out.println("DB load success");
         } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB load fail " + e.toString());
         }
         return conn;
   }
  */
	
	Connection conn =null;
	
	   String coninfo ="jdbc:oracle:thin:@10.211.55.4:1521:xe";
	   String idinfo ="TicketLupin";
	   String pwdinfo ="1234";
	   
	   public Connection getConnection() {
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("after forName");
	         conn= DriverManager.getConnection(coninfo, idinfo, pwdinfo);
	         System.out.println("DBms connection success");
			 System.out.println("DB load success");
	         } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("DB load fail " + e.toString());
	         }
	         return conn;
	   }
	/*
	
	Connection conn = null;
	String coninfo = "jdbc:mysql://localhost:3306/TicketLupin";
	String idinfo ="TicketLupin";
	String pwdinfo ="1234";
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("after forName");
			conn = DriverManager.getConnection(coninfo, "root", "s!1018810");
			System.out.println("DBms connection success");
			System.out.println("DB load success");
		} catch (Exception e) {
			System.out.println("DB load fail " + e.toString());
		}
		return conn;
	}
	
	*/
}
