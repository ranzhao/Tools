package Executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by zhaoran on 5/5/16.
 */
public class CommandExecutorTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private CommandExecutor commandExecutor = new CommandExecutor();

    @Test(expected = IOException.class)
    public void execute_invalid_command_should_throw_exception() throws Exception {
        commandExecutor.exec("abc");
    }

    @Test
    public void execute_command_without_output() throws Exception {
        assertEquals("", commandExecutor.exec("sleep 0"));
    }

    @Test
    public void execute_command_with_waiting() throws Exception {
        commandExecutor = spy(commandExecutor);
        assertEquals("", commandExecutor.exec("sleep 0", 1000l));
        verify(commandExecutor).sleep(1000L);
    }

    @Test
    public void execute_command_with_output() throws Exception {
        assertEquals("hello world", commandExecutor.exec("echo hello world"));
    }

    @Test
    public void execute_async_command_with_output() throws Exception {
        commandExecutor.exec("adb shell am force-stop com.sds.android.ttpod");
        commandExecutor.exec("adb shell am start -W -n com.sds.android.ttpod/com.ali.music.entertainment.presentation.view.splash.SplashActivity", 8000l);
        assertEquals("", commandExecutor.exec("adb shell logcat -ds INIT_SCHEDULER | grep com.sds.android.ttpod-- "));
    }
}
