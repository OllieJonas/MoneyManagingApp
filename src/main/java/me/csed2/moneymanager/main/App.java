package me.csed2.moneymanager.main;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.AutoSave;
import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.InputReader;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * @author Ollie
 * @since 08/03/2020
 */
public class App {


    // Menus
    @Getter @Setter
    private Menu previousMenu;

    @Getter @Setter
    private Menu currentMenu;

    // Threads
    private InputReader reader;

    private AutoSave autoSave;

    // Caches
    @Getter
    private Cache<Category> categoryCache = new Cache<>();

    @Getter
    private Cache<Transaction> transactionCache = new Cache<>();

    @Getter
    private Cache<Subscription> subscriptionCache = new Cache<>();

    @Getter
    private static App instance;

    public App() {
        reader = new InputReader();
        reader.start();

        autoSave = new AutoSave(5, TimeUnit.MINUTES);
        autoSave.start();

        try {
            categoryCache.load(Category.class, "categories.json");
            transactionCache.load(Transaction.class, "transactions.json");
            subscriptionCache.load(Subscription.class, "subscriptions.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        instance = this;
    }

    public void openMenu(Menu menu) {
        previousMenu = currentMenu;
        currentMenu = menu;
        menu.print();
    }

    public synchronized void exit() {
        System.out.println("Exiting program...");

        App.getInstance().getCategoryCache().save("categories.json");

        autoSave.interrupt();

        reader.interrupt();

        System.exit(0);
    }
}
