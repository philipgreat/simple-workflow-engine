package com.terapico.util.mail;

import com.terapico.util.Logger;

public class HTMLTestHandler implements HTMLContentHandler {
	static Logger log = new Logger("testhandler");	
	public void startDocument(String referURL, String rawText) {
		// TODO Auto-generated method stub

	}

	public void endDocument() {
		// TODO Auto-generated method stub

	}

	public void onTag(String rawText, HTMLTagContent content) {
		// TODO Auto-generated method stub
		log.log(content);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
