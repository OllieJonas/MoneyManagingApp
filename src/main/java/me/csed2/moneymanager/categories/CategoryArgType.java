package me.csed2.moneymanager.categories;

import lombok.Getter;

import java.util.Date;

public enum CategoryArgType {
    NAME(String.class),
    ID(Integer.class),
    CREATED(Date.class),
    BUDGET(Integer.class);

    @Getter
    Class<?> clazz;

    CategoryArgType(Class<?> clazz) {
        this.clazz = clazz;
    }

}
