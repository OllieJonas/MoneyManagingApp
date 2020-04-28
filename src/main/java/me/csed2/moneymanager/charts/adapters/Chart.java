package me.csed2.moneymanager.charts.adapters;

import org.jfree.chart.JFreeChart;

import java.util.Collection;

public interface Chart<T> {

    JFreeChart makeChart();

    ChartImpl build();

    Collection<T> getData();

    void reload();

    JFreeChart getChart();
}
