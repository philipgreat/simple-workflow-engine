<%@ page language='java' %><%@ page import='java.sql.*' %><%@ page import='javax.sql.*' %><%@ page import='java.util.*' %><%@ page import='com.msn.cbbp.workflow.*' %><%@ page contentType='text/vnd.wap.wml'%><%

	WorkflowSession wfsession=SampleWorkflowSessions.getSampleWorkflowSession();
	Activity activity=wfsession.getCurrentActivity();
	
	List actionList =activity.getActionList();
	List inputList =activity.getInputList();
	

%><?xml version="1.0"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<!-- Source Generated by WML Deck Decoder -->

<wml>
  <card id="home" title="<%=activity.getLabel()%>" newcontext="true">
    <p >
	<em>login</em>
	<img  localsrc="house" alt="ss" src=""/>
    </p>
    <p>
    
<%

	for(int i=0;i<inputList.size();i++){
		Input input=(Input)inputList.get(i);
		String value=wfsession.parseMessage(input.getValue());
	%>
	<%=input.getLabel()%>: <input  name='<%=input.getName()%>' value='<%=VariantTool.toTextUTF(value)%>'/>
	<%
	
	}
%>
    
    <select name="event" value="0" title="Select">
<%
	String actions="|";
	for(int i=0;i<actionList.size();i++){
		Action action=(Action)actionList.get(i);
		actions+=action.getEvent()+"|";
	%>	
	<option value="<%=action.getEvent()%>"><%=action.getLabel()%></option>
	<%
	
	}
%>
	
    </select>
    </p>
    <p>
        <do type="accept" label="submit">
        	<go href="wmlworkflow.jsp" method="get">
        		<postfield name="event" value="$event"/>

 <%

	for(int i=0;i<inputList.size();i++){
		Input input=(Input)inputList.get(i);
		String value=wfsession.parseMessage(input.getValue());
	%>
			<postfield name="<%=input.getName()%>" value="$<%=input.getName()%>"/>
	<%
	
	}
%>       	
        	</go>
        </do>
    </p>
  </card>
  

</wml>