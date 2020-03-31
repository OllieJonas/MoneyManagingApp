package me.csed2.moneymanager.cache.commands;

import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.transactions.Transaction;
import java.util.Comparator;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class SortCachedListCommand<E extends Cacheable> implements Function<App, CachedList<E>> {
    private Comparator<E>  comparator;
    private Class<E> klass;
    public SortCachedListCommand(Class<E> klass, Comparator<E> comparator){
        this.comparator=comparator;
        this.klass=klass;
    }

    @Override
    public CachedList<E> apply(App app) {
        if(klass == Category.class){
            return (CachedList<E>) app.getCategoryCache().sort((Comparator<Category>) comparator);
        }
        if(klass == Transaction.class){
            return (CachedList<E>) app.getTransactionCache().sort((Comparator<Transaction>) comparator);
        }
        if(klass == Subscription.class){
            return (CachedList<E>) app.getSubscriptionCache().sort((Comparator<Subscription>) comparator);
        }
        return null;
    }

}
