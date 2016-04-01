<%@ page language=java %>
<%@ page import='java.io.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>  
<%@ page import='com.terapico.util.mail.MailTextProcessor' %>
<%@ page import='com.terapico.util.mail.GenericParameters' %>
<%@ page import='com.terapico.util.Logger' %>
<%@ page import='java.nio.ByteBuffer' %>
<%@ page import='tool.*' %>
<%@ page contentType='text/html; charset=GBK'%>
<%!
	static Logger log = new Logger("smartMail.jsp");
%>
<%
	try{
	
	ServletInputStream in=request.getInputStream();
	
	byte[] buf = new byte[1024];
        int len;
        String parameters=null;
	String encoding=request.getHeader("Encoding");
        int length=request.getIntHeader("Content-Length");
	ByteBuffer  buffer=ByteBuffer.allocate(length);

        if(encoding==null){
        	encoding="UTF-8";
        }
        while ((len = in.read(buf)) > 0) {
            buffer.put(buf, 0, len);   
        }
        parameters=new String(buffer.array(),encoding);         
	MailTextProcessor.sendSupperMail(parameters);
	TextFile file=new TextFile("./record");
	file.saveText(parameters);        
        in.close();
        buf=null;
        
	
	}catch(Exception e){
		out.println(e);
		e.printStackTrace();
		log.log(e);
	}
%>