package me.csed2.moneymanager.charts.adapters;

import com.google.gson.internal.Primitives;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import org.jfree.chart.JFreeChart;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
        List<Date> dates = new ArrayList<>();
        data.forEach(item -> {System.out.println(item.getDate()); dates.add(item.getDate());});
        return dates;
    }

    @SuppressWarnings("unchecked")
    protected List<Number> getDataFromField(String field) {
        List<Number> list = new ArrayList<>();

        for (Cacheable item : data) {
            try {
                Field classField = item.getClass().getDeclaredField(field);

                if (Number.class.isAssignableFrom(Primitives.wrap(classField.getType()))) {
                    classField.setAccessible(true);
                    list.add((Number) classField.get(item));

                } else {
                    throw new InvalidTypeException(Number.class, classField.getType());
                }

            } catch (NoSuchFieldException e) {
                App.getInstance().render("Error: Unable to get data from field!");
            } catch (IllegalAccessException e) {
                App.getInstance().render("Error: Unable to access private field in LineGraphAdapter! " +
                        "Please contact a dev if you see this! :(");
            }
        }
        return list;
    }

    public JFreeChart getChart() {
        return chart;
    }
}
