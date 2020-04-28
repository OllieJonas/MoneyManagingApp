package me.csed2.moneymanager.charts;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;

public class JFreeDecorator {

    private JFreeChart chart;
    private Class<? extends Plot> plotClazz;


    public JFreeDecorator(JFreeChart chart) {
        this.chart = chart;
    }
}
