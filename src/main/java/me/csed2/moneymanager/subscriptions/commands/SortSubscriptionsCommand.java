package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;

import java.util.Comparator;
import java.util.function.Function;

public class SortSubscriptionsCommand implements Function<App, CachedList<Subscription>> {

    private SubscriptionArgType argType;
    private boolean reversed;

    public SortSubscriptionsCommand(SubscriptionArgType argType, boolean reversed) {
        this.argType = argType;
        this.reversed = reversed;
    }

    @Override
    public CachedList<Subscription> apply(App app) {
        Comparator<Subscription> comparator = getComparator(argType);
        CachedList<Subscription> sortedList = app.getSubscriptionCache().sort(comparator);
        app.render(sortedList.getReport());
        return sortedList;
    }

    private Comparator<Subscription> getComparator(SubscriptionArgType argType) {
        if (argType == SubscriptionArgType.NAME) {
            return Comparator.comparing(Subscription::getName);
        } else if (argType == SubscriptionArgType.AMOUNT) {
            return Comparator.comparingDouble(Subscription::getAmount);
        } else if (argType == SubscriptionArgType.ID) {
            return Comparator.comparingInt(Subscription::getId);
        } else if (argType == SubscriptionArgType.CREATED) {
            return Comparator.comparing(Subscription::getDate);
        } else if (argType == SubscriptionArgType.VENDOR) {
            return Comparator.comparing(Subscription::getVendor);
        } else {
            return null;
        }
    }
}
