package com.terapico.util.mail;


public interface HTMLContentHandler {
	public  void startDocument(String referURL,String rawText);
	public  void endDocument();
	public  void onTag(String rawText,HTMLTagContent content);	

}
