package test;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class pod {

	    public static void main(String[] args) throws IOException {
	    	createLineChart("test", "100", "100", createDataset());
	    	
    	
	}
   
	    /**
	     * 生成折线图
	     * @param chartTitle 图的标题
	     * @param x          横轴标题
	     * @param y          纵轴标题
	     * @param dataset    数据集
	     * @return
	     */
	    public static JFreeChart createLineChart(
	            String chartTitle, String x,
	            String y, CategoryDataset dataset) {
	     
	        // 构建一个chart
	        JFreeChart chart = ChartFactory.createLineChart(
	                chartTitle,
	                x,
	                y,
	                dataset,
	                PlotOrientation.VERTICAL,
	                true,
	                true,
	                false);
	        //字体清晰
	        chart.setTextAntiAlias(false);
	        // 设置背景颜色
	        chart.setBackgroundPaint(Color.WHITE);
	     
	        // 设置图标题的字体
	        Font font = new Font("隶书", Font.BOLD, 25);
	        chart.getTitle().setFont(font);
	     
	        // 设置面板字体
	        Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
	        // 设置图示的字体
	        chart.getLegend().setItemFont(labelFont);
	     
	        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
	        // x轴 // 分类轴网格是否可见
	        categoryplot.setDomainGridlinesVisible(true);
	        // y轴 //数据轴网格是否可见
	        categoryplot.setRangeGridlinesVisible(true);
	        categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩
	        categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩
	        categoryplot.setBackgroundPaint(Color.lightGray);// 折线图的背景颜色
	     
	        // 设置轴和面板之间的距离
	        // categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
	     
	        // 横轴 x
	        CategoryAxis domainAxis = categoryplot.getDomainAxis();
	        domainAxis.setLabelFont(labelFont);// 轴标题
	        domainAxis.setTickLabelFont(labelFont);// 轴数值
	        // domainAxis.setLabelPaint(Color.BLUE);//轴标题的颜色
	        // domainAxis.setTickLabelPaint(Color.BLUE);//轴数值的颜色
	     
	        // 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
	     
	        // 设置距离图片左端距离
	        domainAxis.setLowerMargin(0.0);
	        // 设置距离图片右端距离
	        domainAxis.setUpperMargin(0.0);
	     
	        // 纵轴 y
	        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
	        numberaxis.setLabelFont(labelFont);
	        numberaxis.setTickLabelFont(labelFont);
	        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        numberaxis.setAutoRangeIncludesZero(true);
	     
	        // 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
	        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
	                .getRenderer();
	        lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
	        lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
	     
	        // 显示折点数据
	        lineandshaperenderer
	                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	        lineandshaperenderer.setBaseItemLabelsVisible(true);
	     
	        return chart;
	    }
		public static String  battery(String commd) throws IOException{
			String	batt = null	;
			Runtime runtime = Runtime.getRuntime();
			Process proc	= runtime.exec( "adb  shell dumpsys gfxinfo com.sds.android.ttpod|awk '/Execute/,/hierarchy/{if(i>1)print x;x=$0;i++}'|sed /^[[:space:]]*$/d|awk '{if(length($0)==16)print $1,$2,$3}'" );
			try {
				if ( proc.waitFor() != 0 ){
					System.err.println( "exit value = " + proc.exitValue() );
				}
				BufferedReader in = new BufferedReader( new InputStreamReader(proc.getInputStream() ) );
				StringBuffer	stringBuffer	= new StringBuffer();
				String		line		= null;
				while ( (line = in.readLine() ) != null ){
					stringBuffer.append( line + " " );
				}
				String	str1	= stringBuffer.toString();
				batt = str1;
				} catch ( InterruptedException e ) {
				System.err.println( e );
				} finally {
				try {
					proc.destroy();
				} catch ( Exception e2 ) {
				}
			}
			return batt;
		}
		
		
		/**
		 * 柱状图数据集
		 *
		 * @return
		 */
		public static CategoryDataset createDataset() {
		    String str1 = "Java EE开发";
		    String str2 = "IOS开发";
		    String str3 = "Android开发";
		    String str4 = "1月";
		    String str5 = "2月";
		    String str6 = "3月";
		    String str7 = "4月";
		    String str8 = "5月";
		 
		    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 
		    dataset.addValue(1.0D, str1, str4);
		    dataset.addValue(4.0D, str1, str5);
		    dataset.addValue(3.0D, str1, str6);
		    dataset.addValue(5.0D, str1, str7);
		    dataset.addValue(5.0D, str1, str8);
		 
		    dataset.addValue(5.0D, str2, str4);
		    dataset.addValue(7.0D, str2, str5);
		    dataset.addValue(6.0D, str2, str6);
		    dataset.addValue(8.0D, str2, str7);
		    dataset.addValue(4.0D, str2, str8);
		 
		    dataset.addValue(4.0D, str3, str4);
		    dataset.addValue(3.0D, str3, str5);
		    dataset.addValue(2.0D, str3, str6);
		    dataset.addValue(3.0D, str3, str7);
		    dataset.addValue(6.0D, str3, str8);
		    return dataset;
		}

}
