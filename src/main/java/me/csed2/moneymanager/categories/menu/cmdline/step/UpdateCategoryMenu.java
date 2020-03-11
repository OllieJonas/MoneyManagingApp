package me.csed2.moneymanager.categories.menu.cmdline.step;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.step.Step;
import me.csed2.moneymanager.ui.cmdline.step.StepMenu;

public class UpdateCategoryMenu extends StepMenu {

    public UpdateCategoryMenu(Menu parent) {
        super("Update Category", parent);
    }

    @Override
    public void addSteps() {
        addStep(new Step<>(String.class, "What category would you like to update?"));
        addStep(new Step<>(String.class, "Budget, ", "Which part of the category would you like to update?"));
    }

    @Override
    public void exitPhase() {

    }
}
