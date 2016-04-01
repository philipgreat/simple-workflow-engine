/*
 * Created on 2005-3-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WorkflowInstance implements Serializable {
	private List actionHistory=null;
	private String workflowName="";
	private String currentActivityName=""; 
	public static void main(String[] args) {
		System.out.println("xixi".hashCode());
		
		System.out.println("x".hashCode());
		System.out.println("xixi"==new String("xixi"));
		System.out.println("xixi"=="xixi");
		System.out.println(new String("xixi")==new String("xixi"));
		System.out.println(new String("xixi").hashCode()==new String("xixi").hashCode());
		
		
	}
	
	/**
	 * @return
	 */
	public List getActionHistory() {
		return actionHistory;
	}

	/**
	 * @return
	 */
	public String getCurrentActivityName() {
		return currentActivityName;
	}

	/**
	 * @return
	 */
	public String getWorkflowName() {
		return workflowName;
	}

	/**
	 * @param string
	 */
	public void setCurrentActivityName(String string) {
		currentActivityName = string;
	}

	/**
	 * @param string
	 */
	public void setWorkflowName(String string) {
		workflowName = string;
	}

}
