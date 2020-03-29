package me.csed2.moneymanager.main;

import lombok.Getter;
import me.csed2.moneymanager.AutoSave;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.cache.commands.LoadSettingsCommand;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.ui.controller.InputReader;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.ui.view.CMDRenderer;
import me.csed2.moneymanager.ui.view.UIRenderer;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Ollie
 * @since 08/03/2020
 */
public class App {

    @Getter
    private UINode currentNode;

    @Getter
    private UIRenderer renderer = new CMDRenderer();

    // Threads
    private InputReader reader;

    private AutoSave autoSave;

    // Caches
    @Getter
    private CachedList<Category> categoryCache = new CachedList<>();

    @Getter
    private CachedList<Transaction> transactionCache = new CachedList<>();

    @Getter
    private CachedList<Subscription> subscriptionCache = new CachedList<>();

    // Settings
    private Map<String, Setting<?>> settings;

    @Getter
    private static App instance;

    public App() {

        // Start reading input
        reader = new InputReader();
        reader.start();

        // Start autosave
        autoSave = new AutoSave(5, TimeUnit.MINUTES);
        autoSave.start();

        // Load caches
        try {
            // Load settings
             this.settings = CommandDispatcher.dispatchSync(new LoadSettingsCommand("settings.json"));
            // Load caches
            categoryCache.load(Category.class, "categories.json");
            transactionCache.load(Transaction.class, "transactions.json");
            subscriptionCache.load(Subscription.class, "subscriptions.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Assign an instance, also ensures GC doesn't collect anything in here.
        instance = this;
    }

    public void render(UINode node) {
        this.currentNode = node;
        renderer.render(node);
    }

    public void render(Stage<?> stage) {
        renderer.renderStage(stage);
    }

    public void sendMessage(String message) {
        renderer.sendMessage(message);
    }

    public synchronized void exit() {
        renderer.sendMessage("Exiting program...");
        App.getInstance().getCategoryCache().save("categories.json");
        autoSave.interrupt();
        reader.interrupt();
        System.exit(0);
    }
}
