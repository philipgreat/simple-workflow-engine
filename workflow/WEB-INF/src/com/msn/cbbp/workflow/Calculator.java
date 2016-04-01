/*
 * Created on 2005-4-1
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Calculator {

	public static void main(String[] args) {
	}
	public static int toNumber(String strval) {
		String intstr = strval;
		int val = 0;
		if (intstr == null) {
			return 0;
		}
		if (intstr.matches("^\\d{1,10}$")) {
			val = Integer.parseInt(intstr);
			return val;
		} else {
			return 0;
		}
	}
	 
	public static int inc(WorkflowSession workflowsession, String key) {
		String intstr = workflowsession.getVariant(key);
		int val = toNumber(intstr);
		val++;
		workflowsession.addVariant(key, val + "");
		return val;
	}
}
