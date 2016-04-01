package com.terapico.util.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.terapico.util.Logger;

public class MailTextProcessor implements HTMLContentHandler {
	static final String fromUser = "zhangxl@ucans.com.cn";
	static final String smtpServer = "mail.ucans.com.cn";
	static final String password = "suddy_chang";	
	static final String tabString = "<table border=0 width=100% height=50 cellspacing=0 cellpadding=1 bgcolor=#cccccc>"
			+ "<tr>"
			+ "<td height=23 width=100% align=center><a href=\"{0}\">{1}</a><br>{2}</td>"
			+ "</tr>" + "</table><br>";

	static final String tabString2 = "<TABLE BORDER=0 WIDTH=100% CELLSPACING=0 CELLPADDING=0>"
			+ "<TR>"
			+ "<TD BGCOLOR=#0000cc>"
			+ "<TABLE BORDER=0 WIDTH=100% CELLSPACING=1 CELLPADDING=2>"
			+ "<TR>"
			+ "<TH WIDTH=100% BGCOLOR=#eeeeee  align=center><a href=\"{0}\"><font size=+1>{1}</font></a><br><font size=-1>[ {2} ]</font></TH>"
			+ "</TR>" + "</TABLE>" + "</TD>" + "</TABLE><br>";
	
	static Logger log = new Logger("MailTextProcessor");
	private String mailText;	
	private String baseURL;
	private Message mailMessage;
	private MimeMultipart msgParts;
	private String referURL;
	private Session session;
	private String from;	
	//private String encoding;
	private String contentType;
	private String to;
	private String subject;
	private List urlList;
	//private String from;
	int attachindex = 0;

	public MailTextProcessor() {
		this.from = "suddy@localhost";

	}

	public MailTextProcessor(String to, String subject, String content,
			String referPage, String from,String baseURL) {
		urlList = new ArrayList();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.mailText = content;
	
		this.referURL = referPage;
		if(baseURL==null){
			
		}else if(baseURL.length()>0){
			this.baseURL = baseURL;
		}else{
			this.baseURL = referPage;
		}		
	}

	public void startDocument(String referURL, String rawText) {
		// TODO Auto-generated method stub
		this.referURL = referURL;
		this.mailText = rawText;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.mime.charset", "GBK");
		System.setProperty("mail.mime.charset", "utf-8");
		session = Session.getDefaultInstance(props, null);
		mailMessage = new MimeMessage(session);
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

	public boolean addURLToDownloadList(String url) {

		boolean isNeedAdd = true;
		for (int i = 0; i < this.urlList.size(); i++) {
			if (url.equals(urlList.get(i))) {
				isNeedAdd = false;
				break;
			}
		}
		if (isNeedAdd) {
			urlList.add(url);
		}
		return isNeedAdd;
	}

	public void endDocument() {
		// TODO Auto-generated method stub
		// send the mail
		try {

			MimeBodyPart mbp1 = new MimeBodyPart();

			mbp1.setContent(mailText, "text/html; charset=UTF-8");
			
			msgParts.addBodyPart(mbp1);
			addAttaches();

			mailMessage.setContent(msgParts);
			mailMessage.setSentDate(new Date());
			
			
			mailMessage.saveChanges();
			//Transport.send(mailMessage);
			
			Transport tr = session.getTransport("smtp");
			
			tr.connect(smtpServer, 
					fromUser,
					password
					);
		
			tr.sendMessage(mailMessage, mailMessage.getAllRecipients());
			tr.close();
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.log(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.log(e);
			e.printStackTrace();
		}

		//System.out.println(mailText);
		// mailText
	}

	public void finalize() {
		this.session = null;
		this.urlList = null;
		this.msgParts = null;
		this.mailText = null;
		this.mailMessage = null;
	}

	public void onTag(String rawText, HTMLTagContent content) {
		// TODO Auto-generated method stub
		//log.log(content);
		if (content.isHyperLinkArea()) {
			//log.log(content);
			String href = content.getProperty("href");
			if (!href.startsWith("#")) {
				//如果参考本页的不需要处理
				try {
					String urlstr = this.getAbsoluteURL(referURL, href);
					content.setProperty("href", urlstr);
					String rawContent = content.getRawText();
					String tagHTML = content.toHTML();

					CharSequence cs1 = rawContent.subSequence(0, rawContent
							.length());
					CharSequence cs2 = tagHTML.subSequence(0, tagHTML.length());
					//log.log(cs1);
					//log.log(cs2);
					mailText = mailText.replace(cs1, cs2);

				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.log(e);
				}

			}//if(!href.startsWith("#"))

		}//if(content.isHyperLinkArea())
		if (content.hasDocument()) {
			String src = content.getProperty("src");
			src = src.replaceAll("&amp;", "&");
			if (this.addURLToDownloadList(src)) {
				String rawContent = content.getRawText();
				CharSequence cs1 = rawContent.subSequence(0, rawContent
						.length());
				CharSequence cs2 = "0".subSequence(0, 1);
				mailText.replace(cs1,cs2);		
				attachindex++;
			}
			
		}	
		if (content.hasImage()) {
			//log.log(content);
			String src = content.getProperty("src");
			//online2 chinaren is not support download from other place

			try {

				content.setProperty("src", "cid:img_" + attachindex
						+ "__suddy__com__");
				src = src.replaceAll("&amp;", "&");
				String urlstr = this.getAbsoluteURL(baseURL, src);
				//log.log("url got: " + src);
				//log.log("url got: "+urlstr);	

				if (this.addURLToDownloadList(urlstr)) {
					String tagHTML = content.toHTML();
					String rawContent = content.getRawText();
					CharSequence cs1 = rawContent.subSequence(0, rawContent
							.length());
					CharSequence cs2 = tagHTML.subSequence(0, tagHTML.length());
					//log.log(cs1);
					//log.log(cs2);

					mailText = mailText.replace(cs1, cs2);
					attachindex++;
				}
				//System.out.println(urlstr);

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.log(e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.log(e);
				e.printStackTrace();
			}

		}//if (content.isImage())
		// TODO: add extra process here to add image to the mail

	}

	public String getAbsoluteURL(String referPage, String path)
			throws URISyntaxException {

		URI url = new URI(referPage);
		return url.resolve(new URI(path)).toString();
		// return referPage;
	}

	public boolean addAttaches() {

		for (int i = 0; i < this.urlList.size(); i++) {

			//URL url=new URL(urlstr);

			//URLDataSource urs=new URLDataSource(url);
			try {

				String url = urlList.get(i).toString();
				log.log("try to download " + url);
				if(url.startsWith("http")){
					MailURLResource mur = MailResourceSucker.getResource(url,
							this.referURL);
					if (null != mur) {
						MimeBodyPart mbp2 = new MimeBodyPart();
						mbp2.setText("This is a beautiful car !");
						mbp2.setHeader("Content-Type", mur.getContentType());
						DataHandler dh = new DataHandler(new ByteArrayDataSource(
								mur.getContent(), mur.getContentType()));
						mbp2.setDataHandler(dh);					
						mbp2.setFileName(mur.getFileName());
						//SET DEFAULT NAME EXT
						mbp2.setHeader("Content-ID", "<img_" + i + "__suddy__com__>");
						msgParts.addBodyPart(mbp2);

					} else {

						log.log("get resource failed: " + url + " referer: "
							+ this.referURL);
						System.out.println("get resource failed: " + url
							+ "referer: " + this.referURL);

					}
					mur = null;					
					
				}else{
					//local file
					MimeBodyPart mdp=new MimeBodyPart();
					FileDataSource fds=new FileDataSource(url);
					DataHandler dh=new DataHandler(fds);
					mdp.setDataHandler(dh);
			
					url=url.replace(':','_');
					url=url.replace('/','_');
					url=url.replace('\\','_');	
					mdp.setFileName(url);
					msgParts.addBodyPart(mdp); 					
				}				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.log(e);
			} catch (IOException e) {
				log.log("IOException: "+e);

			} catch (Exception e) {
				log.log("Exception: "+e);
				e.printStackTrace();

			} 
		}
		return true;

	}

	public static boolean sendSupperMail(String to, String subject,
			String content, String referURL, String from,String baseURL) {
		log.log(from + " send <" + subject + "> to <" + to + "> text from <"
				+ referURL + ">based<"+baseURL+">");

		HTMLContentParser parse = new HTMLContentParser();
		HTMLContentHandler handler = new MailTextProcessor(to, subject,
				content, referURL, from,baseURL);
		parse.setHandler(handler);
		//String sample = parse.getTextFromFile("f:/suddy/sampleHTML.txt");		
		parse.parse(referURL, content);

		return true;

	}

	public static boolean sendSupperMail(String content) throws UnsupportedEncodingException {
		GenericParameters gp = new GenericParameters(content);
		String sendto = gp.getParameter("sendto");
		//sendto="suddy@localhost";
		//String sendto="suddy@localhost";
		String title = gp.getParameter("title");
		String baseURL = gp.getParameter("baseURL");
		
		String location = gp.getParameter("location");
		String from = gp.getParameter("from");
		from = "'knowledgebase'<"+fromUser+">";
		//from = fromUser;
		Format formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String timeStamp=formatter.format(new Date());
		log.log(timeStamp);
		///////////////////////////////////////////////////////////////
		
		String []replaceList={"转自81.china.com",
				"自:Club.ChinaRen.com",
				"www.6park.com",
				"转自club.china.com",
				"http://sh.mop.com ◎ 源自猫扑上海站：",
				"想看更多火爆美图？快到360美图吧总汇 ^O^",
				"这图不错呀，我来评论几句...",
				};
		//想看更多火爆美图？快到360美图吧总汇 ^O^   这图不错呀，我来评论几句...
		for(int i=0;i<replaceList.length;i++)
		{
			String source=replaceList[i];
			String dest="";
			CharSequence cs1 = source.subSequence(0, source.length());
			CharSequence cs2 = dest.subSequence(0, dest.length());
			content=content.replace(cs1, cs2);
		}
		
		
		int index = content.indexOf("\r\n\r\n");	
		if(index<0){
			index = content.indexOf("\n\r\n\r");
		}
		content = content.substring(index);
		MessageFormat form = new MessageFormat(tabString2);
		//Object[] testArgs = { location, title, sendto };
		Object[] testArgs = { location, title, timeStamp };
		
		content = form.format(testArgs) + content;

		if (from == null) {
			from = "knowledgebasesucker@localhost";
		}
		MailTextProcessor
				.sendSupperMail(sendto, title, content, location, from, baseURL);
		return true;

	}
	public static void testMailAuth()
	{
		Properties props = new Properties();		
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.host", "10.130.1.1");
		props.put("mail.mime.charset", "GBK");
		Session session = Session.getDefaultInstance(props, null);
		Transport tr;
		String to="zhangxl@channelsoft.com";
		InternetAddress[] tos;
		
		
		try {
			tr = session.getTransport("smtp");
			
			tr.connect("mail.channelsoft.com", 
						"zhangxl@channelsoft.com",
						"kkredskirt"
						);
			Message mailMessage = new MimeMessage(session);
			tos = InternetAddress.parse(to);			
			mailMessage.setFrom(new InternetAddress(fromUser));
			mailMessage.setRecipients(Message.RecipientType.TO, tos);
			mailMessage.setContent("ewre","text/plain");
			mailMessage.setText("text");
			mailMessage.saveChanges();
			tr.send(mailMessage);
		
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);

			e.printStackTrace();
		}
	
		
		
	}
	public static void testParser(String[] args) {
		// TODO Auto-generated method stub
		HTMLContentParser parse = new HTMLContentParser();
		String sample = parse.getTextFromFile("f:/suddy/sampleHTML.txt");
		if (false) {
			parse.setHandler(new MailTextProcessor());
			parse.parse("http://localhost/java/", sample);
			//http://gocom.primeton.com/blog/resserver.php?blogId=6&resource=goComEAT1.0.JPG
		}
		
		/*
		MailTextProcessor.sendSupperMail("suddy@localhost", "test image",
				sample, "http://suddy.channel.com/java/", "suddy@localhost","");
		*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testMailAuth();
	}

}
