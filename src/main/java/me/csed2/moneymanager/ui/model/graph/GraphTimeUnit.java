package me.csed2.moneymanager.ui.model.graph;

import lombok.Getter;

import java.util.*;

public enum GraphTimeUnit {
    DAY("d"),
    MONTH("m"),
    YEAR("y");

    @Getter
    private String code;

    GraphTimeUnit(String code) {
        this.code = code;
    }

    private static final String[] MONTHS_ABRV = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static String getMonthAbrv(int month) {
        if (month >= 0 && month <= 11) {
            return MONTHS_ABRV[month];
        } else {
            throw new NullPointerException();
        }
    }
}
