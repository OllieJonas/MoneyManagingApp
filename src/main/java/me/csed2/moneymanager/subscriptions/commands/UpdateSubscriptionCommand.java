package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;

import java.util.Optional;
import java.util.function.Function;

public class UpdateSubscriptionCommand<T> implements Function<App, Boolean> {

    private final T result;

    private final String subscriptionName;

    private final SubscriptionArgType argType;


    public UpdateSubscriptionCommand(String subscriptionName, SubscriptionArgType argType, T result) {
        this.subscriptionName = subscriptionName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean apply(App app) {
        CachedList<Subscription> cache = app.getSubscriptionCache();

        Optional<Subscription> subOptional = cache.search(subscriptionName);
        if (subOptional.isPresent()) {
            Subscription subscription = subOptional.get();
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
                    CachedList<Category> categoryCache = app.getCategoryCache();
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
            cache.save("subscriptions.json");
            return true;
        }
        System.out.println("ERROR HERE");
        return false;
    }
}
