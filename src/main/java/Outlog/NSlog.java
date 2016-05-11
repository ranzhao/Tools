package Outlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NSlog {
	
	
	public static void main(String []args) throws IOException, InterruptedException{	
	}

	public static String time() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");// 定义格式，不显示毫秒
		String time = df.format(new Date());
		return time;
	}
	public static String timenow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");// 定义格式，不显示毫秒
		String time = df.format(new Date());
		return time;
	}

	public static void writelogs(String path,String time,String text) throws IOException {	
    try{
    	if(!(new File(path).isDirectory())){
    	new File(path).mkdir();
    	}
		File file = new File(path + File.separator + time + ".txt");          
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(file,true), "UTF-8");
			writer.write(text);
			writer.write("\r\n");
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    catch(Exception FileNotFoundException) {
	  
    }
	}

}


