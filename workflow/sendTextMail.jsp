<%@ page language=java %>
<%@ page import='java.sql.*' %>
<%@ page import='javax.sql.*' %>
<%@ page import='javax.naming.*' %>
<%@ page import='com.msn.cbbp.workflow.*' %>
<%@ page contentType='text/html; charset=GBK'%>

<%
	Mailer mailer=new Mailer();

%>


<%
Context env = (Context) new InitialContext().lookup("java:comp/env");
DataSource source = (DataSource) env.lookup("jdbc/csi_info");

Connection conn = source.getConnection();
try {
  Statement stmt = conn.createStatement();

  ResultSet rs=stmt.executeQuery("select * from help limit 441,3000");
  

  while(rs.next()){

	//"chang.luminarc@gmail.com",
	if(true){
	mailer.sendTextMail(
		"chang.luminarc@gmail.com",
		rs.getString(1)+">"+rs.getString(3)+"@"+rs.getString(2),
		rs.getString(4),
		"suddy@localhost"	
	);
	
	
	}else{
	mailer.sendTextMail(
		"suddy@localhost",
		rs.getString(1)+">"+rs.getString(3)+"@"+rs.getString(2),
		rs.getString(4),
		"suddy@localhost"	
	);
	}
	
   }
  
} finally {
  conn.close();
}

%>

<h1>Thank you, </h1>