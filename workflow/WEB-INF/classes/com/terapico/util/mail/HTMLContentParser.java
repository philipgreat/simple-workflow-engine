package com.terapico.util.mail;

//import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.terapico.util.Logger;

public class HTMLContentParser {
	static Logger log = new Logger("HTMLContentParser");
	// <img src='something' >
	private HTMLContentHandler handler;

	public void setHandler(HTMLContentHandler handler) {
		this.handler = handler;
	}
	public boolean isToken(char ch){		
		return "\t\r\n ".indexOf(ch)>=0;
	}
	public boolean isNameFirstChar(char ch){		
		return "_abcdefghfijklmnopqrstuvwxyzABCDEFGHFIJKLMNOPQRSTUVWXYZ".indexOf(ch)>=0;
	}
	public boolean isAlphaNumer(char ch){		
		return "#+-*$0123456789_abcdefghfijklmnopqrstuvwxyzABCDEFGHFIJKLMNOPQRSTUVWXYZ%".indexOf(ch)>=0;
	}
	public boolean isValue(char ch){		
		return this.isAlphaNumer(ch);
	}
	public void parse(String referenceURL, String htmlText) {
		int index = 0;
		int indexStart = 0;
		int currentTagStartPos= 0;
		int currentTagEndPos= 0;
		
		int indexEnd = 0;
		String currentName = null;
		String currentValue = null;
		String currentTag = null;
		String currentRawText=null;
		HTMLTagContent currentTagContent=new  HTMLTagContent();
		char quote='\0';
		
		HTMLParseState state = HTMLParseState.INIT;
		
		System.out.println("start document");
		// handler.startDocument(referenceURL);
		boolean isNeedForward = true;
		
		handler.startDocument(referenceURL, htmlText);
		
		while (true) {
			isNeedForward = true;
			
			char currentChar = htmlText.charAt(index);

			do {
				if (HTMLParseState.INIT == state) {
					isNeedForward = false;
					state = HTMLParseState.START_DOCUMENT;
					break;
				}
				if (HTMLParseState.START_DOCUMENT == state) {
					if (currentChar == '<') {						
						isNeedForward = false;
						currentTagStartPos=index;
						state = HTMLParseState.NEXT_TAG;
					}
					break;
				}
				
				if (HTMLParseState.TAG_START == state) {
					if(currentChar=='/'||currentChar=='!'){						
						state=HTMLParseState.NEXT_TAG;
						//isNeedForward = false;
					}else if(this.isNameFirstChar(currentChar)){
						
						state = HTMLParseState.TAG_NAME_START;
						isNeedForward = false;
					}
					
					break;
				}
				if (HTMLParseState.TAG_NAME_START == state) {
					if(this.isNameFirstChar(currentChar)){
						indexStart=index;
						state=HTMLParseState.TAG_NAME_IN;						
					}
					break;

				}
				if (HTMLParseState.TAG_NAME_IN == state) {
					if(isToken(currentChar)){
						indexEnd=index;
						currentTag=htmlText.substring(indexStart,indexEnd);
						currentTagContent.setName(currentTag);
						state=HTMLParseState.TAG_NAME_END;
						isNeedForward = false;
					}
					if(currentChar=='>'){
						indexEnd=index;
						currentTag=htmlText.substring(indexStart,indexEnd);
						currentTagContent.setName(currentTag);
						isNeedForward = false;
						state=HTMLParseState.TAG_NAME_END;
						
					}
					break;
				}
				if (HTMLParseState.TAG_NAME_END == state) {
					if(isToken(currentChar)){
						state=HTMLParseState.NEXT_PROPERTY ;					
					}
					if(currentChar=='>'){						
						state = HTMLParseState.NEXT_TAG;
					}
					break;
				}
				if (HTMLParseState.NAME_START == state) {
					
					indexStart=index;
					//isNeedForward = fals
					//TODO: CHECK THIS e;
					state=HTMLParseState.NAME_IN;
					
					break;
				}
				if (HTMLParseState.NAME_IN == state) {
					if(isToken(currentChar)){
						indexEnd=index;
						currentName=htmlText.substring(indexStart, indexEnd);						
						state=HTMLParseState.NAME_END;
					}
					if(currentChar=='='){
						
						isNeedForward = false;
						indexEnd=index;
						currentName=htmlText.substring(indexStart, indexEnd);						
						state=HTMLParseState.LOOK_FOR_EQUAL;
					}
					
					break;
				}
				if (HTMLParseState.NAME_END == state) {
					if(currentChar=='='){
						//isNeedForward = false;	
						state=HTMLParseState.LOOK_FOR_EQUAL;
					}
					break;
				}
				if (HTMLParseState.LOOK_FOR_EQUAL == state) {
					//after '='
					//stop at here
					//if(currentChar)
					//三种情况， 一种是直接的值，一种有空格，另外是有‘/“
					if(currentChar=='\''||currentChar=='\"'){
						quote=currentChar;
						state=HTMLParseState.VALUE_START ;	
						//out.println("1-"+currentName+":"+ currentChar);
					}if(quote=='\0'&&this.isValue(currentChar)){
						//out.println("2-"+currentName+":"+ currentChar);
						isNeedForward = false;				
						state=HTMLParseState.VALUE_START ;	
						
					}
					//out.println("2-"+currentName+": "+ currentChar);
					break;
				}
				if (HTMLParseState.VALUE_START == state) {
					indexStart=index;
					isNeedForward = false;	
					state=HTMLParseState.VALUE_IN ;					
					break;
				}
				if (HTMLParseState.VALUE_IN == state) {
					
					
					
					if(quote=='\0'&&this.isToken(currentChar)){
						isNeedForward = false;	
						state=HTMLParseState.VALUE_END ;	
						
					}else if(quote=='\0'&&currentChar=='>'){
						state=HTMLParseState.VALUE_END ;
						isNeedForward = false;
						
					}else if(currentChar==quote){				
						state=HTMLParseState.VALUE_END ;
						isNeedForward = false;	
					}
					//out.println("--"+currentName+":"+ currentChar);

					
					break;
				}
				if (HTMLParseState.VALUE_END == state) {
					//out.println(currentName+": "+"found in HTMLParseState.VALUE_END");
					indexEnd=index;

					//TODO: REMOVE THIS AFTER RELEASE
					currentValue=htmlText.substring(indexStart, indexEnd);
					//CharSequence cs1="%0D%0A".subSequence(0, 6);
					//CharSequence cs2="".subSequence(0, 0);
					//currentValue=currentValue.replace(cs1, cs2);
					//log.log("current value: "+currentName+"="+currentValue);
					
					currentTagContent.setProperty(currentName, currentValue);					
					state=HTMLParseState.NEXT_PROPERTY;
					isNeedForward = false;
					quote='\0';
					
					break;
				}
				if (HTMLParseState.NEXT_PROPERTY == state) {
					
					if(currentChar=='>'){	
						isNeedForward = false;
						state=HTMLParseState.TAG_END;
					}else if(this.isNameFirstChar(currentChar)){
						isNeedForward = false;
						state=HTMLParseState.NAME_START; 
						
					}
					//isNeedForward = false;
					break;
				}
				if (HTMLParseState.TAG_END == state) {
					currentTagEndPos=index+1;
					if(true){
						
						currentRawText=htmlText.substring(currentTagStartPos, currentTagEndPos);
						currentTagContent.setRawText(currentRawText);
						//TODO: add more process here;
						handler.onTag(currentRawText, currentTagContent);					
					}
					currentTagContent.clear();
					state=HTMLParseState.NEXT_TAG;
					break;
				}
				if (HTMLParseState.NEXT_TAG == state) {
					if(currentChar=='<'){
						currentTagStartPos=index;
						isNeedForward = false;
						state=HTMLParseState.TAG_START;						
					}
					break;
				}
				if (HTMLParseState.END_DOCUMENT == state) {
					
					break;
				}

			} while (false);
			
			//System.out.println(state+": "+currentChar );
			if (isNeedForward)
				index++;

			if (index + 1 > htmlText.length()) {
				
				handler.endDocument();
				break;
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HTMLContentParser parse = new HTMLContentParser();
		String sample = parse.getTextFromFile("f:/suddy/sampleHTML.txt");
		if(true){
			
			parse.setHandler(new HTMLTestHandler());		
					
			parse.parse("http://localhost/java/", sample);
			
		}

	}
	public String getTextFromFile(String filePath){
		StringBuilder sb=new StringBuilder();
		try {
	        BufferedReader in = new BufferedReader(new FileReader(filePath));
	        String str;
	        while ((str = in.readLine()) != null) {
	        	sb.append(str);
	        }
	        in.close();
	    } catch (IOException e) {
	    	return null;
	    }
	    return sb.toString();
	}

}
