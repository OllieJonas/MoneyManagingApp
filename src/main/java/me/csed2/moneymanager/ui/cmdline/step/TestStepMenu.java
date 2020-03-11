package me.csed2.moneymanager.ui.cmdline.step;

public class TestStepMenu extends StepMenu {

    public TestStepMenu() {
        super("Test", null);
    }

    @Override
    public void addSteps() {
        addStep(new Step<>(Integer.class, "Please enter a number: "));
        addStep(new Step<>(String.class, "Please enter a string: "));
    }

    @Override
    public void exitPhase() {
        System.out.println("Exited!");
        Integer result = (Integer) steps.get(0).getResult();
        System.out.println(result);
    }
}
