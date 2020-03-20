package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;

public class UpdateSubscriptionCommand<T> implements ICommand<Boolean> {

    private final T result;

    private final String subscriptionName;

    private final SubscriptionArgType argType;


    public UpdateSubscriptionCommand(String subscriptionName, SubscriptionArgType argType, T result) {
        this.subscriptionName = subscriptionName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean execute() {
        SubscriptionCache cache = SubscriptionCache.getInstance();
        Subscription subscription = cache.readByName(subscriptionName);
        if (subscription != null) {
            switch (argType) {
                case NAME:
                    subscription.setName((String) result);
                    break;
                case AMOUNT:
                    subscription.setAmount((Integer) result);
                    break;
                case NOTES:
                    subscription.setNotes((String[]) result);
                    break;
                case VENDOR:
                    subscription.setVendor((String) result);
                    break;
                case CATEGORY:
                    CategoryCache categoryCache = CategoryCache.getInstance();
                    String categoryName = (String) result;

                    if (categoryCache.exists(categoryName)) {
                        subscription.setCategory(categoryName);
                        break;
                    } else {
                        return false;
                    }
                default:
                    return false; // Should never be called
            }
            cache.update(subscription);
            cache.save();
            return true;
        }
        System.out.println("ERROR HERE");
        return false;
    }
}
