package me.csed2.moneymanager.ui.gui.stage.Category;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.categories.commands.AddCategoryCommand;
import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

import java.util.ArrayList;
import java.util.List;

public class DisplayStageRemoveCategory extends DisplayStageMenu {

    public DisplayStageRemoveCategory(){
        super(300, 300, "Remove Category", CATEGORY);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which category would you like to remove?"));
    }

    @Override
    public void beginPhase() {
        CategoryCache.getInstance().printNames();
    }

    @Override
    public void exitPhase() {

        String name = (String) stages.get(0).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(new RemoveCategoryCommand(name))) {

            showMessage("Removed category " + name + "!");
            openPreviousMenu();

        } else {
            showMessage("Unable to find category " + name + "!");
            openPreviousMenu();
        }

    }
}
