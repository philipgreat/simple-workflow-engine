/*
 * JSP generated by Resin 2.1.16 (built Tue Feb 15 11:12:27 PST 2005)
 */

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.io.*;
import com.msn.cbbp.workflow.*;
import com.terapico.util.mail.MailTextProcessor;
import com.terapico.util.mail.GenericParameters;
import com.terapico.util.Logger;
import java.nio.ByteBuffer;
import tool.*;

public class _smartmail__jsp extends com.caucho.jsp.JavaPage{
  private boolean _caucho_isDead;
  
	static Logger log = new Logger("smartMail.jsp");

  
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
      pageContext.write(_jsp_string1, 0, _jsp_string1.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      pageContext.write(_jsp_string0, 0, _jsp_string0.length);
      
	try{
	
	ServletInputStream in=request.getInputStream();
	
	byte[] buf = new byte[1024];
        int len;
        String parameters=null;
	String encoding=request.getHeader("Encoding");
        int length=request.getIntHeader("Content-Length");
	ByteBuffer  buffer=ByteBuffer.allocate(length);

        if(encoding==null){
        	encoding="UTF-8";
        }
        while ((len = in.read(buf)) > 0) {
            buffer.put(buf, 0, len);   
        }
        parameters=new String(buffer.array(),encoding);         
	MailTextProcessor.sendSupperMail(parameters);
	TextFile file=new TextFile("./record");
	file.saveText(parameters);        
        in.close();
        buf=null;
        
	
	}catch(Exception e){
		out.println(e);
		e.printStackTrace();
		log.log(e);
	}

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
    _caucho_line_map = new com.caucho.java.LineMap("_smartmail__jsp.java", "/workflow/smartMail.jsp");
    _caucho_line_map.add(9, 1);
    _caucho_line_map.add(10, 18);
    _caucho_line_map.add(1, 36);
    _caucho_line_map.add(13, 46);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("smartMail.jsp"), 1235038726531L, 1267L);
    _caucho_depends.add(depend);
  }

  protected void _caucho_clearDepends()
  {
    _caucho_depends.clear();
  }

  private static byte []_jsp_string0;
  private static byte []_jsp_string1;
  static {
    try {
      _jsp_string0 = "\r\n".getBytes("GBK");
      _jsp_string1 = "  \r\n".getBytes("GBK");
    } catch (java.io.UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
