<%@ page language=java %>
<%@ page import='java.sql.*' %>
<%@ page import='javax.sql.*' %>
<%@ page import='java.util.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>
<%@ page contentType='text/html; charset=GBK'%>
<%

	WorkflowSession wfsession=SampleWorkflowSessions.getSampleWorkflowSession();
	Activity activity=wfsession.getCurrentActivity();
	
	List actionList =activity.getActionList();
	List inputList =activity.getInputList();
	

%>
<script>
function setEvent(form,val){
	
	form['event'].value=val;
	form.submit();
		
	var elements=form.elements;	
	for(var i=0;i<elements.length;i++){
		
		elements[i].disabled=true;
		//alert(elements[i].type);
	
	}
	
	
}
function setEvent2(formName,val){
	var form=document.forms[formName];
	form['event'].value=val;
	form.submit();
		
	var elements=form.elements;	
	for(var i=0;i<elements.length;i++){
		
		elements[i].disabled=true;
		//alert(elements[i].type);
	
	}
	
	
}
</script>
<html>
<head>
<title><%=activity.getLabel()%>
</title>

</head>

<body>

<form action='workflow.jsp' name='form2'>

<%
	String actions="|";
	for(int i=0;i<actionList.size();i++){
		Action action=(Action)actionList.get(i);
		actions+=action.getEvent()+"|";
	%>
	<a href="javascript:setEvent2('form2','<%=action.getEvent()%>')">[<%=action.getLabel()%>]</a>
	<!--
	<input type='submit' onclick="setEvent(this.form,'<%=action.getEvent()%>')" value='<%=action.getLabel()%>'>
	
	-->
	<%
	
	}
%>

<br>

<font size='+3'><%=activity.getName()%>: <%=activity.getLabel()%></font>
<br>
<%

	for(int i=0;i<inputList.size();i++){
		Input input=(Input)inputList.get(i);
		String value=wfsession.parseMessage(input.getValue());
%>
	<%=input.getType().equals("hidden")?"":input.getLabel()+":"%> 
	<input type='<%=input.getType()%>' name='<%=input.getName()%>' value='<%=value%>'>
	<%
	
	}
%>
<input type='hidden' name='event' value='<%=actions%>'>

<jsp:include page='<%=activity.getPage()!=null?activity.getPage():"null"%>'/>
</form>
current variant:
<hr>/*
<%
	//WorkflowSession wfsession=SampleWorkflowSessions.getSampleWorkflowSession();
	inputList=wfsession.getVariantList();
	for(int i=0;i<inputList.size();i++){
		UserVariant input=(UserVariant)inputList.get(i);
%>
	<%=input.getName()%> = <%=input.getValue()%> <br>

<%
	
	}
%><br>
*/
<hr>

<%=activity%>



</body>
</html>