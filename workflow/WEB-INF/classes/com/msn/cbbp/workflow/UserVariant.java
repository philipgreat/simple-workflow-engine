/*
 * Created on 2005-3-11
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
public class UserVariant {
	private String name = "";
	private String value = "";

	public static void main(String[] args) {
		UserVariant uv=new UserVariant("$key=value");
		System.out.println(uv);;
	}

	public UserVariant(String name, String value) {

		this.name = name;
		this.value = value;

	}

	public UserVariant(String expr) {
		//$name=value
		String[] exprs = expr.split("=");
		if (exprs.length == 2) {
			this.name = exprs[0].substring(1);
			this.value = exprs[1];
		}

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
	public void setValue(String string) {
		value = string;
	}
	public String toString() {

		StringBuffer sb = new StringBuffer(120);
		sb.append("" + name + "=" + value + ";");
		return sb.toString();

	}

}
