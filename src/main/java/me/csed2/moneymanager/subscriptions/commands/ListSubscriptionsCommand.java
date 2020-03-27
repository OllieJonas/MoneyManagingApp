package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ListSubscriptionsCommand implements Supplier<List<Subscription>> {

    private final String categoryName;

    public ListSubscriptionsCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<Subscription> get() {
        CategoryCache cache = CategoryCache.getInstance();
        List<Subscription> subscriptions = new ArrayList<>();

        if (categoryName.equalsIgnoreCase("ALL")) {
            subscriptions = SubscriptionCache.getInstance().asList();
        } else {
            Optional<Category> category = cache.search(categoryName);
            if (category.isPresent()) {
                subscriptions = SubscriptionCache.getInstance().search(sub -> sub.getCategory().equalsIgnoreCase(categoryName));
            }
        }
        return subscriptions;
    }
}
