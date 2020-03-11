package me.csed2.moneymanager.categories.menu.cmdline.step;

import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.step.Step;
import me.csed2.moneymanager.ui.cmdline.step.StepMenu;

public class RemoveCategoryMenu extends StepMenu {

    public RemoveCategoryMenu(Menu menu) {
        super("Remove Category", menu);
    }

    @Override
    public void addSteps() {
        addStep(new Step<>(String.class, "Which category would you like to remove?"));
    }

    @Override
    public void beginPhase() {
        CategoryRepository.getInstance().printNames();
    }

    @Override
    public void exitPhase() {

        String name = (String) steps.get(0).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(new RemoveCategoryCommand(name))) {

            System.out.println("Removed category " + name + "!");
            openPreviousMenu();

        } else {
            System.out.println("Unable to find category " + name + "!");
            restart();
        }

    }
}
