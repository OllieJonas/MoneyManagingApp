package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.subscriptions.Subscription;

import java.util.function.Function;

public class RemoveSubscriptionCommand implements Function<App, Boolean> {

    private final String subscriptionName;

    public RemoveSubscriptionCommand(String subscriptionName) {
        this.subscriptionName = subscriptionName;

    }
    @Override
    public Boolean apply(App app) {
        CachedList<Subscription> subscriptionCache = app.getSubscriptionCache();

        if (subscriptionCache.exists(subscriptionName)) {
            subscriptionCache.remove(subscriptionName);
            subscriptionCache.save("subscriptions.json");
            return true;
        }
        return false;
    }
}
