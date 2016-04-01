package com.msn.cbbp.workflow;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AuthMailer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//AuthMailer.sendTextMail("suddy_chang@263.net", "test", "what", "suddy_chang@263.net");
			AuthMailer.sendTextMail("chang.luminarc@gmail.com", "test", 
					"what", "zhangxl@channelsoft.com");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void sendTextMail(
			String toAddr,
			String subject,
			String content,
			String fromAddr)
			throws IOException, AddressException, MessagingException,InterruptedException  {

			Properties props = new Properties();
			//props.put("mail.smtp.host", "mail.263.net");
			//props.put("mail.smtp.host", "218.249.24.5");
			props.put("mail.smtp.auth", "true");
			//218.249.24.5
			//mail.mime.charset = big5 
			props.put("mail.mime.charset", "GBK");
			
			props.put("mail.smtp.auth", "true");
			System.setProperty("mail.mime.charset", "utf-8" );
			Session session = Session.getDefaultInstance(props, null);

			//HttpURLConnection httpconn=urlconn.connect();

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromAddr));
			msg.setText(content);
			msg.setHeader("X-Mailer", "TeraPico Wap Mailer");
			InternetAddress[] tos = InternetAddress.parse(toAddr);
			msg.setRecipients(Message.RecipientType.TO, tos);
			//msg.setRecipients(Message.RecipientType.CC, tos);
			//msg.setHeader("Subject", "\u4e00\u4e00\u4e00\u4e00\u4e00");
			msg.setSubject(subject);
			//msg.setSubject("\u4e00\u4e00\u4e00\u4e00\u4e00");	
			Transport tr = session.getTransport("smtp");
			tr.connect("mail.channelsoft.com", fromAddr, "kkredskirt");
			msg.saveChanges();      // don't forget this
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
					

		}
}
