/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SampleWorkflows {

	public static void main(String[] args) {
	}

	
	public static Workflow getXMLWorkflow(){
		Workflow workflow=new Workflow();
		
		//workflow.fromXML()


		try {
			workflow.fromXML("D:/resin-2.1.16/webapps/workflow/atm-workflow.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return workflow;
		
	}
	public static Workflow getXMLMailerWorkflow(){
		Workflow workflow=new Workflow();
		try {
			workflow.fromXML("D:/resin-2.1.16/webapps/workflow/mail-workflow.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;
		
	}
	
	
	
	public static Workflow getEATMWorkflow(){
		Workflow workflow=new Workflow();
		try {
			workflow.fromXML("D:/resin-ee-2.1.14/webapps/workflow/eatm-workflow.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;
		
	}
}
