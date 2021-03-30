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
		String user = "junyoung940408@naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
		String password = "s!160331"; // 패스워드     
		
		String title = "티켓루팡에서 알려드립니다";
		
		String content = null;
		content = "<h2>예매 완료를 알려드립니다</h2>";
		content += "제목 ";
		content += "<table>";
		content += "	<tr>";
		content += "		<td>";
		content += "			내용이 제대로 나오려나";
		content += "		</td>";
		content += "		<td>";
		content += "			두번째 제대로 나오려나";
		content += "		</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<td>";
		content += "			내용이 또 제대로 나오려나";
		content += "		</td>";
		content += "		<td>";
		content += "			흠...이 제대로 나오려나";
		content += "		</td>";
		content += "	</tr>";
		content += "</table>";
		content += "<img src=''>";
		
		
		// SMTP 서버 정보를 설정한다. 
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
			// 메일 제목
			message.setSubject(title); 
			// 메일 내용 
			message.setContent(content, "text/html;charset=UTF-8"); 
			// send the message 
			Transport.send(message); 
			System.out.println("Success Message Send"); 
		} catch (MessagingException e) { 
			e.printStackTrace(); 
		} 
	}
	
}
