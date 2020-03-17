package me.csed2.moneymanager.transactions.menu.cmdline.stage;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionCache;
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
        CategoryCache repository = CategoryCache.getInstance();
        String result = (String) stages.get(0).getResult();

        if (result.equalsIgnoreCase("ALL")) {
            for (Transaction transaction : TransactionCache.getInstance().asList()) {
                System.out.println(transaction.toFormattedString());
                openPreviousMenu();
            }
        } else {
            Category category = repository.readByName(result);

            if (category != null) {
                try {

                    List<Transaction> transactions = TransactionCache.getInstance().readByCategory(result);

                    for (Transaction transaction : transactions) {
                        System.out.println(transaction.toFormattedString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                openPreviousMenu();
            } else {
                System.out.println("Error: Unable to find this category!");
                restart();
            }
        }
    }
}
