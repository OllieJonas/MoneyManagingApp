package me.csed2.moneymanager.ui.gui.stage.Subscription;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.commands.UpdateSubscriptionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateSubscriptionNotes extends DisplayStageMenu {

    public DisplayStageUpdateSubscriptionNotes(){
        super(300, 300, "Update Subscription Notes", UPDATE_TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "Which category is the subscription in?"));
        addStage(new Stage<>(String.class, "Which subscription would you like to update?"));
        addStage(new Stage<>(String.class, "What would you like to change the notes to? (Please separate all notes with a \",\""));
    }

    @Override
    protected void exitPhase() {
        String categoryName = (String) stages.get(0).getResult();
        String subscriptionName = (String) stages.get(1).getResult();
        String[] notes = ((String) stages.get(2).getResult()).split(",");

        if (CommandDispatcher.getInstance().dispatchSync(
                new UpdateSubscriptionCommand<>(subscriptionName, SubscriptionArgType.NOTES, notes))) {
            showMessage("Subscription successfully updated!");
        } else {
            showMessage("Error: Unable to update subscription!");
        }
        openPreviousMenu();
    }

}
