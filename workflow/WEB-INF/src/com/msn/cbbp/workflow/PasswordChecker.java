/*
 * Created on 2005-3-13
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
public class PasswordChecker implements WorkflowPort {
	static Logger log = new Logger(Mailer.class);
	public static void main(String[] args) {
		log.log("sdf");
	}

	/* (non-Javadoc)
	 * @see com.msn.cbbp.workflow.WorkflowPort#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String service(
		HttpServletRequest request,
		HttpServletResponse response,
		WorkflowSession workflowsession)
		throws ServletException, IOException {

	
		String pass1 = workflowsession.getVariant("atm_pinpad_password_1");
		String pass2 = workflowsession.getVariant("atm_pinpad_password_2");
		
		
		workflowsession.addVariant("msg", "123");

		if (pass1 == null || pass2 == null) {
			return "nullerror";
		}
		if (pass1.equals("") || pass2.equals("")) {
			return "blankerror";
		}

		if (pass1.equals(pass2)) {
			return "ok";
		} else {
			return "no";
		}
		
	}

}
