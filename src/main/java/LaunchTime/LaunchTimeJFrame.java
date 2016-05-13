package LaunchTime;

import Executor.CommandExecutor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

public class LaunchTimeJFrame extends JFrame
		implements ActionListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	public LaunchTimeJFrame() throws IOException{
		panel.setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final JTextArea resultTextArea = createResultTextArea(panel);

		final JTextField packageTextField = createPackageTextField(panel);

		final JTextField activityTextField = createActivityTextField(panel);

		final JTextField waitingTimeTextField = createWaitingTimeTextField(panel);

		final JButton startButton = createStartButton();

		startButton.addActionListener(e -> {
			final String packageName = packageTextField.getText();
            final String activityName = activityTextField.getText();
			final long waitingTimeInMillis = 1000 * Long.parseLong(waitingTimeTextField.getText());

            startButton.setBackground(Color.LIGHT_GRAY);
            try {
                resultTextArea.setFont(new Font("Courier", Font.PLAIN, 12));
                resultTextArea.setText("waiting...");

                CommandExecutor executor = new CommandExecutor();
                executor.exec("adb shell am force-stop " + packageName);
                executor.exec("adb shell logcat -c ");
				executor.exec("adb shell am start -W -n " + packageName  + "/" + activityName, waitingTimeInMillis);

                LaunchLogParser parser = new AliPlanetLaunchLogParser(executor.exec("adb shell logcat -v time -ds INIT_SCHEDULER | grep com.sds.android.ttpod-- "));
                resultTextArea.setText(parser.summary());
            } catch (IOException | InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

		setTitle("Android启动时间V1.0-----testly");
		setBounds(0, 150, 632, 758);
		add(panel);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}});
	}

	private JButton createStartButton() {
		final JButton button1 = new JButton("开始测试");
		button1.setBounds(390, 610, 100, 25);
		panel.add(button1);
		return button1;
	}

	private JTextField createWaitingTimeTextField(JPanel panel) {
		final JLabel label1 = new JLabel("等待时间(s):");
		panel.add(label1);
		label1.setBounds(15, 690, 95, 25);

		final JTextField JText1 = new JTextField("8");
		panel.add(JText1);
		JText1.setBounds(130, 690, 30, 30);
		return JText1;
	}

	private JTextField createActivityTextField(JPanel panel) {
		final JLabel label1 = new JLabel("启动Activity名:");
		panel.add(label1);
		label1.setBounds(15, 650, 95, 25);

		final JTextField JText1 = new JTextField("com.ali.music.entertainment.presentation.view.splash.SplashActivity");
		panel.add(JText1);
		JText1.setBounds(130, 650, 450, 30);
		return JText1;
	}

	private JTextField createPackageTextField(JPanel panel) {
		final JLabel label = new JLabel("包名:");
		panel.add(label);
		label.setBounds(70, 610, 45, 25);

		final JTextField JText = new JTextField("com.sds.android.ttpod");
		panel.add(JText);
		JText.setBounds(130, 610, 180, 30);
		return JText;
	}

	private JTextArea createResultTextArea(JPanel panel) {
		final JTextArea textArea = new JTextArea ();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 10, 600, 600);
		add(textArea);
		panel.add(textArea);
		return textArea;
	}

	public void actionPerformed(ActionEvent e){
	}

	public void exit() {
		setVisible(false);
	}

	public static void main(String []args) throws Exception{
		LaunchTimeJFrame m=new LaunchTimeJFrame();
		m.setVisible(true);
	}
}
