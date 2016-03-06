package StartTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class GetPackageStartTime {
    public static String GetstartTime(String packageName, String activityname)
        throws IOException, InterruptedException {
        String TalTime = GetPackageStartTime.Gettime(packageName, activityname);
        Thread.sleep(3000);
        killpackage(packageName);
        System.out.print(TalTime);
        return TalTime;
    }

    public static String Gettime(String packageName, String activityname)
        throws IOException, InterruptedException {
        String str3 = null;
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("adb shell am start -W -n " + packageName +
                "/" + activityname);
        Thread.sleep(5000);
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                        proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while ((line = in.readLine()) != null) {
                stringBuffer.append(line + " ");
            }
            String str1 = stringBuffer.toString();
            String str2 = str1.substring(str1.indexOf("TotalTime"),
                    str1.indexOf("TotalTime") + 16);
            str3 = str2;
        } catch (InterruptedException e) {
            System.err.println(e);
        } finally {
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return str3;
    }

    public static void killpackage(String packageName)
        throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("adb shell  am force-stop " + packageName);
        Thread.sleep(5000);

        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                        proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line + " ");
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        } finally {
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
    }
}
