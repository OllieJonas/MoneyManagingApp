package me.csed2.moneymanager.ui.cmdline.step;

import lombok.Getter;
import me.csed2.moneymanager.ui.Menu;

import java.util.ArrayList;
import java.util.List;

public abstract class StepMenu implements Menu {

    @Getter
    private String name;

    @Getter
    protected List<Step<?>> steps;

    private int count = 0;

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
     */
    public StepMenu(String name) {
        this.name = name;
        this.steps = new ArrayList<>();

        addSteps();
    }

    @Override
    public void print() {
        steps.get(0).print();
    }

    public abstract void addSteps();

    public abstract void exitPhase();

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
}
