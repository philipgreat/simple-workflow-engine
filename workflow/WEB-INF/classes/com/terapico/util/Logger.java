/*
 * Created on 2003-11-7
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.terapico.util;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.terapico.sockcomm.UTest;
public class Logger {
	private Object m_objLog;
	private long preDate = 0;
	private Date curDate = null;
	private int threadid = 0;
	Format formatter = new SimpleDateFormat("dd/MM HH:mm:ss.SSS");
	NumberFormat numformat = new DecimalFormat("000,000");
	private static String hostIP = null;
	private boolean isShowHostName = false;
	private boolean isShowThreadName = true;

	static {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// Get IP Address
			hostIP = addr.getHostName();
			;
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
	}
	public Logger(Object o) {
		m_objLog = o;
		//this.finalize() ;
	}

	public void log(Object o) {
		String send = getLoggerString("trace", ""+o);
		send(send);
	}
	public void log(int i) {
		String send = getLoggerString("trace", "[int]: "+i);
		send(send);
	}
	public void error(Object o) {
		String send = getLoggerString("error", o.toString());
		send(send);
	}
	public void info(Object o) {
		String send = getLoggerString("info", o.toString());
		send(send);
	}
	public String getLoggerString(String errorType, String content) {
		long dur = 0;
		String threadName = Thread.currentThread().getName();
		curDate = new Date();

		if (preDate == 0) {
			// first time with the same logger
			dur = 0;
		} else {
			dur = curDate.getTime() - preDate;
		}
		preDate = curDate.getTime();

		StringBuffer sb = new StringBuffer(100);

		sb.append("[");
		sb.append(formatter.format(curDate));
		sb.append(" " + numformat.format(dur) + "ms]");
		if (isShowHostName) {
			sb.append(hostIP + "/");
		}
		if (isShowThreadName) {
			sb.append(threadName + "/");
		}
		if(m_objLog instanceof Class){
			sb.append(((Class)m_objLog).getName());
		}else{
			sb.append(m_objLog.toString());
		
		}
		
		sb.append("\t" + errorType + ":\t");
		sb.append(content);
		sb.append("\r\n");

		return sb.toString();

	}
	private static void send(String send){
		UTest.send(send);
	}

	/**
	 * @param b
	 */
	public void setShowThreadName(boolean b) {
		isShowThreadName = b;
	}

	/**
	 * @param b
	 */
	public void setShowHostName(boolean b) {
		isShowHostName = b;
	}

}
