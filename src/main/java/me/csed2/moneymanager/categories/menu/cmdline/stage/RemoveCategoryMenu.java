package me.csed2.moneymanager.categories.menu.cmdline.stage;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

public class RemoveCategoryMenu extends StageMenu {

    public RemoveCategoryMenu(Menu menu) {
        super("Remove Category", menu);
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

            System.out.println("Removed category " + name + "!");
            openPreviousMenu();

        } else {
            System.out.println("Unable to find category " + name + "!");
            restart();
        }

    }
}
