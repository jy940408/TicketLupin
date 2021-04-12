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
		String user = "junyoung940408@naver.com"; // 네이버 계정 아이디 
		String password = "GXL5WKYJWDQT"; // 네이버 계정 비번
		
		String title = "티켓루팡에서 예매취소를 알려드립니다.";
		
		ArrayList setList = new ArrayList();
		String content = null;
		for(int i = 0 ; i < list.size() ; i++) {
			content = "<h2>예매 취소를 알려드립니다�</h2>";
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
			content += "		<td colspan=\"10\">" + list.get(i).getStitle() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"2\">공연 날짜</th>";
			content += "		<td colspan=\"4\">" + "test" + "</td>";
			content += "		<th colspan=\"2\">공연 회차</th>";
			content += "		<td colspan=\"4\">" + list.get(i).getSrround() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"1\">이름</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRname() + "</td>";
			content += "		<th colspan=\"2\">연락처</th>";
			content += "		<td colspan=\"3\">" + list.get(i).getRtel() + "</td>";
			content += "		<th colspan=\"2\">이메일</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRemail() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"3\">결제 방법</th>";
			content += "		<td colspan=\"9\">" + list.get(i).getRpaymethod() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"2\">총 결제 금액</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRipayment() + "</td>";
			content += "		<th colspan=\"2\">기본 금액</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRibasic() + "</td>";
			content += "		<th colspan=\"2\">할인 금액</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRidiscount() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"2\">예매 수수료</th>";
			content += "		<td colspan=\"4\">" + list.get(i).getRivat() + "</td>";
			content += "		<th colspan=\"2\">배송료</th>";
			content += "		<td colspan=\"4\">" + list.get(i).getRidelivery() + "</td>";
			content += "	</tr>";
			content += "</table>";
			content += "<br>";
			content += "<h2>회사 내부 사정에 따라 해당 공연이 취소되고 예매 내역이 환불되었습니다.</h2>";
			content += "<h2>양해 부탁드립니다.</h2>";
			content += "<h2>자세한 사항은 티켓루팡 공지사항을 참조해주시기 바랍니다.</h2>";
			setList.add(content);
		}
		System.out.println("내용 리스트 확인: " + setList);
		ArrayList emailList = new ArrayList();
		String email = null;
		for(int i = 0 ; i < list.size() ; i++) {
			email = list.get(i).getRemail();
			emailList.add(email);
		}
		System.out.println("메일 리스트 확인: " + emailList);
		// SMTP 서버 정보 설정 
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
			for(int i = 0 ; i < list.size() ; i++) {
				MimeMessage message = new MimeMessage(session); 
				message.setFrom(new InternetAddress(user)); 
				message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)emailList.get(i))); 
				// 메일 제목
				message.setSubject(title); 
				// 메일 내용
				message.setContent((String)setList.get(i), "text/html;charset=UTF-8"); 
				// send the message 
				Transport.send(message); 
				System.out.println("Success Message Send"); 
			}
		} catch (MessagingException e) { 
			e.printStackTrace(); 
		} 
	}
	
}
