package com.terapico.util;

/**
 * @author qnadmin
 *
 */
public class StreamParser {
	private StringBuffer buffer;
	private CommonTag tag=null;
	private boolean isIn=false;
	public static void main(String []agrs)
	{
		StreamParser parser=new StreamParser();
		parser.parse("adf <a href='sdfasdf' >title</a> <img src='123'>that");
		
	}

	private void parse(String string) {
		// TODO Auto-generated method stub
		buffer=new StringBuffer();
		for(int i=0;i<string.length();i++){
			char c=string.charAt(i);
			this.onChar(c);			
		}
	}
	static final String[] tokens={"<a","<img"};
	private String findToken(String input)
	{
		String ret = null;
		int i=0;
		for(i=0;i<tokens.length;i++){			
			if(input.endsWith(tokens[i])){
				ret = tokens[i];
				break;
			}
		}
		return ret;
	}


	private void onChar(char c) {
		
		String current=buffer.toString();
		String currentToken="";	
		//System.out.print(c);
		buffer.append(c);
		if(isIn&&tag!=null){
			if(tag.onChar(c)){
				isIn=false;
			}
		}
		if((currentToken=findToken(current))!=null&&isIn==false){			
			//System.out.println(current);
			if(currentToken.equals("<a"))
			{
				tag=new CommonTag();
				isIn=true;
			}
			buffer.delete(0, buffer.length());
		}
		
	}

}
