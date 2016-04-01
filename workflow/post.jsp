<%@ page language=java %>
<%@ page import='java.sql.*' %>
<%@ page import='javax.sql.*' %>
<%@ page import='javax.naming.*' %>
<%@ page contentType='text/html; charset=GBK'%>
<%@ page import='java.io.*' %>
<%@ page import='GenericParameters' %>
<%
	ServletInputStream in=request.getInputStream();
	
	byte[] buf = new byte[1024*1024];
        int len;
        System.out.println("start>");
        String parameters=null;
        if ((len = in.read(buf)) > 0) {
        	//out.write(buf, 0, len);
            //System.out.write(buf, 0, len);
            parameters = new String(buf, 0, len,"UTF-8");         
            
            //System.out.println(parameters);
        }
        GenericParameters gp=new GenericParameters(parameters);
        System.out.println(gp.getParameter("title"));
        
        buf = null;
        //read title and other meta data
        
	mailer.sendTextMail(
		"chang.luminarc@gmail.com",
		gp.getParameter("title"),
		parameters,
		"suddy@localhost"	
	);
	
        
        in.close();
        buf=null;
        


%>