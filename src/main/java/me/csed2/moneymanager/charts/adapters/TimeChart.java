package me.csed2.moneymanager.charts.adapters;

import me.csed2.moneymanager.cache.Cacheable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;

@SuppressWarnings("WeakerAccess")
public abstract class TimeChart<T extends Cacheable> extends ChartImpl<T> {

    protected List<Date> xRawData;

    public TimeChart(String title, Collection<T> data) {
        super(title, data);
    }

    @Override
    public TimeChart<T> build() {
        this.xRawData = getDates();
        return this;
    }

    protected List<Date> getDates() {
        return data.parallelStream()
                .collect(Collector.of(
                        ArrayList::new,
                        (list, item) -> list.add(item.getDate()),
                        (left, right) -> {left.addAll(right); return left;},
                        Collector.Characteristics.UNORDERED));
    }
}
