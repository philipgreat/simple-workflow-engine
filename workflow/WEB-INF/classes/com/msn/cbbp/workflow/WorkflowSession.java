/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.terapico.util.Logger;
/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WorkflowSession {
	static Logger log = new Logger(WorkflowSession.class);	
	private Activity currentActivity = null;
	private Workflow currentWorkflow = null;
	private Map userParameter = null;
	private boolean isNeedShowWaiting = false;
	private UserVariantList userVariantList = null;
	private String registeredRoles = "host,mailer,passwordchecker";

	/**
	 * 
	 */
	public WorkflowSession() {
		//super();
		//currentWorkflow=SampleWorkflows.getDefaultWorkflow();
		//getXMLWorkflow
		currentWorkflow = SampleWorkflows.getXMLWorkflow();
		userVariantList = new UserVariantList();


		//TODO change this when released
		log.log("start");
		//System.out.println("currentwf: "+currentWorkflow);

		userVariantList.putVariant(currentWorkflow.getControlVariant());
		try {
			currentActivity = currentWorkflow.getInitActivity();
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//testEvent	
		//currentActivity=currentWorkflow
		// TODO Auto-generated constructor stub
	}
	private boolean isRegisteredRole(String roleName) {

		return registeredRoles.indexOf(roleName) >= 0;
	}
	public void testEvent(String eventKey) {
		//reader xml file and initiate this workflow instance
		//activityMapping.put(activity.getName(),activity);
		try {
			currentWorkflow.reload();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if (eventKey == null) {
			return;
		}

		if (eventKey.equals("0")) {
			return;
		}

		//System.out.println("currentac: " + currentActivity);
		//System.out.println("test event: "+eventKey);
		Action action = null;
		try {
			action = currentActivity.lookup(eventKey);
			
			userVariantList.putVariant(action.getControlVariant());
			

		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			log.log(e);
			return;
		}

		//System.out.println("action is :"+action);;
		//System.out.println("action is :"+action);
		//System.out.println("============================================");

		try {
			currentActivity = currentWorkflow.lookup(action.getActionPage());
			userVariantList.putVariant(currentActivity.getControlVariant());

		} catch (ElementNotFoundException e1) {
			log.log(e1);
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}

	}
	public static void main(String[] args) {
		WorkflowSession session = new WorkflowSession();

		String cmdSeq = "1,2,1,1";
		String[] cmdArray = cmdSeq.split(",");

		for (int i = 0; i < cmdArray.length; i++) {

			session.testEvent(cmdArray[i]);

		}
		System.out.println("123"+session.parseMessage("123+12321"));

	}
	/**
	 * @return
	 */
	public Activity getCurrentActivity() {

		return currentActivity;
	}

	/**
	 * @return
	 */
	public Workflow getCurrentWorkflow() {
		return currentWorkflow;
	}

	/**
	 * @param activity
	 */
	public void setCurrentActivity(Activity activity) {
		currentActivity = activity;
	}

	/**
	 * @param workflow
	 */
	public void setCurrentWorkflow(Workflow workflow) {
		currentWorkflow = workflow;
	}

	/**
	 * @return
	 */
	public boolean isNeedShowWaiting() {
		boolean isNeed = this.isNeedShowWaiting;

		//if(this.isNeedShowWaiting==true)this.isNeedShowWaiting=false;
		String role = currentActivity.getRoleName();

		if (isRegisteredRole(role)) {
			if (isNeed == false) {
				isNeed = true;
			} else {
				isNeed = false;
			}
			this.isNeedShowWaiting = isNeed;

		}
		return this.isNeedShowWaiting;
		//return isNeed;
	}

	public boolean storeVariant(HttpServletRequest request) {
		boolean isNeed = this.isNeedShowWaiting;
		//variantList.add(input);
		//variantList.addAll(currentActivity.getInputList());
		//
		
		
		List inputs = currentActivity.getInputList();
		

		for (int i = 0; i < inputs.size(); i++) {
			Input input = (Input) inputs.get(i);
			String name = input.getName();
			String value = request.getParameter(name);
			//input.setValue(value);
			userVariantList.putVariant(name, value);

			//variantList.add(input);

		}

		return isNeed;
		//return isNeed;
	}

	/**
	 * @return
	 */
	public List getVariantList() {
		return userVariantList.getVariantList();
	}

	public void clearVariant() {
		userVariantList.getVariantList().clear();
	}

	/**
	 * @param key
	 * @return
	 */
	public String getVariant(String key) {
		return userVariantList.getVariant(key);
	}
	public String parseMessage(String value) {
		// TODO Auto-generated method stub
		String ret = "";
		String[] values = value.split("\\+");
		String msg = "";
		for (int i = 0; i < values.length; i++) {
			msg = values[i];
			if (msg.indexOf('$') == 0) {
				String key = msg.substring(1);
				msg = this.getVariant(key);
				
			}else if(msg.indexOf('#') == 0){
				

				String key = msg.substring(1);
				//formatter
				String val=this.getVariant(key);
				long lval=0;
				NumberFormat formatter = new DecimalFormat("#,###,###.00");
				log.log(val);
				//log.log("222".matches("^\\d{1,10}$")+"he");
				if(val.matches("^\\d{1,100}$")){
					lval=Long.valueOf(val).longValue();
					msg =formatter.format(lval) ;
				}else{
					msg =formatter.format(lval) ;
			
				}
				
				
			}
			ret+=msg;
		}

		return ret;
	}
	/**
	 * @param string
	 * @param string2
	 */
	public void addVariant(String name, String value) {
		// TODO Auto-generated method stub
		userVariantList.putVariant(name, value);
	}
	

}
