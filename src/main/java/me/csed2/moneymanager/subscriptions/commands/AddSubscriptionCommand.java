package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionBuilder;

import java.util.Date;
import java.util.function.Function;

public class AddSubscriptionCommand implements Function<App, Boolean> {

    private final String categoryName;
    private final String name;
    private final int amount;
    private final String vendor;
    private final int timeCycle;
    private final String timeCycleUnit;
    private final String[] notes;

    public AddSubscriptionCommand(String categoryName, String name, int amount, String vendor, int timeCycle, String timeCycleUnit, String[] notes) {
        this.categoryName = categoryName;
        this.name = name;
        this.amount = amount;
        this.vendor = vendor;
        this.timeCycle=timeCycle;
        this.timeCycleUnit=timeCycleUnit;
        this.notes = notes;
    }

    @Override
    public Boolean apply(App app) {
        CachedList<Category> categoryCache = app.getCategoryCache();
        CachedList<Subscription> subscriptionCache = app.getSubscriptionCache();

        if (categoryCache.exists(categoryName)) {

            subscriptionCache.add(new SubscriptionBuilder(name)
                    .withAmount(amount)
                    .withVendor(vendor)
                    .withDate(new Date())
                    .withNotes(notes)
                    .withCategoryName(categoryName)
                    .build());

            subscriptionCache.save("subscriptions.json");
            return true;
        }
        return false;
    }
}
