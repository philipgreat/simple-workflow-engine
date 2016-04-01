<%@ page language=java %>
<%@ page import='java.io.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>
<%@ page import='com.terapico.util.mail.*' %>
<%@ page contentType='text/html; charset=GBK'%>
<%
	Mailer mailer=new Mailer();
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
        //read title and other meta data
        String sendto=gp.getParameter("sendto");
        String title=gp.getParameter("title");
        System.out.println("sendto="+sendto+"; title="+title);
	
	mailer.sendHTMLMail(
		sendto,
		title,
		parameters,
		"knowledgebasesucker@localhost",
		"text/html; charset=utf-8"
	);
        
        in.close();
        buf=null;
        


%>