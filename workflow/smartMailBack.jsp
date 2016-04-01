<%@ page language=java %>
<%@ page import='java.io.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>  
<%@ page import='com.terapico.util.mail.MailTextProcessor' %>
<%@ page import='com.terapico.util.mail.GenericParameters' %>
<%@ page import='com.terapico.util.Logger' %>
<%@ page contentType='text/html; charset=GBK'%>
<%!
	static Logger log = new Logger("MailTextProcessor");
%>
<%
	try{

	ServletInputStream in=request.getInputStream();
	
	byte[] buf = new byte[1024];
        int len;
        System.out.println("mailer>");
        String parameters=null;
        StringBuffer sb=new StringBuffer();
        while ((len = in.read(buf)) > 0) {
            sb.append( new String(buf, 0, len,"UTF-8"));     
        }
        parameters=sb.toString();
        //System.out.println(parameters);
        GenericParameters gp=new GenericParameters(parameters);
        //System.out.println(gp.getParameter("title"));
        //System.out.println("len = "+request.getContentLength());
        buf = null;
        sb = null;
        String sendto=gp.getParameter("sendto");
        //String sendto="suddy@localhost";
        String title=gp.getParameter("title");
        String location=gp.getParameter("location");
        
        System.out.println("sentto="+sendto+"; title="+title+"; location="+location);
       
	MailTextProcessor.sendSupperMail(
		sendto,
		title,
		parameters,
		location,
		"knowledgebasesucker@localhost"	
	);
        
        in.close();
        buf=null;
        
	
	}catch(Exception e){
		out.println(e);
		log.log(e);
	}
%>