package me.csed2.moneymanager.ui;

import me.csed2.moneymanager.budget.commands.OverallBudget;
import me.csed2.moneymanager.budget.commands.SeeBudgets;
import me.csed2.moneymanager.budget.autoCommands.BudgetTracker;
import me.csed2.moneymanager.budget.commands.UpdateOverallBudget;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.commands.AddCategoryCommand;
import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.categories.commands.UpdateCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.settings.UpdateSettingsCommand;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.commands.AddSubscriptionCommand;
import me.csed2.moneymanager.subscriptions.commands.RemoveSubscriptionCommand;
import me.csed2.moneymanager.subscriptions.commands.UpdateSubscriptionCommand;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.AddTransactionCommand;
import me.csed2.moneymanager.transactions.commands.RemoveTransactionCommand;
import me.csed2.moneymanager.transactions.commands.UpdateTransactionCommand;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;
import me.csed2.moneymanager.ui.model.StageMenuBuilder;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StageMenuList {

    //Budget
    public static final StageMenu MODIFY_OVERALL = new StageMenuBuilder("Update The Overall Budget")
            .withParent(MenuList.BUDGET)
            .withImage(null)
            .withStages(
                    new Stage<>(Integer.class, "What would you like to set the Overall Budget to?"))
            .withExitPhase((app, stages) ->{

                Integer result = (Integer) stages.get(0).getResult();

                if (CommandDispatcher.dispatchSync(new UpdateOverallBudget(result))) {
                    app.sendMessage("Successfully updated the Budget for Overall");
                } else {
                    app.sendMessage("Error: Unable to update this category!");
                }
                BudgetTracker.trackCheck("Overall", new Date().getMonth() -1);
            })
            .build();

    public static final StageMenu SEE_BUDGET = new StageMenuBuilder("Check A Budget")
            .withParent(MenuList.BUDGET)
            .withImage(null)
            .withStages(
                    new Stage<>(String.class, "What is the name of the budget you would like to check?"),
                    new Stage<>(Integer.class, "What is the month you would like to check this budget for"))

            .withExitPhase((app, stages) -> {
                String name = (String) stages.get(0).getResult();
                int month = (Integer) stages.get(1).getResult();

                CommandDispatcher.dispatchSync(new SeeBudgets(name, month-1));
            })
            .build();

    public static final StageMenu SEE_OVERALL_BUDGET = new StageMenuBuilder("Check Overall Budget")
            .withParent(MenuList.BUDGET)
            .withImage(null)
            .withStages(
                    new Stage<>(Integer.class, "What is the month you would like to check this budget for"))

            .withExitPhase((app, stages) -> {
                int month = (Integer) stages.get(0).getResult() -1;

                CommandDispatcher.dispatchSync(new OverallBudget(month));
            })
            .build();

    public static final StageMenu UPDATE_CATEGORY_BUDGET = new StageMenuBuilder("Update A Categories Budget")
            .withParent(MenuList.BUDGET)
            .withStages(
                    new Stage<>(String.class, "What category would you like to change the budget for?"),
                    new Stage<>(Integer.class, "What would you like to change the budget to?"))
            .withExitPhase((app, stages) -> {

                String categoryName = (String) stages.get(0).getResult();
                Integer result = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(new UpdateCategoryCommand<>(categoryName, CategoryArgType.BUDGET, result))) {
                    app.sendMessage("Successfully updated the name for this category!");
                } else {
                    app.sendMessage("Error: Unable to update this category!");
                }
                BudgetTracker.trackCheck(categoryName, new Date().getMonth() -1);
            })
            .build();


    // Categories
    public static final StageMenu ADD_CATEGORY = new StageMenuBuilder("Add a Category")
            .withParent(MenuList.CATEGORIES)
            .withImage("icons/button_add_0.png")
            .withStages(
                    new Stage<>(String.class, "What is the name of the new category?"),
                    new Stage<>(Integer.class, "What is the budget you would like to set for this category?"))
            .withExitPhase((app, stages) -> {

                String name = (String) stages.get(0).getResult();
                Integer budget =  (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(new AddCategoryCommand(name, budget))) {
                    app.sendMessage("Category successfully added!");
                }
                BudgetTracker.loadBudgetStore();
            })
            .build();

    public static final StageMenu REMOVE_CATEGORY = new StageMenuBuilder("Remove a Category")
            .withParent(MenuList.CATEGORIES)
            .withImage("icons/button_delete_0.png")
            .withStages(
                    new Stage<>(String.class, "What is the name of the new category?"))
            .withExitPhase((app, stages) -> {

                String name = (String) stages.get(0).getResult();

                if (CommandDispatcher.dispatchSync(new RemoveCategoryCommand(name))) {
                    app.sendMessage("Category successfully added!");
                }
                BudgetTracker.loadBudgetStore();
            })

            .build();

    public static final StageMenu UPDATE_CATEGORY_NAME = new StageMenuBuilder("Update a Categories Name")
            .withParent(MenuList.UPDATE_CATEGORY)
            .withStages(
                    new Stage<>(String.class, "What category would you like to change the name for?"),
                    new Stage<>(String.class, "What would you like to change the name to?"))
            .withExitPhase((app, stages) -> {

                String categoryName = (String) stages.get(0).getResult();
                String result = (String) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(new UpdateCategoryCommand<>(categoryName, CategoryArgType.NAME, result))) {
                    app.sendMessage("Successfully updated the name for this category!");
                } else {
                    app.sendMessage("Error: Unable to update this category!");
                }
                BudgetTracker.trackCheck(categoryName, new Date().getMonth() -1);
            })
            .build();


    public static final StageMenu UPDATE_BUDGET = new StageMenuBuilder("Update a Categories Budget")
            .withParent(MenuList.UPDATE_CATEGORY)
            .withStages(
                    new Stage<>(String.class, "What category would you like to change the budget for?"),
                    new Stage<>(Double.class, "What would you like to change the budget to?"))
            .withExitPhase((app, stages) -> {

                String categoryName = (String) stages.get(0).getResult();
                Double result = (Double) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(new UpdateCategoryCommand<>(categoryName, CategoryArgType.BUDGET, result))) {
                    app.sendMessage("Successfully updated the name for this category!");
                } else {
                    app.sendMessage("Error: Unable to update this category!");
                }
                BudgetTracker.trackCheck(categoryName, new Date().getMonth() -1);
            })
            .build();

    public static final StageMenu SEARCH_CATEGORIES = new StageMenuBuilder("Search Categories")
            .withParent(MenuList.CATEGORIES)
            .withStages(
                    new Stage<>(String.class,"What would you like to search for?")
            )
            .withExitPhase((app, stages) -> {
                String searchTerm = (String) stages.get(0).getResult();
                CachedList<Category> items =app.getCategoryCache().searchMatching(searchTerm);
                app.sendMessage(items.getReport());
            }).build();

    // Transactions
    public static final StageMenu ADD_TRANSACTION = new StageMenuBuilder("Add a Transaction")
            .withParent(MenuList.TRANSACTIONS)
            .withImage("icons/button_add_0.png")
            .withStages(
                    new Stage<>(String.class, "What is the name of the category you'd like to add the transaction to?"),
                    new Stage<>(String.class, "What is the name of the transaction>"),
                    new Stage<>(Double.class, "How much was spent here?"),
                    new Stage<>(String.class, "Where did you spend this money?"),
                    new Stage<>(String.class, "Do you have any notes about this transaction?", "Please split all notes you have with: \", \""))
            .withExitPhase((app, stages) -> {

                String categoryName = (String) stages.get(0).getResult();
                String name = (String) stages.get(1).getResult();
                Integer amount = (Integer) stages.get(2).getResult();
                String vendor = (String) stages.get(3).getResult();
                String[] notes = ((String) stages.get(4).getResult()).split(",");

                if (CommandDispatcher.dispatchSync(new AddTransactionCommand(categoryName, name, amount, vendor, notes))) {
                    app.sendMessage("Transaction successfully added!");
                } else {
                    app.sendMessage("Error: Unable to add transaction!");
                }
                BudgetTracker.trackCheck(categoryName, new Date().getMonth() -1);

            })
            .build();

    public static final StageMenu REMOVE_TRANSACTION = new StageMenuBuilder("Remove a Transaction")
            .withParent(MenuList.TRANSACTIONS)
            .withImage("icons/button_delete_0.png")
            .withStages(new Stage<>(String.class, "Which transaction would you like to remove?"))
            .withExitPhase((app, stages) -> {

                String name = (String) stages.get(0).getResult();

                if (CommandDispatcher.dispatchSync(new RemoveTransactionCommand(name))) {
                    app.sendMessage("Transaction successfully removed!");
                } else {
                    app.sendMessage("Error: Unable to remove transaction!");
                }
                BudgetTracker.trackCheck(App.getInstance().getTransactionCache().search(name).get().getCategory(), new Date().getMonth() -1);
            })
            .build();

    public static final StageMenu UPDATE_TRANSACTION_AMOUNT = new StageMenuBuilder("Update Transaction Amount")
            .withParent(MenuList.UPDATE_TRANSACTION)
            .withStages(
                    new Stage<>(String.class, "Which transaction would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the amount to?"))
            .withExitPhase((app, stages) -> {
                String transactionName = (String) stages.get(0).getResult();
                int amount = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(
                        new UpdateTransactionCommand<>(transactionName, TransactionArgType.AMOUNT, amount))) {
                    app.sendMessage("Transaction successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update transaction!");
                }
                BudgetTracker.trackCheck(App.getInstance().getTransactionCache().search(transactionName).get().getCategory(), new Date().getMonth() -1);
            })
            .build();

    public static final StageMenu UPDATE_TRANSACTION_VENDOR = new StageMenuBuilder("Update Transaction Vendor")
            .withParent(MenuList.UPDATE_TRANSACTION)
            .withStages(
                    new Stage<>(String.class, "Which transaction would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the vendor to?"))
            .withExitPhase((app, stages) -> {
                
                String transactionName = (String) stages.get(0).getResult();
                int vendor = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(
                        new UpdateTransactionCommand<>(transactionName, TransactionArgType.VENDOR, vendor))) {
                    app.sendMessage("Transaction successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update transaction!");
                }
            })
            .build();

    public static final StageMenu UPDATE_TRANSACTION_NAME = new StageMenuBuilder("Update Transaction Name")
            .withParent(MenuList.UPDATE_TRANSACTION)
            .withStages(
                    new Stage<>(String.class, "Which transaction would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the name to?"))
            .withExitPhase((app, stages) -> {
                
                String transactionName = (String) stages.get(0).getResult();
                int name = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(
                        new UpdateTransactionCommand<>(transactionName, TransactionArgType.NAME, name))) {
                    app.sendMessage("Transaction successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update transaction!");
                }
            })
            .build();
    
    
    // Subscriptions
    public static final StageMenu ADD_SUBSCRIPTION = new StageMenuBuilder("Add a Subscription")
            .withParent(MenuList.SUBSCRIPTIONS)
            .withImage("icons/button_add_0.png")
            .withStages(
                    new Stage<>(String.class, "What is the name of the category you'd like to add the subscription to?"),
                    new Stage<>(String.class, "What is the name of the subscription?"),
                    new Stage<>(Double.class, "How much is it per renewal?(£)"),
                    new Stage<>(String.class, "Who provides this service?"),
                    new Stage<>(Integer.class, "How frequently does this renew?"),
                    new Stage<>(String.class, "days/months/years"),
                    new Stage<>(String.class, "Would you like to be notified when this subscription renews? (y/n)"),
                    new Stage<>(String.class, "Date of commencement(DD/MM/YY)"),
                    new Stage<>(String.class, "Do you have any notes about this subscription?", "Please split all notes you have with: \", \""))
            .withExitPhase((app, stages) -> {

                String categoryName = (String) stages.get(0).getResult();
                String name = (String) stages.get(1).getResult();
                int amount = (int)(double)((Double)stages.get(2).getResult());
                String vendor = (String) stages.get(3).getResult();
                int timeCycle = (Integer) stages.get(4).getResult();
                String timeCycleUnit = (String) stages.get(5).getResult();
                String cancelMe = (String) stages.get(6).getResult();
                String commencement=(String) stages.get(7).getResult();
                String[] notes = ((String) stages.get(8).getResult()).split(",");

                if (CommandDispatcher.dispatchSync(new AddSubscriptionCommand(categoryName, name, amount, vendor, timeCycle, timeCycleUnit, notes, cancelMe, commencement))) {
                    app.sendMessage("Subscription successfully added!");
                } else {
                    app.sendMessage("Error: Unable to add subscription!");
                }
                BudgetTracker.trackCheck(categoryName, new Date().getMonth() -1);
            })
            .build();

    public static final StageMenu REMOVE_SUBSCRIPTION = new StageMenuBuilder("Remove a Subscription")
            .withParent(MenuList.SUBSCRIPTIONS)
            .withImage("icons/button_delete_0.png")
            .withStages(new Stage<>(String.class, "Which subscription would you like to remove?"))
            .withExitPhase((app, stages) -> {

                String name = (String) stages.get(0).getResult();

                String category = App.getInstance().getSubscriptionCache().search(name).get().getCategory();

                if (CommandDispatcher.dispatchSync(new RemoveSubscriptionCommand(name))) {
                    app.sendMessage("Subscription successfully removed!");
                } else {
                    app.sendMessage("Error: Unable to remove subscription!");
                }
                BudgetTracker.trackCheck(category, new Date().getMonth() -1);
            })
            .build();
    
    public static final StageMenu UPDATE_SUBSCRIPTION_AMOUNT = new StageMenuBuilder("Update Subscription Amount")
            .withParent(MenuList.UPDATE_SUBSCRIPTION)
            .withStages(
                    new Stage<>(String.class, "Which subscription would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the amount to? (Please separate all amount with a \", \""))
            .withExitPhase((app, stages) -> {
                
                String subscriptionName = (String) stages.get(0).getResult();
                int amount = (Integer) stages.get(1).getResult();

                String category = App.getInstance().getSubscriptionCache().search(subscriptionName).get().getCategory();

                if (CommandDispatcher.dispatchSync(
                        new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.AMOUNT, amount))) {
                    app.sendMessage("Subscription successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update subscription!");
                }
                BudgetTracker.trackCheck(category, new Date().getMonth() -1);
            })
            .build();

    public static final StageMenu UPDATE_SUBSCRIPTION_VENDOR = new StageMenuBuilder("Update Subscription Vendor")
            .withParent(MenuList.UPDATE_SUBSCRIPTION)
            .withStages(
                    new Stage<>(String.class, "Which subscription would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the vendor to?"))
            .withExitPhase((app, stages) -> {

                String subscriptionName = (String) stages.get(0).getResult();
                int vendor = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(
                        new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.VENDOR, vendor))) {
                    app.sendMessage("Subscription successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update subscription!");
                }
            })
            .build();

    public static final StageMenu UPDATE_SUBSCRIPTION_NAME = new StageMenuBuilder("Update Subscription Name")
            .withParent(MenuList.UPDATE_SUBSCRIPTION)
            .withStages(
                    new Stage<>(String.class, "Which subscription would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the name to?"))
            .withExitPhase((app, stages) -> {

                String subscriptionName = (String) stages.get(0).getResult();
                int name = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(
                        new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.NAME, name))) {
                    app.sendMessage("Subscription successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update subscription!");
                }
            })
            .build();

    public static final StageMenu UPDATE_SUBSCRIPTION_NOTES = new StageMenuBuilder("Update Subscription Notes")
            .withParent(MenuList.UPDATE_SUBSCRIPTION)
            .withStages(
                    new Stage<>(String.class, "Which subscription would you like to update?"),
                    new Stage<>(Double.class, "What would you like to update the notes to? (Please separate all notes with a \", \""))
            .withExitPhase((app, stages) -> {

                String subscriptionName = (String) stages.get(0).getResult();
                int notes = (Integer) stages.get(1).getResult();

                if (CommandDispatcher.dispatchSync(
                        new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.NOTES, notes))) {
                    app.sendMessage("Subscription successfully updated!");
                } else {
                    app.sendMessage("Error: Unable to update subscription!");
                }
            })
            .build();

    public static final StageMenu SETTINGS = new StageMenuBuilder("Settings")
            .withParent(MenuList.MAIN)
            .withStages(
                    new Stage<>(String.class, "Renderer CMD: Command prompt, Blank: UI:"),
                    new Stage<>(String.class, "Test Settings \", \""))
            .withExitPhase((app, stages) -> {

                String renderer = (String) stages.get(0).getResult();
                String test = (String) stages.get(1).getResult();
                if (new UpdateSettingsCommand(renderer, test).update()){
                    app.sendMessage("Success");
                }else{
                    app.sendMessage("Error");

                }
            })
            .build();
}
