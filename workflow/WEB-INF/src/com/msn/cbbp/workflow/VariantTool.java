/*
 * Created on 2005-3-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.sql.Date;
import java.sql.Time;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VariantTool {
	
	

	public static String hexmap = "0123456789ABCDEF";
	public static String transToHex(byte b) {
		int i = ((b) >> 4) & 0x0000000f;
		int j = (b) & 0x0000000f;
		StringBuffer sb = new StringBuffer(2);
		sb.append(hexmap.charAt(i));
		sb.append(hexmap.charAt(j));
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(getInt("100"));
		System.out.println(getDate("2004-5-9"));
		System.out.println(getTime("15:12:00"));
		System.out.println(toTextUTF("%$$4"));

	}
	public static int getInt(String val) {
		return Integer.parseInt(val);
	}
	public static Date getDate(String val) {
		return Date.valueOf(val);
	}
	public static long getLong(String val) {
		return Long.parseLong(val);
	}
	public static String getString(String val) {
		return val.trim();
	}
	public static Time getTime(String val) {
		return Time.valueOf(val);
	}

	public static String toTextUTF(String gbk) {
		StringBuffer sb = new StringBuffer(2);
		try {

			byte[] bts = gbk.getBytes("UTF-16");
			for (int i = 2; i < bts.length; i++) {
				if ((i - 2) % 2 == 0) {
					sb.append("&#x");
				}
				sb.append(transToHex(bts[i]));
				if ((i - 2) % 2 == 1) {
					sb.append(";");
				}
			}
			return sb.toString();

		} catch (Exception e) {
			return gbk;
			//System.out.println(e.getMessage());
		}
	}
	
	

}
