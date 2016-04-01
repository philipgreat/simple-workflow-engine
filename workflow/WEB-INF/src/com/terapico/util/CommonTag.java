package com.terapico.util;

public class CommonTag {
	private String 	text;
	private StringBuffer buffer;
	private static final String end="</a>";
	private static final char textToken='>';
	private static final String[] propTokens={"src="," href="," alt="};
	
	/**
	 * @param args
	 */
	public CommonTag()
	{
		buffer = new StringBuffer();
	}
	static private String findPropToken(String input)
	{
		String ret = null;
		for(int i=0;i<propTokens.length;i++){			
			if(input.endsWith(propTokens[i])){
				ret = propTokens[i];
				break;
			}
		}
		return ret;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean onChar(char c) {
		// TODO Auto-generated method stub
		boolean ret = false;
		//System.out.print(c);
		buffer.append(c);
		

		if(buffer.toString().endsWith(end))
		{
			ret=true;
			this.text=buffer.substring(0,buffer.length()-end.length());
			System.out.println(text);
		}
		if(c==textToken)
		{
			buffer.delete(0,buffer.length());
			
		}
		
		return ret;
	}

	public void onStart() {
		// TODO Auto-generated method stub
		
	}
	public void onEnd() {
		// TODO Auto-generated method stub
		
	}

}
