package Executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoran on 5/5/16.
 */
public class CommandExecutor {
    private Runtime runtime = Runtime.getRuntime();

    public String exec(String command) throws IOException, InterruptedException {
        return getExecutionOutput(doExec(command, 0l));
    }

    public String exec(String command, Long waitInMillis) throws IOException, InterruptedException {
        return getExecutionOutput(doExec(command, waitInMillis));
    }

    private Process doExec(String command, Long waitInMillis) throws IOException, InterruptedException {
        Process process = runtime.exec(command);
        System.out.println(command + ": wait " + waitInMillis + " ms");

        sleep(waitInMillis);
        final int exitValue = process.waitFor();
        System.out.println(command + ": return " + exitValue + "\n");
        if (exitValue != 0) {
            System.err.println("exit with error: " + exitValue);
        }
        return process;
    }

    void sleep(Long waitInMillis) throws InterruptedException {
        Thread.sleep(waitInMillis);
    }

    private String getExecutionOutput(Process process) throws IOException {
        List<String> lines = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        process.destroy();
        return String.join("\n", lines);
    }
}
