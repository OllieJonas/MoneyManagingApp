package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.main.App;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract implementation of a step menu.
 */
public class StageMenu implements UINode {

    @Getter
    protected final String name;

    @Getter
    protected final Menu parent;

    @Getter
    protected final String image;

    @Getter
    protected List<Stage<?>> stages;

    @Getter
    private Phase beginPhase;

    @Getter
    private Phase exitPhase;

    private int count = 0;

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
     * @param image
     */
    public StageMenu(String name, Menu parent, String image, List<Stage<?>> stages, Phase beginPhase, Phase exitPhase) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.stages = stages;
        this.beginPhase = beginPhase;
        this.exitPhase = exitPhase;

        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    public void beginPhase() {
        if (beginPhase != null) {
            beginPhase.execute(App.getInstance(), stages);
        }
    }

    public void exitPhase() {
        if (exitPhase != null) {
            exitPhase.execute(App.getInstance(), stages);
        }
    }
    // no children for StageMenu
    @Override
    public List<UINode> getChildren() {
        return null;
    }

    public void addStage(Stage<?> stage) {
        stages.add(stage);
    }

    public Stage<?> nextStage() {
        count++;
        if (count >= stages.size()) {
            exitPhase();
            return null;
        } else {
            Stage<?> nextStage = stages.get(count);
            nextStage.executionPhase();
            return nextStage;
        }
    }

    public Stage<?> currentStage() {
        return stages.get(count);
    }

    @FunctionalInterface
    public interface Phase {
        void execute(App app, List<Stage<?>> stages);
    }

}
