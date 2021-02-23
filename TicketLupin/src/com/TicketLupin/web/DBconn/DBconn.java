package com.TicketLupin.web.DBconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconn {

	Connection conn =null;
	   //접속 정보
	   String coninfo ="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	   String idinfo ="TicketLupin";
	   String pwdinfo ="1234";
	   
	   public Connection getConnection() {
	      try {
	         //드라이버에서 OrcleDriver를 찾는다
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         //접속정보를 가지고 연결한다
	         conn= DriverManager.getConnection(coninfo, idinfo, pwdinfo);
	               
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         return conn;
	   }
	
}
