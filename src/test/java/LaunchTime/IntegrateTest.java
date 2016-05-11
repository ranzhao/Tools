package LaunchTime;

import Executor.CommandExecutor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoran on 5/9/16.
 */
public class IntegrateTest {
    @Test
    public void launchAliPlanet() throws Exception {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.exec("adb shell am force-stop com.sds.android.ttpod");
        commandExecutor.exec("adb shell am start -W -n com.sds.android.ttpod/com.ali.music.entertainment.presentation.view.splash.SplashActivity", 8000l);

        String log = commandExecutor.exec("adb shell logcat -ds INIT_SCHEDULER | grep com.sds.android.ttpod-- ");
        AliPlanetLaunchLogParser parser = new AliPlanetLaunchLogParser(log);
        assertEquals("", parser.summary());
    }
}
