package me.csed2.moneymanager.transactions;

import lombok.Getter;

import java.util.Date;

public enum TransactionArgType {
    NAME(String.class),
    ID(Integer.class),
    AMOUNT(Integer.class),
    CATEGORY(String.class),
    CREATED(Date.class),
    NOTES(String.class), // String because that's what the thing takes
    VENDOR(String.class);

    @Getter
    Class<?> klass;

    TransactionArgType(Class<?> klass) {
        this.klass = klass;
    }
}
