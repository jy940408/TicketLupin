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
import com.TicketLupin.web.service.ReservationIdxVo;
import com.TicketLupin.web.service.ReservationShowVo;

public class deleteReservationMail {

	public static void naverMailSend(ArrayList<ReservationIdxVo> list) { 
		
		String host = "smtp.naver.com"; 
		String user = "junyoung940408@naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
		String password = "GXL5WKYJWDQT"; // 패스워드     
		
		String title = "티켓루팡에서 예매 취소를 알려드립니다";
		
		String content = null;
		
		content = "<h2>예매 취소를 알려드립니다</h2>";
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
		content += "		<td colspan=\"10\">" + "test" + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">공연 날짜</th>";
		content += "		<td colspan=\"4\">" + "test" + "</td>";
		content += "		<th colspan=\"2\">공연 회차</th>";
		content += "		<td colspan=\"4\">" + list.get(0).getSrround() + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"1\">이름</th>";
		content += "		<td colspan=\"2\">" + "test" + "</td>";
		content += "		<th colspan=\"2\">연락처</th>";
		content += "		<td colspan=\"3\">" + "test" + "</td>";
		content += "		<th colspan=\"2\">이메일</th>";
		content += "		<td colspan=\"2\">" + "test" + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">수령 방법</th>";
		content += "		<td colspan=\"4\">" + "test" + "</td>";
		content += "		<th colspan=\"2\">결제 방법</th>";
		content += "		<td colspan=\"4\">" + "test" + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">총 결제 금액</th>";
		content += "		<td colspan=\"2\">" + list.get(0).getRipayment() + "</td>";
		content += "		<th colspan=\"2\">기본 금액</th>";
		content += "		<td colspan=\"2\">" + list.get(0).getRibasic() + "</td>";
		content += "		<th colspan=\"2\">할인 금액</th>";
		content += "		<td colspan=\"2\">" + list.get(0).getRidiscount() + "</td>";
		content += "	</tr>";
		content += "	<tr>";
		content += "		<th colspan=\"2\">예매 수수료</th>";
		content += "		<td colspan=\"4\">" + list.get(0).getRivat() + "</td>";
		content += "		<th colspan=\"2\">배송료</th>";
		content += "		<td colspan=\"4\">" + list.get(0).getRidelivery() + "</td>";
		content += "	</tr>";
		content += "</table>";
		content += "<br>";
		content += "<h2>회사 내부 사정에 따라 해당 공연이 취소되고 예매 내역이 환불되었습니다.</h2>";
		content += "<h2>양해부탁드립니다.</h2>";
		
		
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
