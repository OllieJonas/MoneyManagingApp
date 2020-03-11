package me.csed2.moneymanager.categories.menu.cmdline.step;

import me.csed2.moneymanager.categories.CategoryBuilder;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.categories.commands.AddCategoryCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.step.Step;
import me.csed2.moneymanager.ui.cmdline.step.StepMenu;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddCategoryMenu extends StepMenu {


    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public AddCategoryMenu(Menu previousMenu) {
        super("Add Category", previousMenu);
    }

    @Override
    public void addSteps() {
        addStep(new Step<>(String.class, "What is the name of the new category?"));
        addStep(new Step<>(Integer.class, "What is the budget you would like to set for this category?"));
    }

    @Override
    public void exitPhase() {
        String name = (String) steps.get(0).getResult();
        Integer budget = (Integer) steps.get(1).getResult();
        List<Transaction> transactions = new ArrayList<>();

        CommandDispatcher.getInstance().dispatchSync(new AddCategoryCommand(name, budget, transactions));

        openPreviousMenu();
    }
}
