package LaunchTime;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoran on 5/5/16.
 */
public class AliPlanetLaunchLogParser implements LaunchLogParser {
    private final Pattern SERIAL_JOB_PATTERN = Pattern.compile(".*end name = ([\\w|\\s]+), time cost\\(ms\\): (\\d+)");
    private final Pattern CONCURRENT_JOB_PATTERN = Pattern.compile(".*finish blocking job :([\\w|\\s]+), time cost\\(ms\\): (\\d+)");
    private final Pattern MAIN_ACTIVITY_PATTERN = Pattern.compile(".*MainActivity onWindowFocusChanged end hasFocus = true\\s+-t: (\\d+)");
    private final Pattern WELCOME_ACTIVITY_PATTERN = Pattern.compile(".*WelcomeActivity onWindowFocusChanged end hasFocus = true\\s+-t: (\\d+)");
    private final Map<String, Integer> jobsTimeCost = new HashMap<>();
    private int timeMainActivityAppear;
    private int timeWelcomeActivityAppear;

    public AliPlanetLaunchLogParser(String log) {
        System.out.println(log);
        Arrays.asList(log.split("\n")).stream().forEach(line -> matchJob(line));
    }

    private void matchJob(String line) {
        matchJob(line, SERIAL_JOB_PATTERN);
        matchJob(line, CONCURRENT_JOB_PATTERN);

        int time = timeActivityAppear(line, MAIN_ACTIVITY_PATTERN);
        if (time > 0) timeMainActivityAppear = time;

        time = timeActivityAppear(line, WELCOME_ACTIVITY_PATTERN);
        if (time > 0) timeWelcomeActivityAppear = time;
    }

    private void matchJob(String log, Pattern pattern) {
        Matcher matcher = pattern.matcher(log);
        if (matcher.find()) {
            jobsTimeCost.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }
    }

    private int timeActivityAppear(String log, Pattern pattern) {
        Matcher matcher = pattern.matcher(log);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    @Override
    public int jobTimeCost(String job) {
        return jobsTimeCost.get(job);
    }

    @Override
    public int totalTimeCost() {
        return timeWelcomeActivityAppear > 0 ? timeWelcomeActivityAppear : timeMainActivityAppear;
    }

    @Override
    public String summary() {
        final String delimiter = "--------------------------------------------\n";
        final StringBuilder stringBuilder = new StringBuilder(String.format("%-30s %10s\n", "job", "time cost(ms)"));
        stringBuilder.append(delimiter);

        jobsTimeCost.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .forEach(entry -> stringBuilder.append(String.format("%-30s %8d", entry.getKey(), entry.getValue())).append("\n"));

        stringBuilder.append(delimiter)
        .append(String.format("%-30s %8d\n", "total", jobsTimeCost.values().stream().mapToInt(e->e).sum()))
        .append(delimiter)
        .append(String.format("\n%-30s %8d\n", "launch time", totalTimeCost()));

        return stringBuilder.toString();
    }
}
