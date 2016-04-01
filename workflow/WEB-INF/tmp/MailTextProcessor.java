package com.terapico.util.mail;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailTextProcessor implements HTMLContentHandler {

	private String mailText;
	private Message mailMessage;
	private MimeMultipart msgParts; 
	private String referURL;
	private Session session; 
	private String from;
	private String to;
	private String subject;
	//private String from;
	
	static long attachindex = 0;
	public MailTextProcessor(){
		this.from="suddy@localhost";
		
	}
	public MailTextProcessor(
			String to,
			String subject,
			String content,
			String referPage,
			String from)
	{
		this.to=to;
		this.from=from;
		this.subject=subject;
		this.mailText=content;
		this.referURL=referPage;		
		
		
	}
	public void startDocument(String referURL, String rawText) {
		// TODO Auto-generated method stub
		this.referURL = referURL;
		this.mailText = rawText;
		Properties props = new Properties();
		props.put("mail.smtp.host", "127.0.0.1");
		//props.put("mail.smtp.host", "10.130.1.1");
		props.put("mail.mime.charset", "GBK");
		System.setProperty("mail.mime.charset", "utf-8");
		session = Session.getDefaultInstance(props, null);
		mailMessage=new MimeMessage(session);
		InternetAddress[] tos;
		try {
			//tos = InternetAddress.parse("zhangxl@channel.com");
			tos = InternetAddress.parse(to);
			mailMessage.setRecipients(Message.RecipientType.TO, tos);
			mailMessage.setFrom(new InternetAddress(from));
			mailMessage.setSubject(subject);
			
			msgParts = new MimeMultipart();		
			msgParts.setSubType("related");
			
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				
	}


	public void endDocument() {
		// TODO Auto-generated method stub
		// send the mail
		try {
			
			MimeBodyPart mbp1= new MimeBodyPart();
			
			mbp1.setContent(mailText,"text/html; charset=utf-8");
			msgParts.addBodyPart(mbp1);
			
			
			mailMessage.setContent(msgParts);
			mailMessage.setSentDate(new Date());
		    Transport.send(mailMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println(mailText);
		// mailText
	}

	public void OnTag(String rawText, HTMLTagContent content) {
		// TODO Auto-generated method stub
		// mailText
		String rawContent = content.getRawText();

		CharSequence cs1 = rawContent.subSequence(0, rawText.length());
		// if()
		if (content.isImage()) {
			String src = content.getProperty("src");
			content.setProperty("src", "cid:__" + attachindex + "@suddy.com");
			
			try {
				MimeBodyPart mbp2= new MimeBodyPart();				
				String urlstr = this.getAbsoluteURL(referURL, src);
				URL url=new URL(urlstr);
				//URLDataSource urs=new URLDataSource(url);
				mbp2.setFileName(url.getFile());
			    mbp2.setText("This is a beautiful car !");
			    mbp2.setDataHandler(new DataHandler(url));			
			    mbp2.setHeader("Content-ID","<__" + attachindex + "@suddy.com>");
			    msgParts.addBodyPart(mbp2);
				System.out.println(urlstr);

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			String tagHTML = content.toHTML();
			CharSequence cs2 = tagHTML.subSequence(0, tagHTML.length());
			mailText = mailText.replace(cs1, cs2);

		}
		// TODO: add extra process here to add image to the mail

	}

	public String getAbsoluteURL(String referPage, String path)
			throws URISyntaxException {
		URI url = new URI(referPage);
		return url.resolve(new URI(path)).toString();
		// return referPage;
	}

	/**
	 * @param args
	 */
	public void sendHTMLMail(String toAddr, String subject, String content,
			String fromAddr, String contentType) throws IOException, Exception {

		Properties props = new Properties();
		props.put("mail.smtp.host", "127.0.0.1");
		props.put("mail.smtp.host", "10.130.1.1");
		
		//
		// props.put("mail.smtp.host", "1gmail-smtp-in.l.google.com");

		// mail.mime.charset = big5
		props.put("mail.mime.charset", "GBK");
		System.setProperty("mail.mime.charset", "utf-8");
		Session session = Session.getDefaultInstance(props, null);
		// HttpURLConnection httpconn=urlconn.connect();

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(fromAddr));

		BodyPart mdp = new MimeBodyPart();

		mdp.setContent(content, contentType);
		// 给BodyPart对象设置内容和格式/编码方式
		Multipart mm = new MimeMultipart();
		// 新建一个MimeMultipart对象用来存放BodyPart对
		// 象(事实上可以存放多个)
		mm.addBodyPart(mdp);
		// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)

		msg.setContent(mm);

		InternetAddress[] tos = InternetAddress.parse(toAddr);
		msg.setRecipients(Message.RecipientType.TO, tos);
		msg.setSubject(subject);

		Transport.send(msg);
		System.System.out.println("Message is Sent");

	}
	public static boolean sendSupperMail(
			String to,
			String subject,
			String content,
			String referURL,
			String from)
	{
		
		HTMLContentParser parse = new HTMLContentParser();
		HTMLContentHandler handler=new MailTextProcessor(to,        
				subject,   
				content,   
				referURL,  
				from       
		);
		parse.setHandler(handler);		
		//String sample = parse.getTextFromFile("f:/suddy/sampleHTML.txt");		
		parse.Parse(referURL, content);
		
		return true;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HTMLContentParser parse = new HTMLContentParser();
		String sample = parse.getTextFromFile("f:/suddy/sampleHTML.txt");
		if(false){
			
			parse.setHandler(new MailTextProcessor());		
					
			parse.Parse("http://localhost/java/", sample);
			
		}
		MailTextProcessor.sendSupperMail("suddy@localhost",
				"test image",
				sample,
				"http://suddy.channel.com/java/","suddy@localhost");
		
		
	}

}
