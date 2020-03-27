package me.csed2.moneymanager.transactions.menu.cmdline.stage;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.commands.ListTransactionsCommand;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

import java.util.List;

public class ListTransactionsMenu extends StageMenu {

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public ListTransactionsMenu(Menu previousMenu) {
        super("List Transactions", previousMenu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which category would you like to list the names for?"));
    }

    @Override
    public void exitPhase() {
        String result = (String) stages.get(0).getResult();
        List<Transaction> transactions = CommandDispatcher.dispatchSync(new ListTransactionsCommand(result));

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toFormattedString());
        }

        openPreviousMenu();
    }
}
