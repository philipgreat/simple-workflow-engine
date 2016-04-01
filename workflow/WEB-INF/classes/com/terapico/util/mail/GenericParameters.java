package com.terapico.util.mail;
import java.util.HashMap;
import java.util.Map;

public class GenericParameters {

	/**
	 * @param args
	 */
	Map<String,String> map=null;	
	
	
	public GenericParameters(String context){
		
		map = new HashMap<String,String>();		
		String [] lines= context.split("\\\r\\\n", 20);
		for(int i=0;i<lines.length;i++){
			
			String line=lines[i];
			String []args =line.split("=",2);
			if(args.length==2){
				map.put(args[0],args[1]);				
			}else{
				break;			
			}
			
			
		}	
		
	}
	public String toString()
	{
		
		return this.map.toString();
	}
	public String getParameter(String name){
		return map.get(name);		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GenericParameters p=new GenericParameters("title=Google\r\nsendto=chang.luminarc@gmail.com\r\nlocation=http://www.google.com/webhp?btnG=%E6%90%9C%E7%B4%A2&hl=zh-CN&newwindow=1");
		//System.out.println(p);
	}

}
