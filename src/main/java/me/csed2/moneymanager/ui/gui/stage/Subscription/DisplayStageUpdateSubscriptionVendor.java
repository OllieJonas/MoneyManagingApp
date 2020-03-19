package me.csed2.moneymanager.ui.gui.stage.Subscription;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.commands.UpdateSubscriptionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateSubscriptionVendor extends DisplayStageMenu {

    public DisplayStageUpdateSubscriptionVendor(){
        super(300, 300, "Update Subscription Vendor", UPDATE_TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "Which category is the subscription in?"));
        addStage(new Stage<>(String.class, "Which subscription would you like to update?"));
        addStage(new Stage<>(String.class, "What would you like to change the vendor to?"));
    }

    @Override
    protected void exitPhase() {
        String categoryName = (String) stages.get(0).getResult();
        String subscriptionName = (String) stages.get(1).getResult();
        String vendor = (String) stages.get(2).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(
                new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.VENDOR, vendor))) {
             showMessage("Subscription successfully updated!");
        } else {
            showMessage("Error: Unable to update subscription!");
        }
        openPreviousMenu();
    }
}
