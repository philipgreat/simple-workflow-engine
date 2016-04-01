<%@ page language='java' %>
<%@ page import='java.io.*' %>
<%@ page import='java.nio.ByteBuffer' %>
<%@ page contentType='text/plain; charset=GBK'%>
<%
	try{	
		ServletInputStream in=request.getInputStream();
	
		byte[] buf = new byte[1024];
        int len;
        String parameters=null;
		String encoding=request.getHeader("Encoding");
		
        int length=request.getIntHeader("Content-Length");
        if(length<=0){
        	out.print("the length of the head is : "+length);
        	return;
        }
		ByteBuffer  buffer=ByteBuffer.allocate(length);

        if(encoding==null){
        	encoding="UTF-8";
        }
        while ((len = in.read(buf)) > 0) {
            buffer.put(buf, 0, len);   
        }
        parameters=new String(buffer.array(),encoding);         
		BufferedWriter outf = new BufferedWriter(new FileWriter(request.getHeader("FileName")));
        outf.write(parameters);
        outf.close();

		in.close();
        buf=null;
        out.println();
        out.println(parameters);
	
	}catch(Exception e){
		
		e.printStackTrace(response.getWriter());
	}
%>
