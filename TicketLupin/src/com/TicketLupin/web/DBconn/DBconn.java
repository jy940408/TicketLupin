package com.TicketLupin.web.DBconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconn {

	Connection conn =null;
	   //���� ����
	   String coninfo ="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	   String idinfo ="TicketLupin";
	   String pwdinfo ="1234";
	   
	   public Connection getConnection() {
	      try {
	         //����̹����� OrcleDriver�� ã�´�
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         //���������� ������ �����Ѵ�
	         conn= DriverManager.getConnection(coninfo, idinfo, pwdinfo);
	               
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         return conn;
	   }
	
}
