package me.csed2.moneymanager.subscriptions;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.cache.commands.LoadFromDBCommand;
import me.csed2.moneymanager.cache.commands.SaveToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;

import java.io.FileNotFoundException;

public class SubscriptionCache extends Cache<Subscription> {

    private static SubscriptionCache instance;

    public SubscriptionCache() {
        instance = this;
    }

    public static SubscriptionCache getInstance() {
        return instance;
    }

    @Override
    public void load(String fileName) throws FileNotFoundException {
        this.items = CommandDispatcher.getInstance().dispatchSync(new LoadFromDBCommand<>(Subscription.class, fileName));
    }

    @Override
    public void save(String fileName) {
        CommandDispatcher.getInstance().dispatchSync(new SaveToDBCommand<>(fileName, items));
    }
}
