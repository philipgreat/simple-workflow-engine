/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.terapico.util.Logger;
/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Workflow implements IControlVariant,IXMLWorkflowInstantiatable{
	private String initActivity = "T1";
	private String uuid = "";
	private String name = "";
	private long lastUpdateTime=0;
	private String  controlVariant="$vendor=terapico";
	private String xmlFileName="";
	private Map activityMapping = null;
	//public 
	static Logger log = new Logger(Workflow.class);
	public Workflow() {

		activityMapping = new HashMap();

	}
	
	


	public String getInitActivityName() {

		return this.initActivity;
	}
	public void reload() throws ParserConfigurationException, SAXException, IOException{
		File file=new File(xmlFileName);
		long lm=file.lastModified();
		if(lm>lastUpdateTime){
			log.log(lastUpdateTime+"/"+lm);
			this.fromXML(xmlFileName);
			lastUpdateTime=lm;
		}
		
	
	}
	public void fromXML(String filename) 
		throws ParserConfigurationException, 
		SAXException, 
		IOException {
		//lastUpdateTime=0;
		File file=new File(xmlFileName);
		lastUpdateTime=file.lastModified();
		xmlFileName=filename;
				
		//reader xml file and initiate this workflow instance
		SAXParser saxParser;
		WorkflowXMLHandler wfh=new  WorkflowXMLHandler(this);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// Specifies that the parser produced by this code will provide support for XML namespaces.
		factory.setNamespaceAware(true);
		// Specifies that the parser produced by this code will validate documents as they are parsed.
		factory.setValidating(true);
		// Creates a new instance of a SAXParser using the currently configured factory parameters.
		
		saxParser = factory.newSAXParser();
		saxParser.parse(filename,wfh);
		wfh=null;
		factory=null;
		
	}
	public void addActivty(Activity activity) {
		//reader xml file and initiate this workflow instance
		activityMapping.put(activity.getName(), activity);

	}
	
	public void testValid() {
		//reader xml file and initiate this workflow instance
		//activityMapping.put(activity.getName(), activity);

	}
	public synchronized Activity lookup(String name) throws ElementNotFoundException {
		Activity activity = (Activity) activityMapping.get(name);
		if (null == activity) {

			throw new ElementNotFoundException("Activity", name);

		}
		return (Activity) activityMapping.get(name);

	}

	public static void main(String[] args) {
	}
	/**
	 * @return
	 */
	public Activity getInitActivity() throws ElementNotFoundException {
		return this.lookup(initActivity);
	}

	/**
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param string
	 */
	public void setUuid(String string) {
		uuid = string;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setInitActivity(String string) {
		initActivity = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IControlVariant#setControlVariant(java.lang.String)
	 */
	public void setControlVariant(String value) {
		// TODO Auto-generated method stub
		this.controlVariant=value;
	}

	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IControlVariant#getControlVariant()
	 */
	public String getControlVariant() {
		return this.controlVariant;
	}

	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IXMLInstanceable#fromXMLAttributes(org.xml.sax.Attributes)
	 */
	public void fromXMLAttributes(Workflow workflowContext,Attributes atts) {
		// TODO Auto-generated method stub
		String initActivity = atts.getValue("start-activity");
		String uuid = atts.getValue("uuid");
		String name = atts.getValue("name");
		String controlVariant=atts.getValue("set-variant");	
		if(null==controlVariant){
			controlVariant="$workflowVendor=terapico";
		}	
		
		//currentWorkflow.
		this.setUuid(uuid);
		this.setInitActivity(initActivity);
		this.setName(name);
		this.setControlVariant(controlVariant);
	}

}
