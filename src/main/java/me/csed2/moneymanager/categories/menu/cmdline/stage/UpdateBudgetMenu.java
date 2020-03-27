package me.csed2.moneymanager.categories.menu.cmdline.stage;

import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.commands.UpdateCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

public class UpdateBudgetMenu extends StageMenu {


    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public UpdateBudgetMenu(Menu previousMenu) {
        super("Update Budget", previousMenu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "What category would you like to change the budget for?"));
        addStage(new Stage<>(Double.class, "What would you like to change the budget to?"));
    }

    @Override
    public void exitPhase() {
        String name = (String) stages.get(0).getResult();

        int newBudget = (int) Math.round((Double) stages.get(1).getResult() * 100);

        if (CommandDispatcher.dispatchSync(new UpdateCategoryCommand<>(name, CategoryArgType.BUDGET, newBudget))) {
            System.out.println("Successfully updated the budget!");
        } else {
            System.out.println("Error: Unable to update this category!");
        }
        openPreviousMenu();

    }
}
