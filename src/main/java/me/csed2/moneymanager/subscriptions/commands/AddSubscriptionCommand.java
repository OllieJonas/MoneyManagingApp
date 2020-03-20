package me.csed2.moneymanager.subscriptions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.subscriptions.SubscriptionBuilder;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;

import java.util.Date;

public class AddSubscriptionCommand implements ICommand<Boolean> {

    private final String categoryName;
    private final String name;
    private final int amount;
    private final String vendor;
    private final String[] notes;

    public AddSubscriptionCommand(String categoryName, String name, int amount, String vendor, String[] notes) {
        this.categoryName = categoryName;
        this.name = name;
        this.amount = amount;
        this.vendor = vendor;
        this.notes = notes;
    }

    @Override
    public Boolean execute() {
        CategoryCache categoryCache = CategoryCache.getInstance();
        SubscriptionCache subscriptionCache = SubscriptionCache.getInstance();

        if (categoryCache.exists(categoryName)) {

            subscriptionCache.add(new SubscriptionBuilder(name)
                    .withAmount(amount)
                    .withVendor(vendor)
                    .withDate(new Date())
                    .withNotes(notes)
                    .withCategoryName(categoryName)
                    .build());

            subscriptionCache.save();
            return true;
        }
        return false;
    }
}
