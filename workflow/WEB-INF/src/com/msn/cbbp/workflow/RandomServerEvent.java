/*
 * Created on 2005/3/10
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
public class RandomServerEvent {

	public static void main(String[] args) {
		Thread t=Thread.currentThread();

		
	}
	public static String getRandomEvent(int maxvalue){
		int rand=(int)(Math.random()*1000);
		
		int i=1+(rand%maxvalue);
		
		return new Integer(i).toString();
		
	}
}
