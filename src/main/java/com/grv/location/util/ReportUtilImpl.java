package com.grv.location.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ReportUtilImpl implements ReportUtil {

	@Override
	public void generatePieChart(String path, List<Object[]> data) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for(Object[] objects:data) {
			dataSet.setValue(objects[0].toString(), Double.parseDouble(objects[1].toString()));
		}
		
		JFreeChart chart = ChartFactory.createPieChart3D("Location Type Report", dataSet, true, true, false);
		try {
			ChartUtilities.saveChartAsJPEG(new File(path+"/pieChart.jpeg"), chart, 400, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generateBarChart(String path, List<Object[]> data) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for(Object[] objects:data) {
			dataSet.addValue(Integer.parseInt(objects[1].toString()),objects[0].toString(),"TYPE");
		}
		
		JFreeChart chart = ChartFactory.createBarChart3D("Location Bar Chart", "", "Count", dataSet, PlotOrientation.VERTICAL, true, true, false);
		try {
			ChartUtilities.saveChartAsJPEG(new File(path+"/barChart.jpeg"), chart, 400, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
