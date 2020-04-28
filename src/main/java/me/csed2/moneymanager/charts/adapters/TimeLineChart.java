package me.csed2.moneymanager.charts.adapters;

import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.charts.TimeScale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TimeLineChart<T extends Cacheable> extends TimeChart<T> {

    private final String xAxisLabel;
    private final String yAxisLabel;
    private final String yField;
    private final TimeScale scale;
    
    private List<Number> yRawData;

    private XYDataset dataset;

    private TimeLineChart(String title, String xAxisLabel, String yAxisLabel, String yField,
                          TimeScale scale, Collection<T> data) {
        super(title, data);
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.yField = yField.toLowerCase();
        this.scale = scale;
    }

    private XYDataset buildDataset() {
        TimeSeries series = new TimeSeries("Data");

        for (int i = 0; i < xRawData.size(); i++) {
            RegularTimePeriod period = scale.construct(xRawData.get(i));
            series.add(period, yRawData.get(i));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public TimeLineChart<T> build() {
        super.build();
        this.yRawData = getYDataFromAllFields(yField);
        this.dataset = buildDataset();
        return this;
    }

    @Override
    public JFreeChart makeChart() {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                String.format("Time (%s)", scale.getName()),
                yAxisLabel,
                dataset,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0F));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static class Builder<T extends Cacheable> {
        private String title;
        private String xAxisLabel;
        private String yAxisLabel;
        private String yField;
        private TimeScale scale;

        private Collection<T> data;

        public Builder(Collection<T> data) {
            this.data = data;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withXAxisLabel(String xAxisLabel) {
            this.xAxisLabel = xAxisLabel;
            return this;
        }

        public Builder withYAxisLabel(String yAxisLabel) {
            this.yAxisLabel = yAxisLabel;
            return this;
        }

        public Builder withYField(String yField) {
            this.yField = yField;
            return this;
        }

        public Builder withTimescale(TimeScale scale) {
            this.scale = scale;
            return this;
        }

        public TimeLineChart build() {
            return new TimeLineChart<>(title, xAxisLabel, yAxisLabel, yField, scale, data).build();
        }
    }
}
