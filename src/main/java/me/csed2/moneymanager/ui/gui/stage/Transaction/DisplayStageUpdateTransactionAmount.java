package me.csed2.moneymanager.ui.gui.stage.Transaction;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.UpdateTransactionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateTransactionAmount extends DisplayStageMenu {

    public DisplayStageUpdateTransactionAmount(){
        super(300, 300, "Update Transaction Amount", UPDATE_TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "Which transaction would you like to update?"));
        addStage(new Stage<>(Integer.class, "What would you like to change the amount to?"));
    }

    @Override
    protected void exitPhase() {
        String transactionName = (String) stages.get(0).getResult();
        int amount = (Integer) stages.get(1).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(
                new UpdateTransactionCommand<>(transactionName, TransactionArgType.AMOUNT, amount))) {
            showMessage("Transaction successfully updated!");
        } else {
            showMessage("Error: Unable to update transaction!");
        }
        openPreviousMenu();
    }
}
