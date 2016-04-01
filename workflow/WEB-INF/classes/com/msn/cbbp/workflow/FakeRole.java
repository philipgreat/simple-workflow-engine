/*
 * Created on 2005-3-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terapico.util.Logger;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/* atm.pinpad.password = 12333333333333333333333 
atm.jnl = you get $1,000.00 RMS 
biztype = withdraw 
atm.boh = 1000 
atm.pinpad.count = 1000 
vendor = terapico 
workflowVendor = terapico 
atm.cardreader = 
atm.cardreader.1 = @control: pull 

*/ 
public class FakeRole implements WorkflowPort {
	static Logger log = new Logger(Mailer.class);
	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.WorkflowPort#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.msn.cbbp.workflow.WorkflowSession)
	 */
	public String service(
		HttpServletRequest request,
		HttpServletResponse response,
		WorkflowSession workflowsession)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		String info=workflowsession.getVariant("atm.pinpad.password");
		log.log(info);
		Calculator.inc(workflowsession, "atm_pinpad_count");
		return "1";
	}

	public static void main(String[] args) {
	}
}
