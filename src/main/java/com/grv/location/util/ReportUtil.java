package com.grv.location.util;

import java.util.List;

public interface ReportUtil {
	public void generatePieChart(String path, List<Object[]> data);
	public void generateBarChart(String path, List<Object[]> data);
}
