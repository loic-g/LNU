package lg222sv_assign1;

import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

public class SinMain {

	public static void main(String[] args) {
		XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(800).height(600).build();
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setMarkerSize(2);
	    chart.getStyler().setXAxisMax(2*Math.PI);
	    chart.getStyler().setXAxisMin(0.0);
	    
	    
	    List<Double> xData = new ArrayList<>();
	    List<Double> yData = new ArrayList<>();
	    
	    double size = 2*Math.PI;
	    for (double i=0.0;i<size;i+=0.001) {
	    	// y = (1 + x/pi)*cos(x)*cos(40*x) 
	    	//in the range 0 <= x <= 2*pi.
	    	double helpy = 1;
	    	helpy+= i/Math.PI;
	    	helpy*=Math.cos(i);
	    	helpy*=Math.cos(i*40);
	    	yData.add(helpy);
	    	xData.add(i);
	    }
	    //System.out.println(xData);
	    //System.out.println(yData);
	    
	    chart.addSeries("y = (1 + x/pi)*cos(x)*cos(40*x)", xData,yData);
	    new SwingWrapper<XYChart>(chart).displayChart();
	}
}
