package Monitor;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import Outlog.NSlog;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import GetRoScoer.GetTop;
import Monkey.Monkey_Menu;
import LaunchTime.LaunchTime_Menu;
public class Menu extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
		public static String text;
		public static String time=NSlog.time();
		public static boolean log;
		public JPanel frame = new JPanel();
		public Menu() throws IOException{		
			final JButton button2 = new JButton("Monkey");		
			button2.setBounds(120, 580, 100, 35);   
		    frame.add(button2);
		    final JButton button3 = new JButton("启动耗时");		
			button3.setBounds(300, 580, 100, 35);  
		    frame.add(button3);
			frame.setLayout(null);
			final CpuChart rtcp=new CpuChart("CPU","CPU","%");  
			 rtcp.setBounds(0, 40, 700, 250); 
			frame.add(rtcp);
		    final HeapChart rtcp1=new HeapChart("Memory","Memory","kb");  
		    rtcp1.setBounds(805, 40, 700, 250); 
			frame.add(rtcp1); 
		    final FlowChart rtcp2=new FlowChart("Flow","Flow","Kb/s");  
		    rtcp2.setBounds(0, 300, 700, 250); 
			frame.add(rtcp2);		    
		    final BatteryChart rtcp3=new BatteryChart("Battery","Battery","%");  
		    rtcp3.setBounds(805, 300,700, 250);  
			frame.add(rtcp3);		    
			JLabel lable = new JLabel("包名:");
		    frame.add(lable);
		    lable.setBounds(600, 580, 45, 25);
		    final JTextField JText = new JTextField("com.sds.android.ttpod");
		    frame.add(JText);
		    JText.setBounds(680, 580, 220, 30);
			final JButton button1 = new JButton("开始测试");		
			button1.setBounds(1000, 580, 200, 35);  
		    frame.add(button1);
		    final Checkbox r1 = new Checkbox("Log", true);
		    r1.setBounds(940, 588, 105, 15); 
		    frame.add(r1);
 
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					text= JText.getText();			
			  if (r1.getState()){
				  	log=true;
					try {
						NSlog.writelogs("D:/log/Monitor_log",time,"Time       "+"                            Cpu"+"                 Memory"+"                  Flows");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					log=false;
					System.out.print("不要日志");
				}	if(text.isEmpty()){
					JOptionPane.showMessageDialog(new JFrame(), "伙计，包名不要为空好么？");
				}try{
					if(GetTop.cpu(text)==-0.1){      		
					JOptionPane.showMessageDialog(new JFrame(), "请检查设备是否连接,或者你的设备没有连接好,也可能是你的包名不正确！");
				}else{
				  button1.setBackground(Color.LIGHT_GRAY);
				  (new Thread(rtcp)).start(); 
				  (new Thread(rtcp1)).start(); 
				  (new Thread(rtcp2)).start(); 
				  (new Thread(rtcp3)).start();
				  }}
					catch (HeadlessException | IOException e1){
					e1.printStackTrace();
					}
				}   
				});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
			    	if(!(new File("D:/log/Monkey_log").isDirectory())){
			    	new File("D:/log/Monkey_log").mkdir();
			    	}try{
				        Monkey_Menu.Monkey();
						}catch (Exception e1){
						e1.printStackTrace();
					}
				}});
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
					LaunchTime_Menu m = null;
					try {
						m = new LaunchTime_Menu();
					}
			catch (IOException e1){
				 e1.printStackTrace();
			}}});					
					setTitle("testly_Vesion 1.0");
					setBounds(200, 150, 1555, 777);
					add(frame);
					setVisible(true);
					addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent e) {
					        exit();
		}});}
		public void actionPerformed(ActionEvent e){
		}
		public void exit(){
			Object[] options = { "确定", "取消"};
			JOptionPane pane2 = new JOptionPane("真想退出吗?", JOptionPane.QUESTION_MESSAGE,
		    JOptionPane.YES_NO_OPTION, null, options, options[1]);
		    JDialog dialog = pane2.createDialog(this, "警告");
		    dialog.setVisible(true);
		    Object selectedValue = pane2.getValue();
	    if (selectedValue == null || selectedValue == options[1]){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
		}
		else if (selectedValue == options[0]){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		}
		public static void main(String []args) throws Exception{
		Menu m=new Menu();
				m.setVisible(true);
		}
	
}
