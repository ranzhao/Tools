package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
;


public class GetTop {
	private static  double Cpu = 0;


	public static void main( String[] args ) throws IOException{
		for ( int i = 0; i < 1200; i++ ){
			System.out.println( " Cpu：" + heap( "com.sds.android.ttpod" ) );
		}
	}


	  public static double cpu(String packageName) throws IOException {
		  try{ 
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys cpuinfo  $"+packageName);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader( proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" ");       
		        }
		    String str1=stringBuffer.toString(); 
		    if(str1.contains(packageName))  {		    	
		    String str2=str1.substring(str1.indexOf(packageName),str1.indexOf(packageName)+28);
		    String str3=str2.substring(str2.indexOf(":")+1,str2.lastIndexOf("%"));
		    if(str3.contains("%")){
		    	str3=str3.replaceAll("%", "");
		    	Cpu=Double.parseDouble( str3 );
		    }else{
		    	Cpu=Double.parseDouble( str3 );
		    }
		    }} catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }}
			catch ( Exception StringIndexOutOfBoundsException ){
				System.out.print( "请检查设备是否连接" );
				return -0.1;
			}
			return Cpu;
	  }



	public static double heap( String PackageName ) throws IOException
	{
		double Heap = 0;
		try{
			Runtime runtime = Runtime.getRuntime();
			Process proc	= runtime.exec( "adb shell dumpsys meminfo " + PackageName );
			try {
				if ( proc.waitFor() != 0 ){
					System.err.println( "exit value = " + proc.exitValue() );
				}
				BufferedReader in = new BufferedReader( new InputStreamReader(proc.getInputStream() ) );
				StringBuffer	stringBuffer	= new StringBuffer();
				String		line		= null;
				while ( (line = in.readLine() ) != null ){
					stringBuffer.append( line + " " );
				}
				String str1 = stringBuffer.toString();
				if ( str1.contains( "Objects" ) ){
					String	str2	= str1.substring( str1.indexOf( "Objects" ) - 60, str1.indexOf( "Objects" ) );
					String	str3	= str2.substring( 0, 10 );
					str3	= str3.trim();
					Heap	= Double.parseDouble( str3 );
				}  else{
					Heap = 0;
				}
			} catch ( InterruptedException e ) {
				System.err.println( e );
			} finally {
				try {
					proc.destroy();
				} catch ( Exception e2 ) {
				}
			}
		}
		catch ( Exception StringIndexOutOfBoundsException ){
			System.out.print( "请检查设备是否连接" );
			return(-0.1);
		}
		return(Heap);
	}


	public static void heapTal() throws IOException
	{

	}
}


