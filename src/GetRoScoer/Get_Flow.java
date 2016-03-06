package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Get_Flow {
	public static void main(String []args) throws IOException, InterruptedException{
		for(int i=0;i<100;i++){
		System.out.println(Flowmille("com.sds.android.ttpod"));
		}
	}
	public static double Flowmille(String PackageName) throws IOException, InterruptedException{    		
		double i1=GetWifiFlow(PackageName);
		Thread.sleep(1000);
		double j =GetWifiFlow(PackageName)-i1;
		return j ;			
	}
	  public static double GetWifiFlow(String PackageName) throws IOException {
		    double str3=0;
		    String Pid=PID(PackageName);
		try{
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell cat /proc/"+Pid+"/net/dev");
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" ");    	            
		        }
		    String str1=stringBuffer.toString();     
		    if(str1.contains("wlan0:"))
		    {  
			String str2=str1.substring(str1.indexOf("wlan0:"),str1.indexOf("wlan0:")+90);
			String str4=str2.substring(7,16);
			String str6=str2.substring(67,75);
			str4 = str4.trim();
			str6 = str6.trim();
			int b=Integer.parseInt(str4);
			int a=Integer.parseInt(str6);
	        str3=(a+b)/1024;
		    }
		    else{
		    	System.out.println("失败了。。。");
		    	str3 = 0;
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
		    catch (Exception StringIndexOutOfBoundsException){
        	}   
			return str3;
	  }
	  
	  
	  //获取PID
	  public static String PID(String PackageName) throws IOException {
		  String PID=null;		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell ps | grep "+PackageName);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" ");
	        }	  
		    String str1=stringBuffer.toString();
	
		    if(str1.contains(PackageName))
		    {
		    	String str2=str1.substring(str1.indexOf(" "+PackageName)-46,str1.indexOf(" "+PackageName));	
			    PID =str2.substring(0,7);
		    	PID = PID.trim();
		    
		    }
		    else
		    {
		    	PID = null;
		    }
	
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
			return PID;
	  }
	
	
	  }
  

