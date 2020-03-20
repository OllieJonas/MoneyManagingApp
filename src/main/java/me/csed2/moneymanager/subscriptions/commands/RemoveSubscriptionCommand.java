package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;

public class RemoveSubscriptionCommand implements ICommand<Boolean> {

    private final String subscriptionName;

    public RemoveSubscriptionCommand(String subscriptionName) {
        this.subscriptionName = subscriptionName;

    }
    @Override
    public Boolean execute() {
        SubscriptionCache subscriptionCache = SubscriptionCache.getInstance();

        if (subscriptionCache.exists(subscriptionName)) {
            subscriptionCache.remove(subscriptionName);
            subscriptionCache.save();
            return true;
        }
        return false;
    }
}
