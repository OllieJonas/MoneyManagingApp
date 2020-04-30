package me.csed2.moneymanager.charts.adapters;

import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.charts.TimeScale;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;

@SuppressWarnings("WeakerAccess")
public abstract class TimeChart<T extends Cacheable> extends ChartImpl<T> {

    protected List<Date> xRawData;

    protected Predicate<? super T> filter;

    public TimeChart(String title, Collection<T> data) {
        super(title, data);
    }

    @Override
    public TimeChart<T> build() {
        if (filter != null)
            this.xRawData = getDates(filter);
        else
            this.xRawData = getDates();
        return this;
    }

    public static Predicate<? extends Cacheable> DATE_BUILDER(int scale, int amount) {
        if (scale == 100) {
            return null;
        } else {
            return t -> {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(t.getDate());
                calendar.add(scale, (-1) * amount);
                return t.getDate().before(calendar.getTime());
            };
        }
    }

    protected List<Date> getDates() {
        return data.parallelStream()
                .collect(Collector.of(
                        ArrayList::new,
                        (list, item) -> list.add(item.getDate()),
                        (left, right) -> {left.addAll(right); return left;},
                        Collector.Characteristics.UNORDERED));
    }

    protected List<Date> getDates(Predicate<? super T> filter) {
        return data.parallelStream()
                .filter(filter)
                .collect(Collector.of(
                        ArrayList::new,
                        (list, item) -> list.add(item.getDate()),
                        (left, right) -> {left.addAll(right); return left;},
                        Collector.Characteristics.UNORDERED));
    }

    public TimeChart<T> applyFilter(Predicate<? super T> filter) {
        this.filter = filter;
        return this;
    }
}
