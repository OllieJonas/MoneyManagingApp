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

    public static Menu MAIN = new Menu("Main Menu", null, null);

    // Categories
    public static Menu CATEGORIES = new Menu("Categories", MAIN, null);

    public static Action LIST_CATEGORIES = new Action("List All Categories", CATEGORIES, "icons/button_search_0.png", (Consumer<App>) app -> app.showMessage(app.getCategoryCache().getReport()));
    public static Menu ADD_CATEGORY = new Menu("Add a New Category", CATEGORIES, "icons/button_add_0.png");
    public static Menu REMOVE_CATEGORY = new Menu("Remove a Category", CATEGORIES, "icons/button_delete_0.png");
    public static Menu UPADTE_CATEGORY = new Menu("Update a Category", CATEGORIES, "icons/button_update_0.png");

    // Transactions
    public static Menu TRANSACTIONS = new Menu("Transactions", MAIN, null);

    public static Action LIST_TRANSACTIONS = new Action("List All Transactions", TRANSACTIONS, "icons/button_search_0.png", (Consumer<App>) app -> app.showMessage(app.getTransactionCache().getReport()));
    public static Menu ADD_TRANSACTION = new Menu("Add a New Transaction", TRANSACTIONS, "icons/button_add_0.png");
    public static Menu REMOVE_TRANSACTION = new Menu("Remove a Transaction", TRANSACTIONS, "icons/button_delete_0.png");
    public static Menu UPADTE_TRANSACTION = new Menu("Update a Transaction", TRANSACTIONS, "icons/button_update_0.png");

    // Subscriptions
    public static Menu SUBSCRIPTIONS = new Menu("Subscriptions", MAIN, null);

    public static Action LIST_SUBSCRIPTIONS = new Action("List All Subscriptions", SUBSCRIPTIONS, "icons/button_search_0.png", (Consumer<App>) app -> app.showMessage(app.getSubscriptionCache().getReport()));
    public static Menu ADD_SUBSCRIPTION = new Menu("Add a New Subscription", SUBSCRIPTIONS, "icons/button_add_0.png");
    public static Menu REMOVE_SUBSCRIPTION = new Menu("Remove a Subscription", SUBSCRIPTIONS, "icons/button_delete_0.png");
    public static Menu UPADTE_SUBSCRIPTION = new Menu("Update a Subscription", SUBSCRIPTIONS, "icons/button_update_0.png");

    // Monzo
    public static Menu AUTH_MENU = new Menu("Authenticate a Bank Account", MAIN, null);

    public static Menu MONZO = new Menu("Monzo", AUTH_MENU, null);

    public static Action MONZO_AUTH = new Action("Authenticate", MONZO, null, new MonzoAuthCommand());
    public static Action MONZO_UPDATE = new Action("Update", MONZO, null, (Consumer<App>) app -> System.out.println("Coming soon TM"));
    public static Action MONZO_LIST_ACCOUNTS = new Action("List Accounts", MONZO, null, new MonzoGetAccountsCommand());
    public static Action MONZO_GET_TRANSACTIONS = new Action("List Transactions", MONZO, null, new MonzoGetTransactionsCommand());
    public static Action MONZO_CHECK_AUTH = new Action("Check Authentication", MONZO, null, new MonzoCheckAuthCommand());
    public static Action MONZO_PRINT_TOKEN = new Action("Print Access Token", MONZO, null, (Consumer<App>) app -> System.out.println(MonzoHttpClient.getAccessToken()));

    public static Action exitAction(Menu parent) {
        return new Action("Exit the Application", parent, "images/exit_0", App::exit);
    }

    public static Action backAction(Menu parent) {
        return new Action("Go back", parent, "images/back_0", (Consumer<App>) app -> app.openMenu(parent));
    }
}
