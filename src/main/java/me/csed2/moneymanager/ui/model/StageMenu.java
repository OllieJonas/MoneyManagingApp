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
//        App.getInstance().playSound(App.getInstance().getSoundPack().getSubmitClip(name));
        if (exitPhase != null) {
            exitPhase.execute(App.getInstance(), stages);
        }
        clear();
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

    private void clear() {
        count = 0;
    }

    @FunctionalInterface
    public interface Phase {
        void execute(App app, List<Stage<?>> stages);
    }

    public static class Builder {

        protected final String name;

        private Menu parent;

        private String image;

        private List<Stage<?>> stages;

        private Phase beginPhase;

        private Phase exitPhase;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withParent(Menu parent) {
            this.parent = parent;
            return this;
        }

        public Builder withImage(String path) {
            this.image = path;
            return this;
        }

        public Builder withStages(Stage<?>... stages) {
            this.stages = Arrays.asList(stages);
            return this;
        }

        public Builder withBeginPhase(Phase beginPhase) {
            this.beginPhase = beginPhase;
            return this;
        }

        public Builder withExitPhase(Phase exitPhase) {
            this.exitPhase = exitPhase;
            return this;
        }

        public StageMenu build() {
            return new StageMenu(name, parent, image, stages, beginPhase, exitPhase);
        }
    }
}
