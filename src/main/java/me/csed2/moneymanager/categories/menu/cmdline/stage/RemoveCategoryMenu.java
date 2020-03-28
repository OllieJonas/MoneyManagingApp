package me.csed2.moneymanager.categories.menu.cmdline.stage;

import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;

public class RemoveCategoryMenu extends StageMenu {

    public RemoveCategoryMenu(Menu menu) {
        super("Remove Category", menu, image);
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

            System.out.println("Removed category " + name + "!");
            openPreviousMenu();

        } else {
            System.out.println("Unable to find category " + name + "!");
            restart();
        }

    }
}
