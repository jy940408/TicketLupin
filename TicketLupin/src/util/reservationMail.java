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
		String user = "junyoung940408@naver.com"; // 네이버 계정 아이디 
		String password = "GXL5WKYJWDQT"; // 네이버 계정 비번
		
		String title = "티켓루팡에서 예매 정보를 알려드립니다";
		
		String content = null;
		
		content = "<h2>예매 완료를 알려드립니다</h2>";
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
		content += "		<th colspan=\"2\">공연 명</th>";
		content += "		<td colspan=\"10\">" + showTitle + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">공연 날짜</th>";
		content += "		<td colspan=\"4\">" + comDate + "</td>";
		content += "		<th colspan=\"2\">공연 회차</th>";
		content += "		<td colspan=\"4\">" + round + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">좌석</th>";
		content += "		<td colspan=\"10\">" + seat + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"1\">이름</th>";
		content += "		<td colspan=\"2\">" + name + "</td>";
		content += "		<th colspan=\"2\">연락처</th>";
		content += "		<td colspan=\"3\">" + tel + "</td>";
		content += "		<th colspan=\"2\">이메일</th>";
		content += "		<td colspan=\"2\">" + email + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">수령 방법</th>";
		content += "		<td colspan=\"4\">" + pick + "</td>";
		content += "		<th colspan=\"2\">결제 방법</th>";
		content += "		<td colspan=\"4\">" + payMethod + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">총 결제 금액</th>";
		content += "		<td colspan=\"2\">" + paymentSum + "</td>";
		content += "		<th colspan=\"2\">기본 금액</th>";
		content += "		<td colspan=\"2\">" + basicSum + "</td>";
		content += "		<th colspan=\"2\">할인 금액</th>";
		content += "		<td colspan=\"2\">" + discountSum + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">예매 수수료</th>";
		content += "		<td colspan=\"4\">" + VAT + "</td>";
		content += "		<th colspan=\"2\">배송료</th>";
		content += "		<td colspan=\"4\">" + deliverySum + "</td>";
		content += "	</tr>";
		content += "</table>";
		
		
		// SMTP 정보 설정
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
