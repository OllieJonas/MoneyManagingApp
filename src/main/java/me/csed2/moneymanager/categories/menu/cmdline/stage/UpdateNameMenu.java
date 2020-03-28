package me.csed2.moneymanager.categories.menu.cmdline.stage;

import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.commands.UpdateCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;

public class UpdateNameMenu extends StageMenu {

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public UpdateNameMenu(Menu previousMenu) {
        super("Update Name", previousMenu, image);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "What category would you like to change the name for?"));
        addStage(new Stage<>(String.class, "What would you like to change the name to?"));
    }

    @Override
    public void exitPhase() {
        String categoryName = (String) stages.get(0).getResult();
        String result = (String) stages.get(1).getResult();

        if (CommandDispatcher.dispatchSync(new UpdateCategoryCommand<>(categoryName, CategoryArgType.NAME, result))) {
            System.out.println("Successfully updated the name of this category!");
        } else {
            System.out.println("Error: Unable to update this category!");
        }
        openPreviousMenu();
    }
}
