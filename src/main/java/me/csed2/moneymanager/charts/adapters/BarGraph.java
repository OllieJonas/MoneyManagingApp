package me.csed2.moneymanager.charts.adapters;

import me.csed2.moneymanager.cache.Cacheable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Collection;

public class BarGraph<T extends Cacheable> extends ChartImpl<T> {

    private CategoryDataset dataset;
    private String categoryAxisLabel;
    private String valueAxisLabel;
    private String valueField;

    public BarGraph(String title, String categoryAxisLabel, String valueAxisLabel, String valueField, Collection<T> data) {
        super(title, data);
        this.categoryAxisLabel = categoryAxisLabel;
        this.valueAxisLabel = valueAxisLabel;
        this.valueField = valueField;
    }

    private CategoryDataset makeDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (T item : data) {
            Number value = getYDataFromField(item, valueField);
            dataset.addValue(value, item.getName(), item.getName());
        }
        return dataset;
    }


    @Override
    public JFreeChart makeChart() {
        return ChartFactory.createBarChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    @Override
    public ChartImpl build() {
        this.dataset = makeDataset();
        return this;
    }
}
