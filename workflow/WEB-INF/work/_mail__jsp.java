/*
 * JSP generated by Resin 2.1.14 (built Thu Jul  1 18:39:55 PDT 2004)
 */

import javax.servlet.jsp.JspFactory;

import com.msn.cbbp.workflow.Activity;
import com.msn.cbbp.workflow.Mailer;
import com.msn.cbbp.workflow.SampleWorkflowSessions;
import com.msn.cbbp.workflow.WorkflowSession;

public class _mail__jsp extends com.caucho.jsp.JavaPage{
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    com.caucho.jsp.QPageContext pageContext = (com.caucho.jsp.QPageContext) com.caucho.jsp.QJspFactory.create().getPageContext(this, request, response, null, true, 8192, true);
    javax.servlet.jsp.JspWriter out = (javax.servlet.jsp.JspWriter) pageContext.getOut();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    javax.servlet.http.HttpSession session = pageContext.getSession();
    javax.servlet.ServletContext application = pageContext.getServletContext();
    response.setContentType("text/html; charset=GBK");
    request.setCharacterEncoding("GBK");
    try {
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string1, 0, _jsp_string1.length);
      

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

      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.forward(pagepath);
      if (true) return;
      pageContext.write(_jsp_string2, 0, _jsp_string2.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }
  }

  private com.caucho.java.LineMap _caucho_line_map;
  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.util.CauchoSystem.getVersionId() != -2089842221)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Depend depend;
      depend = (com.caucho.vfs.Depend) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public com.caucho.java.LineMap _caucho_getLineMap()
  {
    return _caucho_line_map;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.java.LineMap lineMap,
                   com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    mergePath.addClassPath(getClass().getClassLoader());
    _caucho_line_map = new com.caucho.java.LineMap("_mail__jsp.java", "/workflow/mail.jsp");
    _caucho_line_map.add(6, 1);
    _caucho_line_map.add(1, 30);
    _caucho_line_map.add(8, 36);
    _caucho_line_map.add(56, 85);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("mail.jsp"), 1112022881453L, 1470L);
    _caucho_depends.add(depend);
  }

  protected void _caucho_clearDepends()
  {
    _caucho_depends.clear();
  }

  private static byte []_jsp_string2;
  private static byte []_jsp_string1;
  private static byte []_jsp_string0;
  static {
    try {
      _jsp_string2 = " ".getBytes("GBK");
      _jsp_string1 = "\r\n\r\n".getBytes("GBK");
      _jsp_string0 = "\r\n".getBytes("GBK");
    } catch (java.io.UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
