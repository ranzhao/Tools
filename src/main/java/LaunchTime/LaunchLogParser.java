package LaunchTime;

/**
 * Created by zhaoran on 5/9/16.
 */
public interface LaunchLogParser {
    int jobTimeCost(String job);

    int totalTimeCost();

    String summary();
}
