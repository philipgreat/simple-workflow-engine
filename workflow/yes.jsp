<%@ page import='tool.*' %>
<%

	String ret = request.getServletPath();
	TextFile file=new TextFile("./record");
	file.saveText("love is ���Ĳ���blue");
%><%=ret%>