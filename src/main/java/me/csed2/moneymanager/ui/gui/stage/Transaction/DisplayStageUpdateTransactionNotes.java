package me.csed2.moneymanager.ui.gui.stage.Transaction;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.UpdateTransactionCommand;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateTransactionNotes extends DisplayStageMenu {

    public DisplayStageUpdateTransactionNotes(){
        super(300, 300, "Update Transaction Notes", UPDATE_TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "Which category is the transaction in?"));
        addStage(new Stage<>(String.class, "Which transaction would you like to update?"));
        addStage(new Stage<>(String.class, "What would you like to change the notes to? (Please separate all notes with a \",\""));
    }

    @Override
    protected void exitPhase() {
        String categoryName = (String) stages.get(0).getResult();
        String transactionName = (String) stages.get(1).getResult();
        String[] notes = ((String) stages.get(2).getResult()).split(",");

        if (CommandDispatcher.dispatchSync(
                new UpdateTransactionCommand<>(transactionName, TransactionArgType.NOTES, notes))) {
            showMessage("Transaction successfully updated!");
        } else {
            showMessage("Error: Unable to update transaction!");
        }
        openPreviousMenu();
    }

}
