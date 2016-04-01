package com.terapico.sockcomm;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UTest {
	
	protected static int defaultPort = 16800;
	protected static int bufferLength = 8192;
	
	
	static final	String hostIP="127.0.0.1";
	//ÐÞ¸ÄÎª
	
	public static int sendPackage(byte[] buf,String hostname,int port)
	{
		
		try {
			//String infoToSend=content;
			DatagramSocket ds = new DatagramSocket(0);
			InetAddress ia = InetAddress.getByName(hostname);
			DatagramPacket outgoing = 
				new DatagramPacket(buf, 
					buf.length, ia, port);
			ds.send(outgoing);
			
		}catch (UnknownHostException e) {
			System.err.println(e);
		}catch (SocketException e) {
			System.err.println(e);
		}  // end catch
		catch (IOException e) {
			System.err.println(e);
		}  // end catch
		return 0;
		
		
	}
	public static int sendPackage(String content){		
		sendPackage(content.getBytes(),hostIP,defaultPort);
		return 0;				
	}
	public static int netlog(byte[] buf){		
		sendPackage(buf,hostIP,defaultPort);
		return 0;				
	}
	public static int netlog(String content){		
		sendPackage(content);
		return 0;				
	}
	public static int send(String content){		
			sendPackage(content);
			return 0;				
	}
	public static int send(Object content){		
				sendPackage(content.toString());
				return 0;				
	}
	
	public static void main(String[] args) {
		
		String hostname=hostIP;
		int port=defaultPort;
		int len=8192;
		
		for(int i=0;i<10;i++)
			netlog((new Long(i)).toString());
			//ds.receive(incoming);
			//System.out.println(new String(incoming.getData(), 0, 0, incoming.getLength()));
		
		
	}  // end main
	
}
