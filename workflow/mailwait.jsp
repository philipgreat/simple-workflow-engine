
<%@ page contentType='text/html; charset=Big5'%>
<%@ page import='java.util.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>

<html>
<head>
<!-- 加上以下這一行，以免有亂碼產生 -->
<meta http-equiv="Content-Type" content="text/html; charset=big5">
<title>留言板</title>
</head>

正在處理， 請等待...................

<form action='mail.jsp' name='submitdata'>
<%
	WorkflowSession wfsession=SampleWorkflowSessions.getSampleWorkflowSession();
	List inputList=wfsession.getVariantList();
	for(int i=0;i<inputList.size();i++){
		UserVariant input=(UserVariant)inputList.get(i);
	%>
	<input type='hidden' name='<%=input.getName()%>' value='<%=input.getValue()%>'>
	<%
	
	}
%>
</form>

<script>
	document.submitdata.submit();
	//window.location='workflow.jsp';

</script>
</html>