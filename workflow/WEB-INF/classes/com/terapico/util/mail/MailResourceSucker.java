package com.terapico.util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;

public class MailResourceSucker {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws IOException 
	 */
	static String getFileNameFromURL(String urlString)
	{
		try {
			URL url=new URL(urlString);
			String filePath=url.getFile();
			int index=filePath.lastIndexOf('/');
			return filePath.substring(index+1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "image101.jpg";	
		
	}
	public static MailURLResource getResource(String urlstr,String refer) throws IOException 
	{
		InputStream is=null;
		byte[] body=null;
		String contentType=null;
		ByteBuffer bb=null;
		ByteBuffer.allocate(1024*1024*2);
		
		String urldec="";		
		String fileName=getFileNameFromURL(urlstr);
		try {
			urldec=URLDecoder.decode(urlstr,
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
	        // Create a URLConnection object for a URL
			
			
	        URL url = new URL(urldec);
	        URLConnection conn = url.openConnection();
	        if(refer!=null){
		        conn.setRequestProperty("Referer", refer);
		        //conn.setRequestProperty("Accept", "*/*");
		        //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.7.12) Gecko/20050915 Firefox/1.0.7");
	        }
	        if(conn.getContentType().length()==0){
		        contentType="image/jpeg";
	        }else{
		        contentType=conn.getContentType();		        	
	        }
	        if(conn.getContentLength()>0){
		        bb=ByteBuffer.allocate(conn.getContentLength());
		        //System.out.println(conn.getHeaderField("Set-Cookie"));
		        
		        body=new byte[1024*8]; 
		        is=conn.getInputStream();
		        //while()
		        int len=0;
		        
		        while((len=is.read(body))>0)
		        {
		        	bb.put(body, 0, len);
		        } 
		        
		        
		        return new MailURLResource(fileName,contentType,bb.array());
	        }else if(conn.getContentLength()<0){
	        	//sometimes the length is unkown but it is still ok
	        	bb=ByteBuffer.allocate(1024*1024);
	        	//enough to use
		        int total=0;
		        body=new byte[1024*8]; 
		        is=conn.getInputStream();
		        int len=0;		        
		        while((len=is.read(body))>0)
		        {
		        	total+=len;
		        	bb.put(body, 0, len);
		        } 
		        	        
		        ByteBuffer b2=ByteBuffer.allocate(total);
		        b2.put(bb.array(),0,total);  
		        return new MailURLResource(fileName,contentType,b2.array());
	        }
	    } finally{
	    	
	    	try {
				if(is!=null) is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    }
	    return null;
		
	}

	public static void main(String[] args) 
	{

		//getResource("http://newbbs.china.com/jsp/etc/main/ads/bbspaymail.gif",null);
		String urldec="";
		try {
			urldec=URLDecoder.decode("http://www.mn68.com/downloadimages0930/20051230102017/20051230102017%2810%29_2005123010202010.jpg","GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			getResource(urldec,
					"http://www.66735.com/simple/index.php?t66640.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//http://club.china.com/jsp/pub/staticFile/images/3926/11/20/34.jp
	}
	public static void cookieTest(){
		
		// TODO Auto-generated method stub
		//Set-Cookie
		
	    String cookie="";
	    
	    
	    try {
	        // Create a URLConnection object for a URL
	        URL url = new URL("http://newbbs.china.com/jsp/etc/main/ads/bbspaymail.gif");
	        URLConnection conn = url.openConnection();
	        
	        // Set the cookie value to send
	        //http://www-128.ibm.com/developerworks/cn
	        //conn.setRequestProperty("Cookie", "name1=value1; name2=value2");
	        //conn.setRequestProperty("Refere", "http://www-128.ibm.com/developerworks/cn");
	        //http://www-128.ibm.com/developerworks/cn/
	        System.out.println(conn.getContentLength());
	        System.out.println(conn.getHeaderField("Set-Cookie"));
	        cookie=conn.getHeaderField("Set-Cookie");
	        
	        InputStream is=conn.getInputStream();
	        is.close();
	        
	        // Send the request to the server
	        //conn.connect();
	    } catch (MalformedURLException e) {
	    } catch (IOException e) {
	    }
	    
	    
		try {
	        // Create a URLConnection object for a URL
	        URL url = new URL("http://www-128.ibm.com/developerworks/cn/i/icon-email.gif");
	        URLConnection conn = url.openConnection();
	        
	        // Set the cookie value to send
	        //http://www-128.ibm.com/developerworks/cn
	        //conn.setRequestProperty("Cookie", "name1=value1; name2=value2");
	        conn.setRequestProperty("Cookie", cookie);
	        conn.setRequestProperty("Refere", "http://www-128.ibm.com/developerworks/cn");
	        System.out.println(conn.getContentLength());
	        
	        InputStream is=conn.getInputStream();
	        
	        // Send the request to the server
	        conn.connect();
	    } catch (MalformedURLException e) {
	    } catch (IOException e) {
	    }
		
	}
}
