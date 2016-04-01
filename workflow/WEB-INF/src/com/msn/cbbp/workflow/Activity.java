/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;

import com.terapico.util.Logger;

/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


public class Activity implements IControlVariant, IXMLWorkflowInstantiatable 
{
	static Logger log = new Logger(WorkflowSession.class);	
	private Map actionMapping = null;
	private String name = "";
	private String label = "";
	private String controlVariant = "$vendor=terapico";
	private String roleName = "default";
	private String page = "default";
	private Activity parent=null;
	private Workflow workflowContext=null;
	private List actionList = null;
	private List inputList = null;
	public Activity() {
		actionMapping = new HashMap(10);
		actionList = new ArrayList(10);
		inputList = new ArrayList(10);

	}

	public void addAction(Action action) {
		actionMapping.put(action.getEvent(), action);
		actionList.add(action);
	}
	public void addInput(Input input) {
		inputList.add(input);
	}

	public synchronized Action lookup(String actionName)
		throws ElementNotFoundException {
		//if equals null so , I will thow a NotFoundException
		Action action = (Action) actionMapping.get(actionName);
		if (null == action && this.parent!= null ) {
			
			action = (Action)this.parent.getActionMapping().get(actionName);
			if(action == null){
				throw new ElementNotFoundException("Action", actionName);
			}
		}
		
		if(null == action){			
			throw new ElementNotFoundException("Action-lookup", actionName);			
		}
		
		
		return action;
	}
	public void clear() {
		//if equals null so , I will thow a NotFoundException
		actionMapping.clear();
	}

	public static void main(String[] args) {
	}
	/**
	 * @return
	 */
	public Map getActionMapping() {
		return actionMapping;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param map
	 */
	public void setActionMapping(Map map) {
		actionMapping = map;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
	public String toString() {
		return this.name + "-> role: " + roleName + actionList.toString();
	}

	/**
	 * @return
	 */
	public List getActionList() {
		List allList=new ArrayList(10);
		
		allList.addAll(actionList);
		if(null!=this.parent){
			List parentList=this.parent.getActionList();
			allList.addAll(parentList);		
			
		}
		//List ret = par
		return allList;
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
	public List getInputList() {
		return inputList;
	}

	public String getControlVariant() {
		return controlVariant;
	}

	/**
	
	
	 * @param string
	 */
	public void setControlVariant(String string) {
		controlVariant = string;
	}

	/**
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}
	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.IXMLInstanceable#fromXMLAttributes(org.xml.sax.Attributes)
	 */
	public void fromXMLAttributes(Workflow workflowContext,Attributes atts) {
		// TODO Auto-generated method stub
		String name = atts.getValue("name");
		String label = atts.getValue("label");
		String role = atts.getValue("role");
		String page = atts.getValue("page");
		String parentActivity = atts.getValue("extends");
		log.log("parentActivity"+parentActivity);
		
		if(null!=parentActivity){
			try {
				this.parent = workflowContext.lookup(parentActivity);
			} catch (ElementNotFoundException e) {
				// TODO Auto-generated catch block
				
				log.log("parentActivity"+parentActivity);
				e.printStackTrace();
			}
			
			
		}
		
		
		
		//System.out.println(name+": iserver="+isServer);
		if (null == role) {
			role = "server";
		}
		this.name=name;
		this.label = label;
		this.roleName = role;
		this.page=page;

		//this.setControlVariant(role)
	}

	/**
	 * @return
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param string
	 */
	public void setPage(String string) {
		page = string;
	}

	/**
	 * @return Returns the parent.
	 */
	public Activity getParent() {
		return parent;
	}


}
