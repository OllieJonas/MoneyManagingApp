package me.csed2.moneymanager.ui;

import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.commands.MonzoAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoCheckAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoGetAccountsCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoGetTransactionsCommand;
import me.csed2.moneymanager.ui.model.Action;
import me.csed2.moneymanager.ui.model.Menu;

import java.util.function.Consumer;

public class MenuList {

    public static final Menu MAIN = new Menu("Main Menu", null, null);

    // Categories
    public static final Menu CATEGORIES = new Menu("Categories", MAIN, null);

    public static final Action LIST_CATEGORIES = new Action("List All Categories", CATEGORIES, "icons/button_search_0.png", (Consumer<App>) app -> app.sendMessage(app.getCategoryCache().getReport()));
    public static final Menu UPDATE_CATEGORY = new Menu("Update a Category", CATEGORIES, "icons/button_update_0.png");


    // Transactions
    public static final Menu TRANSACTIONS = new Menu("Transactions", MAIN, null);

    public static final Action LIST_TRANSACTIONS = new Action("List All Transactions", TRANSACTIONS, "icons/button_search_0.png", (Consumer<App>) app -> app.sendMessage(app.getTransactionCache().getReport()));
    public static final Menu UPDATE_TRANSACTION = new Menu("Update a Transaction", TRANSACTIONS, "icons/button_update_0.png");


    // Subscriptions
    public static final Menu SUBSCRIPTIONS = new Menu("Subscriptions", MAIN, null);

    public static final Action LIST_SUBSCRIPTIONS = new Action("List All Subscriptions", SUBSCRIPTIONS, "icons/button_search_0.png", (Consumer<App>) app -> app.sendMessage(app.getSubscriptionCache().getReport()));
    public static final Menu UPDATE_SUBSCRIPTION = new Menu("Update a Subscription", SUBSCRIPTIONS, "icons/button_update_0.png");


    // Authenticate
    public static final Menu AUTH_MENU = new Menu("Authenticate a Bank Account", MAIN, null);

    // Monzo
    public static final Menu MONZO = new Menu("Monzo", AUTH_MENU, null);

    public static final Action MONZO_AUTH = new Action("Authenticate", MONZO, null, new MonzoAuthCommand());
    public static final Action MONZO_UPDATE = new Action("Update", MONZO, null, (Consumer<App>) app -> System.out.println("Coming soon TM"));
    public static final Action MONZO_LIST_ACCOUNTS = new Action("List Accounts", MONZO, null, new MonzoGetAccountsCommand());
    public static final Action MONZO_GET_TRANSACTIONS = new Action("List Transactions", MONZO, null, new MonzoGetTransactionsCommand());
    public static final Action MONZO_CHECK_AUTH = new Action("Check Authentication", MONZO, null, new MonzoCheckAuthCommand());
    public static final Action MONZO_PRINT_TOKEN = new Action("Print Access Token", MONZO, null, (Consumer<App>) app -> app.render(MonzoHttpClient.getAccessToken()));


    public static void decorateExitAction(Menu parent) {
        new Action("Exit the Application", parent, "images/exit_0", App::exit);
    }

    public static void decorateBackAction(Menu parent) {
        new Action("Go Back", parent, "images/back_0", (Consumer<App>) app -> app.render(parent.getParent()));
    }
}
