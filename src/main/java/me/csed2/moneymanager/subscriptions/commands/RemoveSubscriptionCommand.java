package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.subscriptions.SubscriptionCache;

import java.util.function.Supplier;

public class RemoveSubscriptionCommand implements Supplier<Boolean> {

    private final String subscriptionName;

    public RemoveSubscriptionCommand(String subscriptionName) {
        this.subscriptionName = subscriptionName;

    }
    @Override
    public Boolean get() {
        SubscriptionCache subscriptionCache = SubscriptionCache.getInstance();

        if (subscriptionCache.exists(subscriptionName)) {
            subscriptionCache.remove(subscriptionName);
            subscriptionCache.save();
            return true;
        }
        return false;
    }
}
