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
 */
public class Action implements IControlVariant,IXMLWorkflowInstantiatable {
	private String label="";
	private String actionPage="";
	private String event="";
	private String  controlVariant="$vendor=terapico";
	public Action(
	String event,
	String name,
	String page){
		this.event=event;
		this.label=name;
		this.actionPage=page;
	}
	public Action(){
	
	}
	public static void main(String[] args) {
	}
	
	/**
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return
	 */
	public String getActionPage() {
		return actionPage;
	}


	public String toString(){
		StringBuffer sb=new StringBuffer(20);
		sb.append("\r\n<br>");
		sb.append(this.getClass().getName());
		sb.append("@");
		sb.append(this.hashCode());
		sb.append("{");
		sb.append(this.event);
		sb.append(",");		
		sb.append(this.label);
		sb.append(",");		
		sb.append(this.getActionPage());
		sb.append("}");
		
		return sb.toString();
		
		
	
	}

	/**
	 * @return
	 */
	public String getEvent() {
		return event;
	}
	/**
	 * 
	 */
	public String getControlVariant() {
		// TODO Auto-generated method stub
		return controlVariant;
	}
	/**
	 * @param string
	 */
	public void setControlVariant(String string) {
		controlVariant = string;
	}
	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IXMLInstanceable#fromXMLAttributes(org.xml.sax.Attributes)
	 */
	public void fromXMLAttributes(Workflow workflowContext,Attributes atts) {
		// TODO Auto-generated method stub
		String event = atts.getValue("event");
		String label = atts.getValue("label");
		String gotoPage = atts.getValue("goto");

		String controlVariant=atts.getValue("set-variant");	
		if(null==controlVariant){
			controlVariant="$workflowVendor=terapico";
		}	
		this.event=event;
		this.label=label;
		this.actionPage=gotoPage;
		this.controlVariant=controlVariant;
		
	}

}
