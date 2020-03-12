package me.csed2.moneymanager.ui.cmdline.stage;

import me.csed2.moneymanager.main.MainMenu;

public class TestStageMenu extends StageMenu {

    public TestStageMenu(MainMenu menu) {
        super("Test", menu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(Integer.class, "Please enter a number: "));
        addStage(new Stage<>(String.class, "Please enter a string: "));
    }

    @Override
    public void exitPhase() {
        System.out.println("Exited!");
        Integer result = (Integer) stages.get(0).getResult();
        System.out.println(result);

        openPreviousMenu();
    }
}
