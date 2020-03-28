package me.csed2.moneymanager.ui.gui.stage.Category;

import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

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
        App.getInstance().getCategoryCache().print();
    }

    @Override
    public void exitPhase() {

        String name = (String) stages.get(0).getResult();

        if (CommandDispatcher.dispatchSync(new RemoveCategoryCommand(name))) {

            showMessage("Removed category " + name + "!");
            openPreviousMenu();

        } else {
            showMessage("Unable to find category " + name + "!");
            openPreviousMenu();
        }

    }
}
