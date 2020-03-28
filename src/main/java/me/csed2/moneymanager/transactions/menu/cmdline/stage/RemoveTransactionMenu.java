package me.csed2.moneymanager.transactions.menu.cmdline.stage;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.commands.RemoveTransactionCommand;
import me.csed2.moneymanager.transactions.menu.cmdline.TransactionMenu;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;

public class RemoveTransactionMenu extends StageMenu {

    public RemoveTransactionMenu(TransactionMenu menu) {
        super("Remove Transaction", menu, image);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which transaction would you like to remove?"));
    }

    @Override
    public void exitPhase() {
        String transactionName = (String) stages.get(0).getResult();

        if (CommandDispatcher.dispatchSync(new RemoveTransactionCommand(transactionName))) {
            System.out.println("Transaction successfully removed!");
            openPreviousMenu();
        } else {
            System.out.println("Error: Unable to remove transaction!");
            restart();
        }
    }
}
