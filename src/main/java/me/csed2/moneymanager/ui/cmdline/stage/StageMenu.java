package me.csed2.moneymanager.ui.cmdline.stage;

import lombok.Getter;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of a step menu.
 */
public abstract class StageMenu implements Menu {

    @Getter
    private final String name;

    @Getter
    protected List<Stage<?>> stages;

    @Getter
    protected final Menu previousMenu;

    private int count = 0;

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
     */
    public StageMenu(String name, Menu previousMenu) {
        this.name = name;
        this.stages = new ArrayList<>();
        this.previousMenu = previousMenu;

        addStages();
        System.out.println("Please type \"EXIT\" if you'd like to exit this menu!");
        beginPhase();
    }

    @Override
    public void print() {
        stages.get(0).executionPhase();
        stages.get(0).print();
    }

    public abstract void addStages();

    public abstract void exitPhase();

    public void beginPhase() {

    }

    public void addStage(Stage<?> stage) {
        stages.add(stage);
    }

    public void nextStage() {
        count++;
        if (count >= stages.size()) {
            exitPhase();
        } else {
            Stage<?> nextStage = stages.get(count);
            nextStage.executionPhase();
            nextStage.print();
        }
    }

    public Stage<?> currentStage() {
        return stages.get(count);
    }

    public void openPreviousMenu() {
        User.getInstance().openMenu(previousMenu);
    }

    public void restart() {
        count = 0;
        beginPhase();
        System.out.println("Please type \"EXIT\" if you'd like to exit this menu!");
        print();
    }
}
