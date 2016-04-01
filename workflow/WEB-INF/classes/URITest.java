import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URITest {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static String getDirectory(String in)
	{
		
		String [] arr = in.split("\\/");
		String ret="";
		for(int i=0;i<arr.length-1;i++){			
			ret+=arr[i]+"/";			
		}
		return ret;
		
	}
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String ret=getDirectory("/lib/security/pam_console.so");
		System.out.println(ret);
		
		
	}

}
