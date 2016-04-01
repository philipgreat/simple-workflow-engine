package com.terapico.util.mail;


public interface HTMLContentHandler {
	public  void startDocument(String referURL,String rawText);
	public  void endDocument();
	public  void OnTag(String rawText,HTMLTagContent content);	

}
