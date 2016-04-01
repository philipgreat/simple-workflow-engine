
<%@ page contentType='text/html; charset=GBK'%>
<%@ page import='java.util.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=big5">
<title>wait............</title>
</head>

淏婓揭燴ㄛ ③脹渾............

<form action='workflow.jsp' name='submitdata'>
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