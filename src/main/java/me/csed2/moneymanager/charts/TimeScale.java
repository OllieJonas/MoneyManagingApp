package me.csed2.moneymanager.charts;

import org.jfree.data.time.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public enum TimeScale {
    HOUR("Hours", Hour.class),
    DAY("Days", Day.class),
    MONTH("Months", Month.class),
    YEAR("Years", Year.class);

    private final String name;
    private final Class<? extends RegularTimePeriod> clazz;

    TimeScale(String name, Class<? extends RegularTimePeriod> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public RegularTimePeriod construct(Date date) {
        try {
            Constructor<?> constructor = clazz.getConstructor(Date.class);
            constructor.setAccessible(true);
            return (RegularTimePeriod) constructor.newInstance(date);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
