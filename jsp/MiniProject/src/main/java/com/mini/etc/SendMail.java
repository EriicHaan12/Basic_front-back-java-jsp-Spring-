package com.mini.etc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	public static void send(String userEmailAddr, String confirmCode)
			throws MessagingException, NoSuchProviderException {
		// SMTP(Send Mail Transfer Protocol) : 메일 전송 통신 규약

		// SMTP에 따라 메소드 생성

		// 보낼 메일(메세지)을 만듬
		
		String subject = "mini.com에서 보낸 이메일 인증번호 입니다.";

		String message = "mini.com 회원 가입을 환영합니다. 인증 번호 : " + confirmCode + "를 입력 하시고 확인 버튼을 눌러 인증을 해주세요 ";

		// 메일 환경 세팅
// 네이버
//		Properties prop = new Properties();
//		// map interface 로 부터 상속 받은 클래스 -> hashMap 과 쓰임이 비슷하다
//		prop.put("mail.smtp.starttls.required", "true");
//		
//		// 메일 환경 설정을 시작하겠다 라는 뜻
//		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		// 메일의 보안 프로토콜(암호화) 설정 / ssl 연결 하겠다는 뜻
//		prop.put("mail.smtp.host", "smtp.naver.com");
//		// 메일 서버의 주소 설정, tmp서버명 지정
//		// 네이버 메일 참고
//		prop.put("mail.smtp.port", "465"); // 포트번호
//		prop.put("mail.smtp.auth", "true");// 인증과정을 거치겠다
//		prop.put("mail.smtp.ssl.enable", "true"); // SSL 사용하겠다
		
		// gmail
		Properties prop = new Properties();
		// map interface 로 부터 상속 받은 클래스 -> hashMap 과 쓰임이 비슷하다
		prop.put("mail.smtp.starttls.required", "true");
		
		// 메일 환경 설정을 시작하겠다 라는 뜻
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		// 메일의 보안 프로토콜(암호화) 설정 / ssl 연결 하겠다는 뜻
		prop.put("mail.smtp.host", "smtp.gmail.com");
		// 메일 서버의 주소 설정, tmp서버명 지정
		// 네이버 메일 참고
		prop.put("mail.smtp.port", "465"); // 포트번호
		prop.put("mail.smtp.auth", "true");// 인증과정을 거치겠다
		prop.put("mail.smtp.ssl.enable", "true"); // SSL 사용하겠다
		
		
		
		
		

		// 인증 과정 (access Token의 과정)
		Session mailSession = Session.getInstance(prop, new Authenticator() {

			// Authenticator가 Anonymous 속성의 추상 메서드 속성을 가지고 있으므로,
			// 이 안에 override 할 객체를 생성 해준다.

			// 2)메일 서버에 javax.mail.PasswordAuthentication 객체로 로그인 하여
			// javax.mail.PasswordAuthentication 타입의 인증 정보 토큰을 반환

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// 1) 내 메일 서버의 아이디와 패스워드 정보를 객체로 만들어서 반환
				return new javax.mail.PasswordAuthentication(EmailAccount.emailAddr, EmailAccount.emailPwd);
			}
			//
		});
		System.out.println("인증할 메일 세션 : " + mailSession.toString());

		if (mailSession != null) {
			MimeMessage mime = new MimeMessage(mailSession);

			//naver
			//mime.setFrom(new InternetAddress("erichan112@naver.com"));
			//gmail
			
			////	mime.setFrom(new InternetAddress(FROM, FROMNAME)); test 꼭 다시 해보기
			
			
			mime.setFrom(new InternetAddress("han33836914@gmail.com"));
			// 보낸 사람 주소 internetAddress 객체를 생성해서 주소 형태로 만들어준뒤

			mime.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmailAddr));
			// 메일을 받을 사람 to: 직접 보낼 사람에게 // cc: 참조 걸사람 ,// bcc: 참조는 걸되 모르게 걸 사람

			mime.setSubject(subject); // 보낼 메일 제목

			// 내용 중 파일을 첨부해야 될 시
//			mime.setContent(new Multipart() {
//				
//				@Override
//				public void writeTo(OutputStream arg0) throws IOException, MessagingException {
//					// TODO Auto-generated method stub
//					
//				}
//			});
			String html = "<h5>메일 인증 코드드드웅 : "
					+ "<a href='http://localhost:8084/MiniProject/member/register.jsp?cc="+confirmCode+">" + confirmCode + "</a></h5>";
	
//			mime.setContent(html,"text/html");
			mime.setText(html, "utf-8", "html");

			
		//	mime.setText(message); // 본문 내용

			// 실제로 메일 전송 (예외가 나지 않았을 때)

			Transport tran = mailSession.getTransport("smtp");
			tran.connect(EmailAccount.emailAddr, EmailAccount.emailPwd );
			tran.sendMessage(mime, mime.getAllRecipients()); //mime을 보내되 mime의 보낼 사람한테해당 하는 모든 사람에게 보내기
			tran.close();
		}

	}
}
