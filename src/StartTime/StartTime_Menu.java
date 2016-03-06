package StartTime;

import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class StartTime_Menu extends JFrame
	implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static String text;
		public JPanel frame = new JPanel();
       
		public StartTime_Menu() throws IOException{
			frame.setLayout(null);  
            final JTextArea txt = new JTextArea ();
            txt.setLineWrap(true);
            txt.setWrapStyleWord(true);  
            txt.setBounds(10,10,600,452);
            add(txt);
            frame.add(txt);
		    JLabel lable = new JLabel("包名:");
		    frame.add(lable);
		    lable.setBounds(70, 510, 45, 25);
		    
		    final JTextField JText = new JTextField("");
		    frame.add(JText);
		    JText.setBounds(130, 510, 180, 30);
			 JLabel lable1 = new JLabel("启动Activity名:");
			 frame.add(lable1);
			 lable1.setBounds(15, 550, 95, 25);
			    
		     final JTextField JText1 = new JTextField("");
			 frame.add(JText1);
			 JText1.setBounds(130, 550, 180, 30);
			final JButton button1 = new JButton("开始测试");		
			button1.setBounds(390, 530, 200, 35);  
		    frame.add(button1);
		
	        //初始化下拉列表框  
	        @SuppressWarnings({ "unchecked", "rawtypes" })
			final JComboBox<String> box = new JComboBox();  
	        box.addItem("1次");  
	        box.addItem("5次"); 
	        box.addItem("10次");
	        box.addItem("20次"); 
	        box.addItem("50次"); 
	        box.setBounds(320, 535, 55, 25);  
	        frame.add(box); 
   
			button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
				public void actionPerformed(ActionEvent e)
				{	
					int j = 1;
					if(box.getSelectedItem().equals("10次"))
					{
						j=10;
					}
					if(box.getSelectedItem().equals("5次"))
					{
						j=5;
					}
					if(box.getSelectedItem().equals("20次"))
					{
						j=20;
					}
					if(box.getSelectedItem().equals("50次"))
					{
						j=50;
					}
                     String pn=JText.getText();
                     String pn1=JText1.getText();
                   
					 button1.setBackground(Color.LIGHT_GRAY);
				try {
					for(int i=1;i<=j;i++)
					{
						
			            txt.append("第"+i+"次："+GetPackageStartTime.GetstartTime(pn,pn1)+"\n");
			            Thread.currentThread();
						Thread.sleep(100);
	            
					}	
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}   
				});
			

			setTitle("Android启动时间V1.0-----testly");
			setBounds(0,150, 632, 628);
			add(frame);
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
			  }
			

		
		
		
	public static void main(String []args) throws Exception{
		
		StartTime_Menu m=new StartTime_Menu();
		m.setVisible(true);
		
		}
	
	
		

}
