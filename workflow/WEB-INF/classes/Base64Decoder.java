import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Decoder {

	/**
	 * @param args
	 */
	String readFromFile(String filename) {
		StringBuffer sb = new StringBuffer(1024 * 1024 * 14);
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String str;

			while ((str = in.readLine()) != null) {
				//process(str);
				sb.append(str);
				//sb.append("\r\n");
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString().trim();

	}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Base64Decoder dec=new Base64Decoder();
		
		String s=dec.readFromFile("d:/downloads/nk.bin.zip.base64");
		try {
			byte [] buf = new sun.misc.BASE64Decoder().decodeBuffer(s);			
	        OutputStream out = new FileOutputStream("d:/downloads/nk.bin.zip");
	        //out.wr
	        out.write(buf);
	        
	        out.close();
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
