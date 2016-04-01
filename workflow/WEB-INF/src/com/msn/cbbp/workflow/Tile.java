/*
 * Created on 2005-3-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import org.xml.sax.Attributes;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Tile implements IXMLWorkflowInstantiatable {
	private String include="";
	private String clazz="";
	
	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IXMLInstanceable#fromXMLAttributes(org.xml.sax.Attributes)
	 */
	 
	public void fromXMLAttributes(Workflow workflowContext,Attributes atts) {
		// TODO Auto-generated method stub
		this.include=atts.getValue("include");
		this.clazz=atts.getValue("class");
	}

	public static void main(String[] args) {
	}
	/**
	 * @return
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @return
	 */
	public String getInclude() {
		return include;
	}

	/**
	 * @param string
	 */
	public void setClazz(String string) {
		clazz = string;
	}

	/**
	 * @param string
	 */
	public void setInclude(String string) {
		include = string;
	}

}
