package me.csed2.moneymanager.ui.gui.stage.Subscription;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.commands.RemoveSubscriptionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageRemoveSubscription extends DisplayStageMenu{

    public DisplayStageRemoveSubscription(){
        super(300, 300, "Remove Subscription", TRANSACTION);
    }

    @Override
    protected void beginPhase() {

    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which subscription would you like to remove?"));
    }

    @Override
    public void exitPhase() {
        String subscriptionName = (String) stages.get(0).getResult();

        if (CommandDispatcher.dispatchSync(new RemoveSubscriptionCommand(subscriptionName))) {
            showMessage("Subscription successfully removed!");
        } else {
            showMessage("Error: Unable to remove subscription!");
        }
        openPreviousMenu();
    }
}
