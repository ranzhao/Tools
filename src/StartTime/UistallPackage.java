package StartTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UistallPackage {


	public static void main(String []args) throws IOException, InterruptedException
	{

			 installPackage("taobao");

		}

	public static void UistallPackageMe(String packageName) throws IOException, InterruptedException  {
		  
	        String unstallapk="adb uninstall "+packageName;
		    
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec(unstallapk);
		    //Thread.sleep(2000); 
		
		   // Thread.sleep(10000);
		    try {
                  
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());    
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));  
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) 
		        {
		            stringBuffer.append(line+" "); 
		        }
	        
		        String str1=stringBuffer.toString();
		        
		        
		        if(str1.contains("Success  "))
		        {
		       	 System.out.print("卸载成功！");
		        }
		        else
		        {
		       	 System.out.print("卸载失败！请检查");
		        }



		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }

	  }
	
	

	public static void installPackage(String apk) throws IOException, InterruptedException  {
		  
	     
		    String installapk="adb install \"d:/apk/"+apk+".apk\"";
	       
		    
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec(installapk);
	 
		
		   // Thread.sleep(10000);
		    try {
                  
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());    
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));  
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) 
		        {
		            stringBuffer.append(line+" "); 
		        }
	        
		        String str1=stringBuffer.toString();
		        
		        
		        if(str1.contains("Success  "))
		        {
		       	 System.out.print("安装成功！");
		        }
		        else
		        {
		       	 System.out.print("安装失败！请检查设备是否连接或者其他异常");
		        }

		
		 
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }

	}
	
	
	

	
	
	

	public static String CheckPackage(String packageName) throws IOException, InterruptedException  {
		  
		    String str1=null;
	
	       
		    
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys meminfo"+packageName);
	 
		
		   // Thread.sleep(10000);
		    try {
                  
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());    
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));  
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) 
		        {
		            stringBuffer.append(line+" "); 
		  	   
		        }
		        //System.out.print(stringBuffer.toString());
		        str1=stringBuffer.toString();
		    
  

		    } 
		   
		    
		    catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
        
		    return str1;
		    
	}
	
	
	
	
	
	
	  }
  

