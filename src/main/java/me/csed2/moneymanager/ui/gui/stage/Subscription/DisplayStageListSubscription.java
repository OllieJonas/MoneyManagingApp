package me.csed2.moneymanager.ui.gui.stage.Subscription;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

import javax.swing.*;
import java.util.Optional;

public class DisplayStageListSubscription extends DisplayStageMenu{

    public DisplayStageListSubscription(){
        super(300, 300, "List Subscriptions", TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which category would you like to list the names for?"));
    }

    @Override
    public void exitPhase() {
        CategoryCache cache = CategoryCache.getInstance();
        String result = (String) stages.get(0).getResult();

        Optional<Category> category = cache.search(result);

        if (category.isPresent()) {
            JOptionPane.showMessageDialog(null, getSubscriptionReport(result));
        } else {
            System.out.println("Error: Unable to find this category!");
        }
        openPreviousMenu();
    }

    private String getSubscriptionReport(String name){
        StringBuilder builder = new StringBuilder();

        for (Subscription subscription : SubscriptionCache.getInstance().search(category -> category.getName().equalsIgnoreCase(name))) {
            builder.append(subscription.toFormattedString()).append("\n");
        }

        return builder.toString();
    }
}
