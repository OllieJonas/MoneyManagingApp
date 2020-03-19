package me.csed2.moneymanager.subscriptions;

import lombok.Getter;

public enum SubscriptionArgType {
    NAME(String.class),
    AMOUNT(Integer.class),
    CATEGORY(String.class),
    NOTES(String.class), // String because that's what the thing takes
    VENDOR(String.class);

    @Getter
    Class<?> klass;

    SubscriptionArgType(Class<?> klass) {
        this.klass = klass;
    }
}
