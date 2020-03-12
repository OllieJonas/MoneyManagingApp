package me.csed2.moneymanager.transactions;

import lombok.Getter;

public enum TransactionArgType {
    NAME(String.class),
    AMOUNT(Integer.class),
    NOTES(String.class), // String because that's what the thing takes
    VENDOR(String.class);

    @Getter
    Class<?> klass;

    TransactionArgType(Class<?> klass) {
        this.klass = klass;
    }
}
