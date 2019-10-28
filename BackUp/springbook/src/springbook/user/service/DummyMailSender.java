package springbook.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSender implements MailSender{
	public void send(SimpleMailMessage mailMessage) throws MailException{
		System.out.println("메일 전송 테스트 OK");
	}
	
	public void send(SimpleMailMessage[] mailMessage) throws MailException{
		System.out.println("메일 전송 테스트 OK");
	}

}
