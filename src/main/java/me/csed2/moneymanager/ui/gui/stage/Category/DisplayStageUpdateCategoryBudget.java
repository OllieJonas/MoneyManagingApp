package me.csed2.moneymanager.ui.gui.stage.Category;

import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.commands.UpdateCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

public class DisplayStageUpdateCategoryBudget extends DisplayStageMenu {

    public DisplayStageUpdateCategoryBudget(){
        super(300, 300, "Update Category Budget", UPDATE_CATEGORY);
    }

    @Override
    protected void beginPhase() {

    }

    @Override
    protected void addStages() {
        addStage(new Stage<>(String.class, "What category would you like to change the budget for?"));
        addStage(new Stage<>(Double.class, "What would you like to change the budget to?"));
    }

    @Override
    protected void exitPhase() {
        String name = (String) stages.get(0).getResult();

        int newBudget = (int) Math.round((Double) stages.get(1).getResult() * 100);

        if (CommandDispatcher.getInstance().dispatchSync(new UpdateCategoryCommand<>(name, CategoryArgType.BUDGET, newBudget))) {
            showMessage("Successfully updated the budget!");
        } else {
            showMessage("Error: Unable to update this category!");
        }
        openPreviousMenu();

    }
}
