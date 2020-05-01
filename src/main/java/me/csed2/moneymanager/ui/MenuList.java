package me.csed2.moneymanager.ui;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.commands.SortCategoriesCommand;
import me.csed2.moneymanager.charts.TimeScale;
import me.csed2.moneymanager.charts.adapters.BarGraph;
import me.csed2.moneymanager.charts.adapters.PieChart;
import me.csed2.moneymanager.charts.adapters.TimeLineChart;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.monzo.commands.MonzoAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoCheckAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoGetAccountsCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoGetTransactionsCommand;
import me.csed2.moneymanager.sound.Sound;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.commands.SortSubscriptionsCommand;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.SortTransactionsCommand;
import me.csed2.moneymanager.ui.model.Action;
import me.csed2.moneymanager.ui.model.ChartNode;
import me.csed2.moneymanager.ui.model.Menu;
import me.csed2.moneymanager.ui.model.UINode;

import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MenuList {

    public static final Menu MAIN = new Menu("Main Menu", null, null);

    //Budget
    public static final Menu BUDGET = new Menu("Budget", MAIN, "icons/budget.png");

    public static final Menu GRAPHS = new Menu("Graphs", MAIN, "icons/graphs_main_screen.png");

    public static final Action TOTAL_SPENDING = new Action("View Total Spending", GRAPHS, null, (Consumer<App>) app -> app.render("Total Spent: " + app.getTransactionCache().parallelStream().mapToDouble(Transaction::getAmount).sum()));
    public static final Action TRANS_SPENDING = new Action("View Transactional Spending", GRAPHS, null, (Consumer<App>) app -> app.render(new TimeLineChart.Builder<>(app.getTransactionCache())
        .withTimescale(TimeScale.DAY)
        .withTitle("Amount Spent")
        .withXAxisLabel("Time")
        .withYAxisLabel("Amount")
        .withYField("amount")
        .build())).withLoadSound(Sound.GRAPH);

    public static final Action CAT_DISTRO = new Action("View Category Spending", GRAPHS, null, (Consumer <App>) app -> app.render(new PieChart<>("Category Budget Spread", "budget", app.getCategoryCache()).build()));

            // Categories
    public static final Menu CATEGORIES = new Menu("Categories", MAIN, "icons/categories.png").withLoadSound(Sound.CATEGORY);
    public static final Action LIST_CATEGORIES = new Action("List All Categories", CATEGORIES, "icons/view_all.png", (Consumer<App>) app -> app.sendMessage(app.getCategoryCache().getReport()));
    public static final Menu UPDATE_CATEGORY = new Menu("Update a Category", CATEGORIES, "icons/button_update_0.png");

    public static final Menu SORT_CATEGORIES = new Menu ("Sort Categories", CATEGORIES, "icons/sort.png");

    public static final Action SORT_CATEGORIES_NAME = new Action("Sort by Name",SORT_CATEGORIES,null, new SortCategoriesCommand(CategoryArgType.NAME, false));
    public static final Action SORT_CATEGORIES_ID = new Action ("Sort by ID", SORT_CATEGORIES,null,new SortCategoriesCommand(CategoryArgType.ID, false));
    public static final Action SORT_CATEGORIES_BUDGET = new Action ("Sort by Budget", SORT_CATEGORIES, null,new SortCategoriesCommand(CategoryArgType.BUDGET, false));
    public static final Action SORT_CATEGORIES_CREATED = new Action ("Sort by Date Created", SORT_CATEGORIES, null,new SortCategoriesCommand(CategoryArgType.CREATED, false));

    // Transactions
    public static final Menu TRANSACTIONS = new Menu("Transactions", MAIN, "icons/transactions.png").withLoadSound(Sound.TRANSACTION);

    public static final Action LIST_TRANSACTIONS = new Action("List All Transactions", TRANSACTIONS, "icons/view_all.png", (Consumer<App>) app -> app.sendMessage(app.getTransactionCache().getReport()));
    public static final Menu UPDATE_TRANSACTION = new Menu("Update a Transaction", TRANSACTIONS, "icons/button_update_0.png");
    public static final Menu SORT_TRANSACTIONS= new Menu ("Sort Transactions", TRANSACTIONS, "icons/sort.png");
    
    public static final Action SORT_TRANSACTIONS_NAME = new Action("Sort by Name",SORT_TRANSACTIONS,null, new SortTransactionsCommand(TransactionArgType.NAME, false));
    public static final Action SORT_TRANSACTIONS_ID = new Action ("Sort by ID", SORT_TRANSACTIONS,null,new SortTransactionsCommand(TransactionArgType.ID, false));
    public static final Action SORT_TRANSACTIONS_CATEGORY = new Action ("Sort by Category", SORT_TRANSACTIONS, null,new SortTransactionsCommand(TransactionArgType.CATEGORY, false));
    public static final Action SORT_TRANSACTIONS_BUDGET = new Action ("Sort by Budget", SORT_TRANSACTIONS, null,new SortTransactionsCommand(TransactionArgType.AMOUNT, false));
    public static final Action SORT_TRANSACTIONS_CREATED = new Action ("Sort by Date Created", SORT_TRANSACTIONS, null,new SortTransactionsCommand(TransactionArgType.CREATED, false));
    public static final Action SORT_TRANSACTIONS_VENDOR = new Action ("Sort by Vendor", SORT_TRANSACTIONS, null,new SortTransactionsCommand(TransactionArgType.VENDOR, false));


    // Subscriptions
    public static final Menu SUBSCRIPTIONS = new Menu("Subscriptions", MAIN, "icons/subscriptions.png").withLoadSound(Sound.SUBSCRIPTION);

    public static final Action LIST_SUBSCRIPTIONS = new Action("List All Subscriptions", SUBSCRIPTIONS, "icons/view_all.png", (Consumer<App>) app -> app.sendMessage(app.getSubscriptionCache().getReport()));
    public static final Menu UPDATE_SUBSCRIPTION = new Menu("Update a Subscription", SUBSCRIPTIONS, "icons/button_update_0.png");
    public static final Menu SORT_SUBSCRIPTIONS= new Menu ("Sort Subscriptions", SUBSCRIPTIONS, "icons/sort.png");
    public static final Action SORT_SUBSCRIPTIONS_NAME = new Action("Sort by Name",SORT_SUBSCRIPTIONS,null, new SortSubscriptionsCommand(SubscriptionArgType.NAME, false));
    public static final Action SORT_SUBSCRIPTIONS_ID = new Action ("Sort by ID", SORT_SUBSCRIPTIONS,null,new SortSubscriptionsCommand(SubscriptionArgType.ID, false));
    public static final Action SORT_SUBSCRIPTIONS_CATEGORY = new Action ("Sort by Category", SORT_SUBSCRIPTIONS, null,new SortSubscriptionsCommand(SubscriptionArgType.CATEGORY, false));
    public static final Action SORT_SUBSCRIPTIONS_BUDGET = new Action ("Sort by Budget", SORT_SUBSCRIPTIONS, null,new SortSubscriptionsCommand(SubscriptionArgType.AMOUNT, false));
    public static final Action SORT_SUBSCRIPTIONS_CREATED = new Action ("Sort by Date Created", SORT_SUBSCRIPTIONS, null,new SortSubscriptionsCommand(SubscriptionArgType.CREATED, false));
    public static final Action SORT_SUBSCRIPTIONS_VENDOR = new Action ("Sort by Vendor", SORT_SUBSCRIPTIONS, null,new SortSubscriptionsCommand(SubscriptionArgType.VENDOR, false));

    // Authenticate
    public static final Menu AUTH_MENU = new Menu("Authenticate a Bank Account", MAIN, "icons/authenticate_monzo.png");

    // Monzo
    public static final Menu MONZO = new Menu("Monzo", AUTH_MENU, null);

    public static final Action MONZO_AUTH = new Action("Authenticate", MONZO, null, new MonzoAuthCommand());
    public static final Action MONZO_LIST_ACCOUNTS = new Action("List Accounts", MONZO, null, new MonzoGetAccountsCommand());
    public static final Action MONZO_GET_TRANSACTIONS = new Action("List Transactions", MONZO, null, new MonzoGetTransactionsCommand());
    public static final Action MONZO_CHECK_AUTH = new Action("Check Authentication", MONZO, null, new MonzoCheckAuthCommand());


    public static void addExitAction(Menu parent) {
        new Action("Exit the Application", parent, "icons/exit.png", App::exit);
    }

    public static void addBackAction(Menu parent) {
        new Action("Go Back", parent, "icons/go_back.png", (Consumer<App>) app -> app.render(parent.getParent()));
    }
}
