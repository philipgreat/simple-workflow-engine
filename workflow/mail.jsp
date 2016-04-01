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
	String pagepath="show.jsp";
	boolean isNeed=wfsession.isNeedShowWaiting();
	System.out.println("is need waiting: "+isNeed);
	if(isNeed){
		pagepath="mailwait.jsp";
		
	}else if(activity.getRoleName().equals("mailer")){
		//this.isNeedShowWaiting=false;
		//Thread t=Thread.currentThread();
		//t.sleep(3000);
		Mailer mailer=new Mailer();
		event=mailer.service(request,response,wfsession);
		
		//event=RandomServerEvent.getRandomEvent(2);
		//System.out.println("the returned event is: "+event);
		pagepath="mail.jsp?event="+event;
		
		
		//wfsession.clearVariant();
	}
	
	
	
	//check if it is a server side tx
	

	//request.getRequestDispatcher(pagepath).forward(request,response);
	response.setHeader("Cache-Control","no-cache");	
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0); 
	response.setHeader("Pragma","no-cache");
%>
<jsp:forward page="<%=pagepath%>"/> 