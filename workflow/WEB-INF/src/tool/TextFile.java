package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextFile  {
	public String fileName;
	public TextFile(String pathname) {
		Format formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		this.fileName = pathname+"/"+formatter.format(new java.util.Date())+".txt";
		// TODO Auto-generated constructor stub
	}
	public TextFile() {
		
		// TODO Auto-generated constructor stub
	}
	public void saveText(String text) throws IOException
	{
			BufferedWriter out;		
			out = new BufferedWriter(new FileWriter(fileName));
	        out.write(text);
	        out.close();
		
		
	}
	public static void main(String[] args)
	{
		
		TextFile file=new TextFile("f:/lover.txt");
		try {
			file.saveText("love is ÷–Œƒ≤‚ ‘blue");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Date());
		
	}



	
}
