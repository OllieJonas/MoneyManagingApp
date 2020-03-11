package me.csed2.moneymanager.ui.cmdline.step;

import lombok.Getter;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of a step menu.
 */
public abstract class StepMenu implements Menu {

    @Getter
    private String name;

    @Getter
    protected List<Step<?>> steps;

    protected Menu previousMenu;

    private int count = 0;

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
     */
    public StepMenu(String name, Menu previousMenu) {
        this.name = name;
        this.steps = new ArrayList<>();
        this.previousMenu = previousMenu;

        addSteps();
        beginPhase();
    }

    @Override
    public void print() {
        steps.get(0).print();
    }

    public abstract void addSteps();

    public abstract void exitPhase();

    public void beginPhase() {

    }

    public void addStep(Step<?> step) {
        steps.add(step);
    }

    public void nextStep() {
        count++;
        if (count >= steps.size()) {
            exitPhase();
        } else {
            Step<?> nextStep = steps.get(count);
            nextStep.print();
        }
    }

    public Step<?> currentStep() {
        return steps.get(count);
    }

    public void openPreviousMenu() {
        User.getInstance().openMenu(previousMenu);
    }

    public void restart() {
        count = 0;
        steps = new ArrayList<>();
        beginPhase();
        print();
    }
}
