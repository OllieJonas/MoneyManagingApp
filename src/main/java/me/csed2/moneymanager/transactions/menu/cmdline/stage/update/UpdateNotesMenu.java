package me.csed2.moneymanager.transactions.menu.cmdline.stage.update;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.UpdateTransactionCommand;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

public class UpdateNotesMenu extends StageMenu {
    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public UpdateNotesMenu(Menu previousMenu) {
        super("Update Notes", previousMenu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which category is the transaction in?"));
        addStage(new Stage<>(String.class, "Which transaction would you like to update?"));
        addStage(new Stage<>(String.class, "What would you like to change the notes to? (Please separate all notes with a \",\""));
    }

    @Override
    public void exitPhase() {
        String categoryName = (String) stages.get(0).getResult();
        String transactionName = (String) stages.get(1).getResult();
        String[] notes = ((String) stages.get(2).getResult()).split(",");

        if (CommandDispatcher.dispatchSync(
                new UpdateTransactionCommand<>(transactionName, TransactionArgType.NOTES, notes))) {
            System.out.println("Transaction successfully updated!");
            openPreviousMenu();
        } else {
            System.out.println("Error: Unable to update transaction!");
            restart();
        }
    }
}
