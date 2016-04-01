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
public class Label implements IXMLWorkflowInstantiatable {

	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IXMLInstanceable#fromXMLAttributes(org.xml.sax.Attributes)
	 */

	private String label = "";
	public Label() {

	}
	public Label(String label, String type, String name, String value) {
		this.label = label;
		
	}
	public static void main(String[] args) {
	}
	/**
	 * @return
	 */

	/**
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param string
	 */
	public void setLabel(String string) {
		label = string;
	}
	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IXMLInstanceable#fromXMLAttributes(org.xml.sax.Attributes)
	 */
	public void fromXMLAttributes(Workflow workflowContext,Attributes atts) {
		// TODO Auto-generated method stub

		String label = atts.getValue("label");
		this.label = label;

	}

}
