package me.csed2.moneymanager.ui.gui.stage.Transaction;

import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.commands.UpdateCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.UpdateTransactionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateTransactionName extends DisplayStageMenu {

    public DisplayStageUpdateTransactionName(){
        super(300, 300, "Update Transaction Name", UPDATE_TRANSACTION);
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
        String transactionName = (String) stages.get(0).getResult();
        String result = (String) stages.get(1).getResult();

        if (CommandDispatcher.dispatchSync(new UpdateTransactionCommand<>(transactionName, TransactionArgType.NAME, result))) {
            showMessage("Successfully updated the name of this transaction!");
        } else {
            showMessage("Error: Unable to update this transaction!");
        }
        openPreviousMenu();
    }
}
