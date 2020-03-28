package me.csed2.moneymanager.ui.gui.stage.Subscription;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.commands.UpdateSubscriptionCommand;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateSubscriptionName extends DisplayStageMenu {

    public DisplayStageUpdateSubscriptionName(){
        super(300, 300, "Update Subscription Name", UPDATE_TRANSACTION);
    }

    @Override
    protected void beginPhase() {

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "What category would you like to change the name for?"));
        addStage(new Stage<>(String.class, "What would you like to change the name to?"));
    }

    @Override
    protected void exitPhase() {
        String subscriptionName = (String) stages.get(0).getResult();
        String result = (String) stages.get(1).getResult();

        if (CommandDispatcher.dispatchSync(new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.NAME, result))) {
            showMessage("Successfully updated the name of this subscription!");
        } else {
            showMessage("Error: Unable to update this subscription!");
        }
        openPreviousMenu();
    }
}
