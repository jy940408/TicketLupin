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

	public static void naverMailSend() { 
		
		String host = "smtp.naver.com"; 
		String user = "junyoung940408@naver.com"; // ���̹��� ��� ���̹� ����, gmail��� gmail ���� 
		String password = "s!160331"; // �н�����     
		
		String title = "Ƽ�Ϸ��ο��� �˷��帳�ϴ�";
		
		String content = null;
		content = "<h2>���� �ϷḦ �˷��帳�ϴ�</h2>";
		content += "���� ";
		content += "<table>";
		content += "	<tr>";
		content += "		<td>";
		content += "			������ ����� ��������";
		content += "		</td>";
		content += "		<td>";
		content += "			�ι�° ����� ��������";
		content += "		</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<td>";
		content += "			������ �� ����� ��������";
		content += "		</td>";
		content += "		<td>";
		content += "			��...�� ����� ��������";
		content += "		</td>";
		content += "	</tr>";
		content += "</table>";
		content += "<img src=''>";
		
		
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("junyoung940408@gmail.com")); 
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
