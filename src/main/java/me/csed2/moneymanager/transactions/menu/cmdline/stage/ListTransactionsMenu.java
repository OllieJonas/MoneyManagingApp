package me.csed2.moneymanager.transactions.menu.cmdline.stage;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

public class ListTransactionsMenu extends StageMenu {
    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
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
        CategoryRepository repository = CategoryRepository.getInstance();
        String result = (String) stages.get(0).getResult();

        Category category = repository.readByName(result);

        if (category != null) {
            category.printTransactions();
            openPreviousMenu();
        } else {
            System.out.println("Error: Unable to find this category!");
            restart();
        }

    }
}
