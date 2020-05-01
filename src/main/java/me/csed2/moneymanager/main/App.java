package me.csed2.moneymanager.main;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.AutoSave;
import me.csed2.moneymanager.budget.BudgetCachedList;
import me.csed2.moneymanager.budget.autocommands.BudgetTracker;
import me.csed2.moneymanager.budget.autocommands.EndOfMonthActions;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.charts.adapters.Chart;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.sound.AudioDispatcher;
import me.csed2.moneymanager.sound.Sound;
import me.csed2.moneymanager.sound.SoundPack;
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
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @author Ollie
 * @since 08/03/2020
 */
public class App {

    public static final String DEFAULT_DIRECTORY = "Documents";

    private static final long AUTOSAVE_TIME = 5L;

    private static final TimeUnit AUTOSAVE_TIMEUNIT = TimeUnit.MINUTES;

    @Getter
    private UINode currentNode;

    @Getter
    private UIRenderer renderer = new SwingRenderer();

    // Sound
    private AudioDispatcher audio;

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

    @Getter
    private BudgetCachedList budgetCache;

    // Settings
    @Getter
    private SettingWrapper settings;

    // Monzo
    @Getter
    private MonzoHttpClient monzoClient;

    @Getter
    private static App instance;

    public App() {

        this.reader = startInputReader();
        this.autoSave = startAutoSave();

        this.subscriptionNotifications = new Thread(new SubscriptionNotificationDispatcher(this, this.renderer));
        this.subscriptionNotifications.start();
        this.monzoClient = new MonzoHttpClient();
        try {
            this.settings = new SettingWrapper("settings.json");
            this.renderer = settings.getValue("renderer", String.class)
                    .orElse("Swing").equals("CMD") ? new CMDRenderer() : new SwingRenderer();

            categoryCache.load(Category.class, "categories.json");
            transactionCache.load(Transaction.class, "transactions.json");
            subscriptionCache.load(Subscription.class, "subscriptions.json");
//            budgetCache.load("budgets.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.audio = new AudioDispatcher(new SoundPack(settings.getValue("soundpack", String.class).orElse("default")));
        instance = this;

        //this loads the budget store, by taking information from the cache
        BudgetTracker.loadBudgetStore();
        EndOfMonthActions.checkMonth();

        audio.playSound(Sound.GREETING);
    }

    private AutoSave startAutoSave() {
        AutoSave autoSave = new AutoSave(AUTOSAVE_TIME, AUTOSAVE_TIMEUNIT);
        autoSave.start();
        return autoSave;
    }

    private InputReader startInputReader() {
        InputReader reader = new InputReader();
        reader.start();
        return reader;
    }

    public void render(UINode node) {
        this.currentNode = node;
        renderer.render(node);
        playSound(node.getLoadSound());
    }

    public void render(Chart graph) {
        renderer.renderGraph(graph);
    }

    public void render(String text) {
        renderer.renderText(text);
    }

    public void render(Stage<?> stage) {
        renderer.renderStage(stage);
    }

    public void playSound(Sound sound) {
        if (sound != null)
            audio.playSound(sound);
    }

    public void sendMessage(String message) {
        renderer.renderText(message);
    }

    public synchronized void exit() {
        AuthServerManager.getInstance().closeAll();
        playSound(Sound.HAPPY_SAVING);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        App.getInstance().getCategoryCache().save("categories.json");
        subscriptionNotifications.interrupt();
        autoSave.interrupt();
        reader.interrupt();
        System.exit(0);
    }
}
