package Monkey;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Monkey_Menu extends JFrame
	implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static String text;
		public JPanel frame1 = new JPanel();
       
		public Monkey_Menu() throws IOException{
			
		
			frame1.setLayout(null);
			

		    JLabel lable0 = new JLabel("设备名称:");
		    frame1.add(lable0);
		    lable0.setBounds(30, 30, 75, 25);
		    
		    
		    final JTextField JText0 = new JTextField();
		    frame1.add(JText0);
		    JText0.setBounds(90, 30, 180, 30);

		    JLabel lable = new JLabel("包名:");
		    frame1.add(lable);
		    lable.setBounds(300, 30, 45, 25);
		    
		    
		    final JTextField JText = new JTextField("com.sds.android.ttpod");
		    frame1.add(JText);
		    JText.setBounds(360, 30, 180, 30);

			 JLabel lable1 = new JLabel("事件数量:");
			 frame1.add(lable1);
			 lable1.setBounds(30, 80, 75, 25);
			    
		     final JTextField JText1 = new JTextField("500");
			 frame1.add(JText1);
			 JText1.setBounds(90, 80, 180, 30);
			 

			 JLabel lable2 = new JLabel("种子数量:");
			 frame1.add(lable2);
			 lable2.setBounds(300, 80,75, 25);
			    
		     final JTextField JText2 = new JTextField("500");
			 frame1.add(JText2);
			 JText2.setBounds(360, 80, 180, 30);
			 
			 
			 
			 JLabel lable6 = new JLabel("随机事件：");
			 frame1.add(lable6);
			 lable6.setBounds(30,150, 75, 25); 
		     final JTextField JText6 = new JTextField("");
			 frame1.add(JText6);
			 JText6.setBounds(100, 150, 100, 30);
			 
			 

			 JLabel lable4 = new JLabel("点击事件：");
			 frame1.add(lable4);
			 lable4.setBounds(230,150, 75, 25); 
		     final JTextField JText4 = new JTextField("");
			 frame1.add(JText4);
			 JText4.setBounds(300, 150, 100, 30);
			 

			 JLabel lable5 = new JLabel("滑动事件：");
			 frame1.add(lable5);
			 lable5.setBounds(430,150, 75, 25);
			    
		     final JTextField JText5 = new JTextField("");
			 frame1.add(JText5);
			 JText5.setBounds(500, 150, 100, 30);
			 
			 
			 JLabel lable7 = new JLabel("轨迹球事件：");
			 frame1.add(lable7);
			 lable7.setBounds(20,220, 75, 25); 
		     final JTextField JText7 = new JTextField("");
			 frame1.add(JText7);
			 JText7.setBounds(100, 220, 100, 30);
			 
			 

			 JLabel lable8 = new JLabel("导航事件：");
			 frame1.add(lable8);
			 lable8.setBounds(230,220, 75, 25); 
		     final JTextField JText8 = new JTextField("");
			 frame1.add(JText8);
			 JText8.setBounds(300, 220, 100, 30);
			 

			 JLabel lable9 = new JLabel("页面切换事件：");
			 frame1.add(lable9);
			 lable9.setBounds(420,220, 85, 25);
			    
		     final JTextField JText9 = new JTextField("");
			 frame1.add(JText9);
			 JText9.setBounds(500, 220, 100, 30);
			 
			  
				

			 JLabel lable11 = new JLabel("日志路径：");
			 frame1.add(lable11);
			 lable11.setBounds(0, 300, 200, 35);   
		     final JTextField JText11 = new JTextField("D:/log/Monkey_log/");
			 frame1.add(JText11);
			 JText11.setBounds(70, 300, 200, 35); 
			 
			final JButton button1 = new JButton("开始测试");		
			button1.setBounds(400, 300, 200, 35);  
		    frame1.add(button1);
		    JLabel lable13 = new JLabel("延时:");
			frame1.add(lable13);
		    lable13.setBounds(280, 300, 200, 35);   
	        //初始化下拉列表框  
	        final JComboBox<String> box = new JComboBox<String>();  
	        box.addItem("0");  
	        box.addItem("0.1秒");  
	        box.addItem("0.5秒"); 
	        box.addItem("1秒");
	        box.addItem("2秒"); 
	        box.setBounds(320, 300, 75, 35);  
	        frame1.add(box); 
	      

		    
		    
	button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
		public void actionPerformed(ActionEvent e)
		{	
			new Thread(new Runnable(){
	            public void run() {
	        		try {
					String anyevent;
					String touch;
					String motion;
					String trackball;
					String syskeys;
					String appswitch;
					String throttle = null;
					String DevicesName=JText0.getText();
					String PackageName =JText.getText();
					String zscount=JText2.getText();
					if(JText6.getText().isEmpty())
					{
					   anyevent=JText6.getText();
					}
					else
					{
						 anyevent=" --pct-anyevent "+JText6.getText();
					}
					
					if(JText4.getText().isEmpty())
					{
						touch=JText4.getText();
					}
					else
					{
						 touch=" --pct-touch "+JText4.getText();
					}
			
					if(JText5.getText().isEmpty())
					{
						motion=JText5.getText();
					}
					else
					{
						motion=" --pct-motion "+JText5.getText();
					}

					if(JText7.getText().isEmpty())
					{
						trackball=JText7.getText();
					}
					else
					{
						trackball=" --pct-trackball "+JText7.getText();
					}
			
					if(JText8.getText().isEmpty())
					{
						syskeys=JText8.getText();
					}
					else
					{
						syskeys=" --pct-syskeys "+JText8.getText();
					}
					if(JText9.getText().isEmpty())
					{
						appswitch=JText9.getText();
					}
					else
					{
						appswitch=" --pct-appswitch "+JText9.getText();
					}
					
					if(box.getSelectedItem().equals("0"))
					{
						throttle="";
					}
					if(box.getSelectedItem().equals("0.1秒"))
					{
						throttle="--throttle "+100;
					}
					if(box.getSelectedItem().equals("0.5秒"))
					{
						throttle="--throttle "+500;
					}
					if(box.getSelectedItem().equals("1秒"))
					{
						throttle="--throttle "+1000;
					}
					if(box.getSelectedItem().equals("2次"))
					{
						throttle="--throttle "+2000;
					}
					
					
					
			
					String count=JText1.getText();
				    String path =JText11.getText();
					Monkey.Monkey(DevicesName, PackageName, zscount,throttle,anyevent, touch, motion, trackball, syskeys,appswitch, count,path);
				
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        }).start();
				}});
			
			setTitle("Monkey-----testly");
			setBounds(0, 150, 635, 408);
			add(frame1);
			setVisible(true);
			addWindowListener(new WindowAdapter() {
			      public void windowClosing(WindowEvent e) {
			        exit();
			      }});
			
			
			
		}
			
		
		
		public void actionPerformed(ActionEvent e){

		 
		}

		 public void exit() {
			   
			       setVisible(false);
			      //setDefaultCloseOperation(EXIT_ON_CLOSE);
			    
			  }
			

		
		
		
	public static void Monkey() throws Exception{
	
/*
		Thread j= new Thread();	
		j.start();*/
		Monkey_Menu m=new Monkey_Menu();
		//m.setState(JFrame.ICONIFIED); 
		m.setVisible(true);
		
		}
	
	
		

}
