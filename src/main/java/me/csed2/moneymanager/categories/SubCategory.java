package me.csed2.moneymanager.categories;

import lombok.Getter;

public class SubCategory<T extends Category> {

    @Getter
    private final T parent;

    public SubCategory(T parent) {
        this.parent = parent;
    }

}
