<%@ page contentType='text/vnd.wap.wml'%><?xml version="1.0"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<card id="cardname" >
<p>
	&#x5723;&#x8bde;&#x5feb;&#x4e50;!--&#x559c;&#x6765;

<%
        java.util.Enumeration names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String)names.nextElement();
            out.println("<br/>"+name+": "+request.getHeader(name));
            System.err.println(name+": "+request.getHeader(name));
        }
%>
<%
        names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String)names.nextElement();
            out.println("<br/>"+name+": "+request.getParameter(name));
            System.err.println(name+": "+request.getParameter(name));
        }
%>

</p>
</card>
</wml>

