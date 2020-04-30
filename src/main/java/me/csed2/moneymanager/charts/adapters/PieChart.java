package me.csed2.moneymanager.charts.adapters;

import me.csed2.moneymanager.cache.Cacheable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class PieChart<T extends Cacheable> extends ChartImpl<T> implements Chart<T> {

    private String field;

    private PieDataset dataset;

    public PieChart(String title, String field, Collection<T> data) {
        super(title, data);
        this.field = field;
    }

    @Override
    public JFreeChart makeChart() {
        JFreeChart chart = decorateLookAndFeel(ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false));
        return chart;
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (T item : data) {
            dataset.setValue(item.getName(), getYDataFromField(item, field));
        }
        return dataset;
    }

    @Override
    public PieChart<T> build() {
        this.dataset = createDataset();
        return this;
    }
}
