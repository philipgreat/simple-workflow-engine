/*
 * Created on 2005-3-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.msn.cbbp.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserVariantList {
	private Map userVariantMap = null;
	private List userVariables = null;

	/**
	 * 
	 */
	public UserVariantList() {
		userVariantMap = new HashMap(10);
		userVariables = new ArrayList(10);

	}
	public String putVariant(String name, String value) {

		userVariantMap.put(name, value);
		//update list
		userVariables.clear();
		Set varSet = userVariantMap.keySet();
		
		Object[] keys =  varSet.toArray();
		for (int i = 0; i < varSet.size(); i++) {
			String key = (String)keys[i];
			String val = (String) userVariantMap.get(key);
			UserVariant uv = new UserVariant(key, val);
			userVariables.add(uv);

		}

		return name;

	}
	public String putVariant(UserVariant userVariant) {

		putVariant(userVariant.getName(), userVariant.getValue());
		return userVariant.getName();

	}
	public String getVariant(String key){
		return (String)userVariantMap.get(key);
	}
	public String putVariant(String expr) {
		
		//set-variant=""
		//$xilai=gege;$didi=xixi
		String[] exprs=expr.split(";");
		for(int i=0;i<exprs.length;i++){
			UserVariant uv = new UserVariant(exprs[i]);
			putVariant(uv);
		}
		
		return "collection";

	}
	public List getVariantList() {

		return userVariables;
		//userVariantMap.put(name, value);
		//return name;

	}
	public static void main(String[] args) {
		UserVariantList uvs=new UserVariantList();
		uvs.putVariant("name", "suddy");
		uvs.putVariant("age", "10");
		System.out.println(uvs.getVariantList());
			
	}
}
