package me.csed2.moneymanager.main;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.AutoSave;
import me.csed2.moneymanager.budget.BudgetTracker;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionNotificationDispatcher;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.ui.controller.InputReader;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.ui.view.CMDRenderer;
import me.csed2.moneymanager.ui.view.SwingRenderer;
import me.csed2.moneymanager.ui.view.UIRenderer;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * @author Ollie
 * @since 08/03/2020
 */
public class App {

    @Getter
    private UINode currentNode;

    @Getter
    private UIRenderer renderer = new SwingRenderer();

    // Threads
    private final InputReader reader;

    private final AutoSave autoSave;

    private final Thread subscriptionNotifications;

    // Caches
    @Getter
    private CachedList<Category> categoryCache = new CachedList<>();

    @Getter @Setter
    private CachedList<Transaction> transactionCache = new CachedList<>();

    @Getter
    private CachedList<Subscription> subscriptionCache = new CachedList<>();

    // Settings
    private SettingWrapper settings;

    // Monzo
    @Getter
    private MonzoHttpClient monzoClient;

    @Getter
    private static App instance;

    public App() {

        // Start reading input
        reader = new InputReader();
        reader.start();

        // Start autosave
        autoSave = new AutoSave(5, TimeUnit.MINUTES);
        autoSave.start();

        subscriptionNotifications = new Thread(new SubscriptionNotificationDispatcher(this, this.renderer));
        subscriptionNotifications.start();

        monzoClient = new MonzoHttpClient();

        // Load caches
        try {
            // Load settings
            this.settings = new SettingWrapper("settings.json");

            this.renderer = ((String) settings.get("renderer").getValue()).equalsIgnoreCase("CMD") ? new CMDRenderer() : new SwingRenderer();

            // Load caches
            categoryCache.load(Category.class, "categories.json");
            transactionCache.load(Transaction.class, "transactions.json");
            subscriptionCache.load(Subscription.class, "subscriptions.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //this loads the budget store, by taking information from the cache
        BudgetTracker.loadBudgetStore();

        instance = this;
    }

    public void render(UINode node) {
        this.currentNode = node;
        renderer.render(node);
    }

    public void render(String text) {
        renderer.renderText(text);
    }

    public void render(Stage<?> stage) {
        renderer.renderStage(stage);
    }

    public void sendMessage(String message) {
        renderer.renderText(message);
    }

    public synchronized void exit() {
        AuthServerManager.getInstance().closeAll();
        App.getInstance().getCategoryCache().save("categories.json");
        subscriptionNotifications.interrupt();
        autoSave.interrupt();
        reader.interrupt();
        System.exit(0);
    }
}
