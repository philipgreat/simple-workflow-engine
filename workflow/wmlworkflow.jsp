<%@ page language=java %>
<%@ page import='java.sql.*' %>
<%@ page import='javax.sql.*' %>
<%@ page import='javax.naming.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>
<%@ page contentType='text/html; charset=GBK'%>


<%

	String event=request.getParameter("event");
	
	if(null==event){
	
		event="0";
	}
	WorkflowSession wfsession=SampleWorkflowSessions.getSampleWorkflowSession();
	
	wfsession.storeVariant(request);
	
	wfsession.testEvent(event);
	
	
	Activity activity=wfsession.getCurrentActivity();
	String pagepath="showwml.jsp";
	boolean isNeed=wfsession.isNeedShowWaiting();
	Context ctx = new InitialContext();
	//lookup the  role;
	WorkflowPort wp=null;
	try{
		wp=(WorkflowPort)ctx.lookup(activity.getRoleName());
	}catch(Exception e){
	
	}
	
	
	if(isNeed==true){
		pagepath="waitwml.jsp";
		
	}else if(wp!=null){
		//this.isNeedShowWaiting=false;
		Thread t=Thread.currentThread();
		t.sleep(1000);
		//get event
		//WorkflowPort
		
		event=wp.service(request,response,wfsession);
		//System.out.println("the returned event is: "+event);
		pagepath="wmlworkflow.jsp?event="+event;
		
		//wfsession.clearVariant();
	}
	
	//check if it is a server side tx
	response.setHeader("Cache-Control","no-cache");	
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0); 
	response.setHeader("Pragma","no-cache");
	
	//request.getRequestDispatcher(pagepath).forward(request,response);
		

%>
<jsp:forward page="<%=pagepath%>"/> 