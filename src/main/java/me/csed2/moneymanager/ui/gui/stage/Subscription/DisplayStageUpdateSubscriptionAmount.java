package me.csed2.moneymanager.ui.gui.stage.Subscription;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.commands.UpdateSubscriptionCommand;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateSubscriptionAmount extends DisplayStageMenu {

    public DisplayStageUpdateSubscriptionAmount(){
        super(300, 300, "Update Subscription Amount", UPDATE_TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "Which subscription would you like to update?"));
        addStage(new Stage<>(Integer.class, "What would you like to change the amount to?"));
    }

    @Override
    protected void exitPhase() {
        String subscriptionName = (String) stages.get(0).getResult();
        int amount = (Integer) stages.get(1).getResult();

        if (CommandDispatcher.dispatchSync(
                new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.AMOUNT, amount))) {
            showMessage("Subscription successfully updated!");
        } else {
            showMessage("Error: Unable to update subscription!");
        }
        openPreviousMenu();
    }
}
