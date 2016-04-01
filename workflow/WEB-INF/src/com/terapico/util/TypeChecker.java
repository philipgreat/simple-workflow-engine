/*
 * Created on 2003-11-30
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.terapico.util;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeChecker {

	public static boolean checkStringMin(String str, String min) {
		//最简单条件表达式为 "17:22"表示长度的单位
		String[] ar = min.split("[:]");
		if (ar.length == 2) {
			int mi = Integer.parseInt(ar[0]);
			int ma = Integer.parseInt(ar[1]);
			return (str.length() >= mi && str.length() <= ma);
		} else {
			//这里应该给出一个配置错误的信息
			//TODO: Set A Error here
			return false;
		}



	}
	public static boolean checkStringMax(String str, String max) {
		//如果为空,则返回true
		//如果是其他情况则判断其正则表达式max与str是否配合
		if (max == null || "".equals(max)) {
			return true;
		} else {
			Pattern p = Pattern.compile(max);
			Matcher m = p.matcher(str);
			return m.matches();
		}
		//return true;
	}
	public static boolean checkIntegerMin(int i, int min) {

		return i >= min;

	}

	public static boolean checkIntegerMax(int i, int max) {
		return i <= max;
	}
	public static boolean checkIntMin(int i, int min) {

		return i >= min;

	}

	public static boolean checkIntMax(int i, int max) {
		return i <= max;
	}
	public static boolean checkLongMin(long l, long min) {
		return l >= min;
	}
	public static boolean checkLongMax(long l, long max) {
		return l <= max;
	}
	public static boolean checkDoubleMin(double d, double min) {
		return d >= min;
	}
	public static boolean checkDoubleMax(double d, double max) {
		return d <= max;
	}

	public static boolean checkFloatMin(float f, float min) {
		return f >= min;
	}

	public static boolean checkFloatMax(float f, float max) {
		return f <= max;
	}
	public static boolean checkDateMin(Date dt, Date min) {
		return dt.after(min);
	}
	public static boolean checkDateMax(Date dt, Date max) {
		return dt.before(max);
	}
}				