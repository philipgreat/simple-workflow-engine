/*
 * Created on 2005/3/8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author suddy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WorkflowXMLHandler extends DefaultHandler {

	private static final String WORKFLOW_TAG = "workflow";
	private static final String ACTIVITY_TAG = "activity";
	private static final String ACTIION_TAG = "action";
	private static final String INPUT_TAG = "input";

	private Activity currentActivity = null;
	private Action currentAction = null;
	
	private Input currentInput = null;
	private Workflow workflow = null;
	
	public static void main(String[] args) {
	}
	public WorkflowXMLHandler(Workflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * Receive notification of the start of an element.
	 * @param namespaceURI - The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
	 * @param localName - The local name (without prefix), or the empty string if Namespace processing is not being performed.
	 * @param qName - The qualified name (with prefix), or the empty string if qualified names are not available.
	 * @param atts - The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
	 * @throws SAXException - Any SAX exception, possibly wrapping another exception.
	 */
	public void startElement(
		String namespaceURI,
		String localName,
		String qName,
		Attributes atts)
		throws SAXException {
			

		if (WORKFLOW_TAG.equals(localName)) {

			workflow.fromXMLAttributes(workflow,atts);
		} else if (ACTIVITY_TAG.equals(localName)) {
			//activity
			//System.out.println(name+": iserverbool="+isServerActivity);
			currentActivity = new Activity();
			currentActivity.fromXMLAttributes(workflow,atts);
			workflow.addActivty(currentActivity);
			
		} else if (ACTIION_TAG.equals(localName)) {
			//workflow
			//<action event='A1' label= '100' goto='T2'/>
			currentAction= new Action();
			currentAction.fromXMLAttributes(workflow,atts);
			currentActivity.addAction(currentAction);
			
		} else if (INPUT_TAG.equals(localName)) {
			//workflow
			
			//<action event='A1' label= '100' goto='T2'/>
			currentInput = new Input();
			currentInput.fromXMLAttributes(workflow,atts);
			currentActivity.addInput(currentInput);
		}

	}

	/**
	 * Receive notification of character data inside an element.
	 * @param ch - The characters.
	 * @param start - The start position in the character array.
	 * @param length - The number of characters to use from the character array.
	 * @throws SAXException - Any SAX exception, possibly wrapping another exception.
	 */
	public void characters(char[] ch, int start, int length)
		throws SAXException {
		float floatValue = 0;

	}

	/**
	 * Receive notification of the end of an element.
	 * @param namespaceURI - The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
	 * @param localName - The local name (without prefix), or the empty string if Namespace processing is not being performed.
	 * @param qName - The qualified name (with prefix), or the empty string if qualified names are not available.
	 * @throws SAXException - Any SAX exception, possibly wrapping another exception.
	 */
	public void endElement(String namespaceURI, String localName, String qName)
		throws SAXException {

	}

}
