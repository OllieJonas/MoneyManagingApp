package me.csed2.moneymanager.ui.gui.stage;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;

public class DisplayStageListTransactions extends DisplayStageMenu{

    public DisplayStageListTransactions(){
        super(300, 300, "List Transactions", TRANSACTION);
    }

    @Override
    protected void beginPhase(){

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
        } else {
            System.out.println("Error: Unable to find this category!");
        }
        openPreviousMenu();
    }
}
