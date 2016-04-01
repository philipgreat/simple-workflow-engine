<%@ page import='tool.*' %>
<%

	String ret = request.getServletPath();
	TextFile file=new TextFile("./record");
	file.saveText("love is ÖÐÎÄ²âÊÔblue");
%><%=ret%>