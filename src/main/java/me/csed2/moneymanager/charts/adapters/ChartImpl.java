package me.csed2.moneymanager.charts.adapters;

import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.utils.ClassUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.title.TextTitle;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;

@SuppressWarnings("WeakerAccess")
public abstract class ChartImpl<T extends Cacheable> implements Chart<T> {

    protected String title;

    protected JFreeChart chart;

    protected final Collection<T> data;

    public ChartImpl(String title, Collection<T> data) {
        this.title = title;
        this.data = data;
    }


    @Override
    public Collection<T> getData() {
        return data;
    }

    @Override
    public void reload() {

    }

    protected List<Number> getYDataFromAllFields(String field) {
        return data.parallelStream().collect(Collector.of(ArrayList::new,
                (list, item) -> list.add(getYDataFromField(item, field)),
                (left, right) -> { left.addAll(right); return left; }, Collector.Characteristics.UNORDERED));
    }

    protected Number getYDataFromField(T item, String field) {
        return ClassUtils.canCast(getValueFromField(item, field), Number.class)
                ? (Number) getValueFromField(item, field) : null;
    }

    protected Object getValueFromField(T item, String field) {
        try {
            Field classField = item.getClass().getDeclaredField(field);
            classField.setAccessible(true);
            return classField.get(item);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected JFreeChart decorateLookAndFeel(JFreeChart original) {
        original.getLegend().setFrame(BlockBorder.NONE);
        original.setTitle(new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)));
        return original;
    }

    public JFreeChart getChart() {
        return chart;
    }
}
