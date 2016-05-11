package LaunchTime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoran on 5/5/16.
 */
public class AliPlanetLaunchLogParserTest {
    @Test
    public void parse_1_serial_job() {
        String log = "com.sds.android.ttpod:push---start name = Log  -t: 98  -d: 81 \n"
                + "com.sds.android.ttpod:push---end name = Log, time cost(ms): 8  -t: 103 -d: 5";

        assertEquals(8, new AliPlanetLaunchLogParser(log).jobTimeCost("Log"));
    }

    @Test
    public void parse_2_serial_jobs() throws Exception {
        String log = "com.sds.android.ttpod:push---start name = Log  -t: 98  -d: 81 \n"
                + "com.sds.android.ttpod:push---end name = Log, time cost(ms): 8  -t: 103 -d: 5 \n"
                + "com.sds.android.ttpod:support---start name = MusicBundle       -t: 941 -d: 0 \n"
                + "com.sds.android.ttpod:support---end name = MusicBundle, time cost(ms): 75      -t: 1016        -d: 75";

        AliPlanetLaunchLogParser parser = new AliPlanetLaunchLogParser(log);
        assertEquals(8, parser.jobTimeCost("Log"));
        assertEquals(75, parser.jobTimeCost("MusicBundle"));
    }

    @Test
    public void parse_1_concurrent_job() throws Exception {
        String log = "com.sds.android.ttpod:support---Start job list: 1 \n"
                + "com.sds.android.ttpod:support---Job list 1, start blocking job :support base init \n"
                + "com.sds.android.ttpod:support---Job list 1, finish blocking job :support base init, time cost(ms): 64";

        assertEquals(64, new AliPlanetLaunchLogParser(log).jobTimeCost("support base init"));
    }

    @Test
    public void parse_hybrid_jobs() throws Exception {
        String log = "com.sds.android.ttpod:push---start name = Log  -t: 98  -d: 81 \n"
                + "com.sds.android.ttpod:push---end name = Log, time cost(ms): 8  -t: 103 -d: 5 + \n"
                + "com.sds.android.ttpod:support---Start job list: 1 \n"
                + "com.sds.android.ttpod:support---Job list 1, start blocking job :support base init \n"
                + "com.sds.android.ttpod:support---Job list 1, finish blocking job :support base init, time cost(ms): 64";

        AliPlanetLaunchLogParser parser = new AliPlanetLaunchLogParser(log);
        assertEquals(8, parser.jobTimeCost("Log"));
        assertEquals(64, parser.jobTimeCost("support base init"));
    }

    @Test
    public void parse_total_time_with_main_activity() throws Exception {
        String log = "com.sds.android.ttpod---MainActivity onCreate start    -t: 528520        -d: 26\n" +
                "com.sds.android.ttpod---MainActivity onCreate end      -t: 528520        -d: 115\n" +
                "com.sds.android.ttpod---MainActivity onWindowFocusChanged start hasFocus = true        -t: 528520        -d: 558\n" +
                "com.sds.android.ttpod---MainActivity onWindowFocusChanged end hasFocus = true  -t: 528520        -d: 0";

        AliPlanetLaunchLogParser parser = new AliPlanetLaunchLogParser(log);
        assertEquals(528520, parser.totalTimeCost());
    }

    @Test
    public void parse_total_time_with_welcome_activity() throws Exception {
        String log = "com.sds.android.ttpod---WelcomeActivity onCreate start    -t: 4282        -d: 26\n" +
                "com.sds.android.ttpod---WelcomeActivity onCreate end      -t: 4397        -d: 115\n" +
                "com.sds.android.ttpod---WelcomeActivity onWindowFocusChanged start hasFocus = true        -t: 4955        -d: 558\n" +
                "com.sds.android.ttpod---WelcomeActivity onWindowFocusChanged end hasFocus = true       -t: 4958       -d: 1358";

        AliPlanetLaunchLogParser parser = new AliPlanetLaunchLogParser(log);
        assertEquals(4958, parser.totalTimeCost());
    }

    @Test
    public void parse_total_time_with_both_activites() throws Exception {
        String log = "com.sds.android.ttpod---WelcomeActivity onCreate start    -t: 4282        -d: 26\n" +
                "com.sds.android.ttpod---WelcomeActivity onCreate end      -t: 4397        -d: 115\n" +
                "com.sds.android.ttpod---WelcomeActivity onWindowFocusChanged start hasFocus = true        -t: 4955        -d: 558\n" +
                "com.sds.android.ttpod---WelcomeActivity onWindowFocusChanged end hasFocus = true       -t: 4958       -d: 1358" +
                "com.sds.android.ttpod---MainActivity onWindowFocusChanged start hasFocus = true       -t: 528520      -d: 940\n" +
                "com.sds.android.ttpod---MainActivity onWindowFocusChanged end hasFocus = true -t: 528520      -d: 0";

        AliPlanetLaunchLogParser parser = new AliPlanetLaunchLogParser(log);
        assertEquals(4958, parser.totalTimeCost());
    }
}
