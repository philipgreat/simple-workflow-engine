/*
 * Created on 2005-3-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terapico.util.Logger;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Mailer implements WorkflowPort {
	static Logger log = new Logger(Mailer.class);
	public static void main(String[] args) {
		String host = "127.0.0.1";
		String username = "killer";
		String password = "killer";
		System.out.println("start");
		
		// Get session
		Session session = Session.getInstance(System.getProperties(), null);

		// Get the store
		Store store = null;
		// Get folder
		Folder folder = null;
		try {
			store = session.getStore("pop3");
			store.connect(host, username, password);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
		} catch (MessagingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

		BufferedReader reader =
			new BufferedReader(new InputStreamReader(System.in));

		// Get directory
		Message message[] = null;
		try {
			message = folder.getMessages();
		} catch (MessagingException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		for (int i = 0, n = message.length; i < n; i++) {
			try {
				System.out.println(	i
						+ ": "
						+ message[i].getFrom()[0]
						+ message[i].getSubject()
                        + message[i].getContent().toString()						                       
						+ "\t"
						);
			} catch (MessagingException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

		// Close connection
		try {
			folder.close(true);
			store.close();
		} catch (MessagingException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
			
		System.out.println("end");
		
	}

	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.WorkflowPort#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String service(
		HttpServletRequest request,
		HttpServletResponse response,
		WorkflowSession workflowsession)
		throws ServletException, IOException {

		String user = workflowsession.getVariant("mail_username");
		String to = workflowsession.getVariant("mail_sender_to");
		String content = workflowsession.getVariant("mail_sender_body");
		String subject = workflowsession.getVariant("mail_sender_subject");

		log.log(user);
		log.log(to);
		log.log(subject);
		log.log(content);

		try {
			this.sendTextMail(to, subject, content, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "urlerror";
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			log.log(e);
			return "desterror";
		} catch(javax.mail.AuthenticationFailedException e){			
			return "autherr";
		}catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.log(e);
			return "msgerr";
		}catch(InterruptedException e){
			log.log(e);
			return "sleeperror";				
		}
		return "ok";
	}
	public String getStringFromURL(String strURL)
		throws IOException, Exception
	{
		String ret="";
		URL url = new URL(strURL);
		URLConnection urlconn = url.openConnection();
		urlconn.connect();
		InputStream is = urlconn.getInputStream();

		String content = "";
		byte b[] = new byte[32];
		int readb = 0;
		StringBuffer sb=new StringBuffer(8*1024);
		while ((readb = is.read(b)) >= 0) {
			//content += new String(b, 0, readb);
			sb.append(new String(b, 0, readb));
			//b=new byte[32];
		};	
		return sb.toString();
		
	}

	public void sendHTMLMail(
		String toAddr,
		String subject,
		String content,
		String fromAddr,
		String contentType)
		throws IOException, Exception {
		Properties props = new Properties();
		log.log("to="+toAddr+";from="+fromAddr+";content="+content);
		log.log("contentType="+contentType);
		props.put("mail.smtp.host", "127.0.0.1");
		props.put("mail.mime.charset", "GBK");
		System.setProperty("mail.mime.charset", "utf-8" );
		Session session = Session.getDefaultInstance(props, null);
		//HttpURLConnection httpconn=urlconn.connect();

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(fromAddr));

		BodyPart mdp = new MimeBodyPart();

		mdp.setContent(content, contentType);
		//给BodyPart对象设置内容和格式/编码方式 
		Multipart mm = new MimeMultipart();
		//新建一个MimeMultipart对象用来存放BodyPart对 
		//象(事实上可以存放多个) 
		mm.addBodyPart(mdp);
		//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart) 

		msg.setContent(mm);

		InternetAddress[] tos = InternetAddress.parse(toAddr);
		msg.setRecipients(Message.RecipientType.TO, tos);
		msg.setSubject(subject);

		Transport.send(msg);
		System.out.println("Message is Sent");

	}
	public void sendTextMail(
		String toAddr,
		String subject,
		String content,
		String fromAddr)
		throws IOException, AddressException, MessagingException,InterruptedException  {

		Properties props = new Properties();
		props.put("mail.smtp.host", "10.130.1.1");
		//props.put("mail.smtp.host", "218.249.24.5");
		//props.put("mail.smtp.auth", "true");
		//218.249.24.5
		//mail.mime.charset = big5 
		props.put("mail.mime.charset", "big5");
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
		Transport.send(msg);
		Thread.sleep(1000);
				

	}
	public void receiveMail() {
		Authenticator authenticator = null;
		Properties props = new Properties();
		props.put("mail.smtp.host", "10.130.1.1");
		//mail.mime.charset = big5 
		props.put("mail.mime.charset", "GBK");
		Session session = Session.getDefaultInstance(props, authenticator);
		try {
			Store store1 = session.getStore("pop3");
			Store store2 = session.getStore("IMAP"); //实例化IMAPStore
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //实例化Pop3Store

	}

}

