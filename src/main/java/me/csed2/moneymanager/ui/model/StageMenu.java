package me.csed2.moneymanager.ui.model;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of a step menu.
 */
public abstract class StageMenu implements UINode {

    @Getter
    protected final String name;

    @Getter
    protected final UINode parent;

    @Getter
    protected final Image image;

    @Getter
    protected List<Stage<?>> stages = new ArrayList<>();

    private int count = 0;

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
     * @param image
     */
    public StageMenu(String name, UINode parent, Image image) {
        this.name = name;
        this.parent = parent;
        this.image = image;


    }

    public StageMenu build() {
        addStages();
        return this;
    }

    public abstract void addStages();

    public abstract void exitPhase();

    public void beginPhase() {

    }

    // no children for StageMenu
    @Override
    public List<UINode> getChildren() {
        return null;
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

}
