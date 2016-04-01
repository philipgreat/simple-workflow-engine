package com.terapico.util.mail;

import java.util.Enumeration;
import java.util.Properties;

public class HTMLTagContent {

	/**
	 * @param args
	 */
	private String rawText;
	private String name;
	private Properties properties;
	private String text;
	//the content should be 
	// <img src='here' title='shit'>
	public HTMLTagContent(){
		properties=new Properties();
		
	}
	public boolean isImage(){
		return this.name.equals("img");
		
	}
	public String toString()
	{
		return this.name+""+this.properties.toString()+"";
		
	}
	public String getProperty(String key)
	{
		return (String)this.properties.getProperty(key);
		
	}
	public String toHTML()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("<"+this.name);
		//Iterator it=this.properties.
		//equals)
	    for (Enumeration e= this.properties.propertyNames();e.hasMoreElements();)
		{
	    	 //<String>
	    	String key=(String) e.nextElement();
	    	sb.append(" "+key+"='"+this.properties.getProperty(key)+"'");
	    	
	    	
			
			
		}
	    sb.append('>');	    
		
		return sb.toString();
		
	}
	public void setProperty(String name,String value){		
		properties.put(name.toLowerCase(), value);
	}
	
	/**
	 * @return Returns the name.
	 */
	public void clear() {
		this.name=null;
		this.text=null;
		this.properties.clear();
		//return name;
	}

	public String getName() {
		return name;
	}
	/**
	 * @return Returns the properties.
	 */
	public Properties getProperties() {
		return properties;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name.toLowerCase();
	}
	
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return Returns the rawText.
	 */
	public String getRawText() {
		return rawText;
	}
	/**
	 * @param rawText The rawText to set.
	 */
	public void setRawText(String rawText) {
		this.rawText = rawText;
	}
}
