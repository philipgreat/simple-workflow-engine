/*
 * JSP generated by Resin 2.1.16 (built Tue Feb 15 11:12:27 PST 2005)
 */

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.msn.cbbp.workflow.*;

public class _workflow__jsp extends com.caucho.jsp.JavaPage{
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
	Context ctx = new InitialContext();
	//lookup the  role;
	WorkflowPort wp=null;
	try{
		wp=(WorkflowPort)ctx.lookup(activity.getRoleName());
	}catch(Exception e){
	
	}
	
	
	if(isNeed==true){
		pagepath="wait.jsp";
		
	}else if(wp!=null){
		//this.isNeedShowWaiting=false;
		Thread t=Thread.currentThread();
		//t.sleep(1000);
		//get event
		//WorkflowPort
		
		event=wp.service(request,response,wfsession);
		//System.out.println("the returned event is: "+event);
		pagepath="workflow.jsp?event="+event;
		
		//wfsession.clearVariant();
	}
	System.out.println(new java.util.Date()+": is need waiting: "+isNeed+"; pagepath "+pagepath);
	//check if it is a server side tx
	response.setHeader("Cache-Control","no-cache");	
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0); 
	response.setHeader("Pragma","no-cache");
	
	//request.getRequestDispatcher(pagepath).forward(request,response);
		


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
    if (com.caucho.util.CauchoSystem.getVersionId() != -2089842219)
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
    _caucho_line_map = new com.caucho.java.LineMap("_workflow__jsp.java", "/workflow/workflow.jsp");
    _caucho_line_map.add(6, 1);
    _caucho_line_map.add(1, 30);
    _caucho_line_map.add(9, 36);
    _caucho_line_map.add(64, 92);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("workflow.jsp"), 1156669427359L, 1615L);
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
      _jsp_string1 = "\r\n\r\n\r\n".getBytes("GBK");
      _jsp_string0 = "\r\n".getBytes("GBK");
    } catch (java.io.UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
