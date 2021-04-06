package util;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.ReservationDao;
import com.TicketLupin.web.service.ReservationShowVo;

public class reservationMail {

	public static void naverMailSend(String showTitle, ArrayList seat, int sidx, String comDate, String round, String name, String tel, String email, String pick, String payMethod, int paymentSum, int basicSum, int discountSum, int VAT, int deliverySum) { 
		
		String host = "smtp.naver.com"; 
		String user = "junyoung940408@naver.com"; // ���̹��� ��� ���̹� ����, gmail��� gmail ���� 
		String password = "GXL5WKYJWDQT"; // �н�����     
		
		String title = "Ƽ�Ϸ��ο��� ���� ������ �˷��帳�ϴ�";
		
		String content = null;
		
		content = "<h2>���� �ϷḦ �˷��帳�ϴ�</h2>";
		content += "<table border=\"1\">";
		content += "	<colgroup>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "		<col width='50px'/>";
		content += "	</colgroup>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">���� ��</th>";
		content += "		<td colspan=\"10\">" + showTitle + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">���� ��¥</th>";
		content += "		<td colspan=\"4\">" + comDate + "</td>";
		content += "		<th colspan=\"2\">���� ȸ��</th>";
		content += "		<td colspan=\"4\">" + round + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">�¼�</th>";
		content += "		<td colspan=\"10\">" + seat + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"1\">�̸�</th>";
		content += "		<td colspan=\"2\">" + name + "</td>";
		content += "		<th colspan=\"2\">����ó</th>";
		content += "		<td colspan=\"3\">" + tel + "</td>";
		content += "		<th colspan=\"2\">�̸���</th>";
		content += "		<td colspan=\"2\">" + email + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">���� ���</th>";
		content += "		<td colspan=\"4\">" + pick + "</td>";
		content += "		<th colspan=\"2\">���� ���</th>";
		content += "		<td colspan=\"4\">" + payMethod + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">�� ���� �ݾ�</th>";
		content += "		<td colspan=\"2\">" + paymentSum + "</td>";
		content += "		<th colspan=\"2\">�⺻ �ݾ�</th>";
		content += "		<td colspan=\"2\">" + basicSum + "</td>";
		content += "		<th colspan=\"2\">���� �ݾ�</th>";
		content += "		<td colspan=\"2\">" + discountSum + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">���� ������</th>";
		content += "		<td colspan=\"4\">" + VAT + "</td>";
		content += "		<th colspan=\"2\">��۷�</th>";
		content += "		<td colspan=\"4\">" + deliverySum + "</td>";
		content += "	</tr>";
		content += "</table>";
		
		
		// SMTP ���� ������ �����Ѵ�. 
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", 587); 
		props.put("mail.smtp.auth", "true"); 
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(user, password); 
			} 
		}); 
		
		try { 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user)); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
			// ���� ����
			message.setSubject(title); 
			// ���� ���� 
			message.setContent(content, "text/html;charset=UTF-8"); 
			// send the message 
			Transport.send(message); 
			System.out.println("Success Message Send"); 
		} catch (MessagingException e) { 
			e.printStackTrace(); 
		} 
	}
	
}
