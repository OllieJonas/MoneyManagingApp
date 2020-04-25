package me.csed2.moneymanager.charts.adapters;

import com.google.gson.internal.Primitives;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import org.jfree.chart.JFreeChart;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

@SuppressWarnings("WeakerAccess")
public abstract class Graph {

    protected String title;

    protected JFreeChart chart;

    protected final Collection<? extends Cacheable> data;

    public Graph(String title, Collection<? extends Cacheable> data) {
        this.title = title;
        this.data = data;
    }

    public abstract JFreeChart makeChart();

    protected List<Date> getDates() {
        return data.parallelStream()
                .collect(Collector.of(
                        ArrayList::new,
                        (list, item) -> list.add(item.getDate()),
                        (left, right) -> {left.addAll(right); return left;},
                        Collector.Characteristics.UNORDERED));
    }

    protected List<Number> getDataFromField(String field) {
        return data.parallelStream().collect(Collector.of(ArrayList::new,
                (list, item) -> addFieldDataToList(list, item, field),
                (left, right) -> { left.addAll(right); return left; }, Collector.Characteristics.UNORDERED));
    }

    private void addFieldDataToList(List<Number> list, Cacheable item, String field) {
        try {
            Field classField = item.getClass().getDeclaredField(field);
            if (Number.class.isAssignableFrom(Primitives.wrap(classField.getType()))) {
                classField.setAccessible(true);
                list.add((Number) classField.get(item));
            } else {
                throw new InvalidTypeException(Number.class, classField.getType());
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public JFreeChart getChart() {
        return chart;
    }
}
