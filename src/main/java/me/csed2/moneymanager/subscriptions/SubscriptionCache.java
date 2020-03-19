package me.csed2.moneymanager.subscriptions;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.cache.commands.LoadFromDBCommand;
import me.csed2.moneymanager.cache.commands.SaveToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SubscriptionCache extends Cache<Subscription> {

    private static SubscriptionCache instance;

    public SubscriptionCache() {
        instance = this;
    }
    public static SubscriptionCache getInstance() {
        return instance;
    }

    @Override
    public void load() throws FileNotFoundException {
        this.items = CommandDispatcher.getInstance().dispatchSync(new LoadFromDBCommand<>(Subscription.class, "Subscriptions.json"));
    }

    @Override
    public void save() {
        CommandDispatcher.getInstance().dispatchSync(new SaveToDBCommand<>("Subscriptions.json", items));
    }

    public List<Subscription> readByCategoryID(int categoryId) {
        List<Subscription> Subscriptions = new ArrayList<>();

        for (Subscription Subscription : items) {
            if (idPredicate.test(categoryId, Subscription.getId())) {
                Subscriptions.add(Subscription);
            }
        }
        return Subscriptions;
    }

    public List<Subscription> readByCategory(String name, Predicate<String> predicate) {
        List<Subscription> Subscriptions = new ArrayList<>();

        for (Subscription Subscription : items) {
            if (namePredicate.test(name, Subscription.getName()) && predicate.test(Subscription.getName())) {
                Subscriptions.add(Subscription);
            }
        }
        return Subscriptions;
    }

    public List<Subscription> readByCategory(String name) {
        List<Subscription> Subscriptions = new ArrayList<>();

        for (Subscription Subscription : items) {
            if (namePredicate.test(name, Subscription.getCategory())) {
                Subscriptions.add(Subscription);
            }
        }
        return Subscriptions;
    }
}
