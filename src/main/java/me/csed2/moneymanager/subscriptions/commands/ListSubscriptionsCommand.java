package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.subscriptions.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ListSubscriptionsCommand implements Command<List<Subscription>> {

    private final String categoryName;

    public ListSubscriptionsCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<Subscription> execute(App app) {
        CachedList<Category> cache = app.getCategoryCache();
        List<Subscription> subscriptions = new ArrayList<>();

        if (categoryName.equalsIgnoreCase("ALL")) {
            subscriptions = app.getSubscriptionCache().asList();
        } else {
            Optional<Category> category = cache.search(categoryName);
            if (category.isPresent()) {
                subscriptions = app.getSubscriptionCache().search(sub -> sub.getCategory().equalsIgnoreCase(categoryName));
            }
        }
        return subscriptions;
    }
}
