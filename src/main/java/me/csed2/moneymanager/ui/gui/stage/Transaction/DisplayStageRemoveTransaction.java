package me.csed2.moneymanager.ui.gui.stage.Transaction;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.commands.RemoveTransactionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageRemoveTransaction extends DisplayStageMenu {

    public DisplayStageRemoveTransaction(){
        super(300, 300, "Remove Transaction", TRANSACTION);
    }

    @Override
    protected void beginPhase() {

    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which transaction would you like to remove?"));
    }

    @Override
    public void exitPhase() {
        String transactionName = (String) stages.get(0).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(new RemoveTransactionCommand(transactionName))) {
            showMessage("Transaction successfully removed!");
        } else {
            showMessage("Error: Unable to remove transaction!");
        }
        openPreviousMenu();
    }
}
