
<%@ page contentType='text/html; charset=Big5'%>
<%@ page import='java.util.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>

<html>
<head>
<!-- �[�W�H�U�o�@��A�H�K���ýX���� -->
<meta http-equiv="Content-Type" content="text/html; charset=big5">
<title>�d���O</title>
</head>

���b�B�z�A �е���...................

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