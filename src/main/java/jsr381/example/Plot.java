/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsr381.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author zoran sevarac
 */
public class Plot {
    public static void line(String title, String xAxisLabel, String yAxisLabel, double[][] data) {
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
          xData.add(data[i][0]);
          yData.add(data[i][1]);
        }

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();

        // Customize Chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setXAxisLabelRotation(45);

        // Series
        chart.addSeries("Data", xData, yData);
        
        // display chart
        new SwingWrapper<>(chart).displayChart(title);        
    }
    
    public static void line(double[][] data) {
        line("Line Chart", "x", "y", data);
    }
    
    public static void scatter(double[][] data) {
        scatter("Scatter Plot", "x", "y", data);
    }
    
    public static void scatter(String title, String xAxisLabel, String yAxisLabel, double[][] data) {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideN);

        // Series
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
          xData.add(data[i][0]);
          yData.add(data[i][1]);
        }
        chart.addSeries("Data", xData, yData);

        new SwingWrapper<>(chart).displayChart(title); 
    }
    
    
}
