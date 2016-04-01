/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SampleWorkflowSessions {
	private static WorkflowSession wfs=null;
	public static WorkflowSession getSampleWorkflowSession(){
		if(wfs==null){
			
			wfs=new WorkflowSession();
			
			
		}
		
		return wfs;
		
	}
	public static void main(String[] args) {
	}
}
