/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import org.xml.sax.Attributes;

/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * <input type='text' name='atm.printer' value='*****'>
 * 使用JAVA的類來代替該元素
 */
public class Input implements IXMLWorkflowInstantiatable {
	private String type = "";
	private String name = "";
	private String value = "";
	private String label = "";
	public Input() {

	}
	public Input(String label, String type, String name, String value) {
		this.label = label;
		this.type = type;
		this.name = name;
		this.value = value;
	}
	public static void main(String[] args) {
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

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
		String type = atts.getValue("type");
		String name = atts.getValue("name");
		String value = atts.getValue("value");
		String label = atts.getValue("label");
		this.type = type;
		this.name = name;
		this.value = value;
		this.label = label;
	}

}
