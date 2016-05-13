package ApkSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

public class ApkSizeJFrame extends JFrame
		implements ActionListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
    private final String DELIMITER = "------------------------------------------------------------------------------\n";

	public ApkSizeJFrame(String apkPath) {
		panel.setLayout(null);
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		int windowWidth = 560;
		int windowHeight = 500;
		setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth, windowHeight);

		add(panel);

		setTitle("Android安装包分析V1.0-----testly");
		setVisible(true);

        final JTextArea resultTextArea = createResultTextArea(panel);
		resultTextArea.setFont(new Font("Courier", Font.PLAIN, 12));
		resultTextArea.setText("waiting...");

		try {
			resultTextArea.setText(resultText(ApkParser.parse(apkPath)));
		} catch (IOException e) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

    private String resultText(List<ApkSizeEntry> entries) {
        final String TITLE_FORMAT = "%-50s %8s %8s %8s\n";
        final String ITEM_FORMAT = "%-50s %8d %8d %8d\n";

        StringBuilder stringBuilder = new StringBuilder(DELIMITER).append(String.format(TITLE_FORMAT, "name", "dex", "res", "so"))
                .append(DELIMITER);

        entries.stream()
                .sorted((entry1, entry2) -> (int)(entry2.dexSize() - entry1.dexSize()))
                .forEach(entry -> stringBuilder.append(String.format(ITEM_FORMAT
                , entry.entryName(), entry.dexSize(), entry.resSize(), entry.soSize())));
        stringBuilder.append(DELIMITER);

        stringBuilder.append(String.format(ITEM_FORMAT, "Total:"
                , entries.stream().mapToLong(ApkSizeEntry::dexSize).sum()
                , entries.stream().mapToLong(ApkSizeEntry::resSize).sum()
                , entries.stream().mapToLong(ApkSizeEntry::soSize).sum()))
				.append(DELIMITER);

        return stringBuilder.toString();
    }

    private JTextArea createResultTextArea(JPanel panel) {
		final JTextArea textArea = new JTextArea ();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 10, 600, 500);
		add(textArea);
		panel.add(textArea);
		return textArea;
	}

	public void actionPerformed(ActionEvent e){
	}

	public static void main(String []args) throws Exception{
		ApkSizeJFrame m=new ApkSizeJFrame("/Users/zhaoran/Downloads/aliplanet_9.0.0.apk");
		m.setVisible(true);
	}
}
