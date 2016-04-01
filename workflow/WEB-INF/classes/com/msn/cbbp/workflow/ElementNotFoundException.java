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
public class ElementNotFoundException extends Exception {
	private String elementType = "Activity";
	private String elementName = "";

	public ElementNotFoundException(String eleName, String eleType) {
		this.elementName = eleName;
		this.elementType = eleType;
	}
	public static void main(String[] args) {
	}
	public String toString() {

		StringBuffer sb = new StringBuffer(20);
		sb.append(this.getClass().getName());
		sb.append("@");
		sb.append(this.hashCode());
		sb.append("{");
		sb.append(this.elementType);
		sb.append(",");
		sb.append(this.elementName);
		sb.append("};");


		return sb.toString();

	}
}
