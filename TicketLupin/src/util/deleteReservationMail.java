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
		String user = "junyoung940408@naver.com"; // ���̹��� ��� ���̹� ����, gmail��� gmail ���� 
		String password = "GXL5WKYJWDQT"; // �н�����     
		
		String title = "Ƽ�Ϸ��ο��� ���� ��Ҹ� �˷��帳�ϴ�";
		
		ArrayList setList = new ArrayList();
		String content = null;
		for(int i = 0 ; i < list.size() ; i++) {
			content = "<h2>���� ��Ҹ� �˷��帳�ϴ�</h2>";
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
			content += "		<td colspan=\"10\">" + list.get(i).getStitle() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"2\">���� ��¥</th>";
			content += "		<td colspan=\"4\">" + "test" + "</td>";
			content += "		<th colspan=\"2\">���� ȸ��</th>";
			content += "		<td colspan=\"4\">" + list.get(i).getSrround() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"1\">�̸�</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRname() + "</td>";
			content += "		<th colspan=\"2\">����ó</th>";
			content += "		<td colspan=\"3\">" + list.get(i).getRtel() + "</td>";
			content += "		<th colspan=\"2\">�̸���</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRemail() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"3\">���� ���</th>";
			content += "		<td colspan=\"9\">" + list.get(i).getRpaymethod() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"2\">�� ���� �ݾ�</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRipayment() + "</td>";
			content += "		<th colspan=\"2\">�⺻ �ݾ�</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRibasic() + "</td>";
			content += "		<th colspan=\"2\">���� �ݾ�</th>";
			content += "		<td colspan=\"2\">" + list.get(i).getRidiscount() + "</td>";
			content += "	</tr>";
			content += "	<tr>";
			content += "		<th colspan=\"2\">���� ������</th>";
			content += "		<td colspan=\"4\">" + list.get(i).getRivat() + "</td>";
			content += "		<th colspan=\"2\">��۷�</th>";
			content += "		<td colspan=\"4\">" + list.get(i).getRidelivery() + "</td>";
			content += "	</tr>";
			content += "</table>";
			content += "<br>";
			content += "<h2>ȸ�� ���� ������ ���� �ش� ������ ��ҵǰ� ���� ������ ȯ�ҵǾ����ϴ�.</h2>";
			content += "<h2>���غ�Ź�帳�ϴ�.</h2>";
			content += "<h2>�ڼ��� ������ Ƽ�Ϸ��� ���������� �������ֽñ� �ٶ��ϴ�.</h2>";
			setList.add(content);
		}
		System.out.println("���� ����Ʈ Ȯ��: " + setList);
		ArrayList emailList = new ArrayList();
		String email = null;
		for(int i = 0 ; i < list.size() ; i++) {
			email = list.get(i).getRemail();
			emailList.add(email);
		}
		System.out.println("���� ����Ʈ Ȯ��: " + emailList);
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
			for(int i = 0 ; i < list.size() ; i++) {
				MimeMessage message = new MimeMessage(session); 
				message.setFrom(new InternetAddress(user)); 
				message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)emailList.get(i))); 
				// ���� ����
				message.setSubject(title); 
				// ���� ���� 
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
