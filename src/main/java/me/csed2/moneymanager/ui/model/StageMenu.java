package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.sound.Sound;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract implementation of a step menu.
 */
@Getter
public class StageMenu implements UINode {

    protected final String name;

    protected final Menu parent;

    protected final String image;

    protected List<Stage<?>> stages;

    private Phase beginPhase;

    private Phase exitPhase;

    private Sound loadSound;

    private Sound submitSound;

    private int count = 0;

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param name
     * @param image
     */
    private StageMenu(String name, Menu parent, String image, List<Stage<?>> stages, Sound loadSound, Sound submitSound, Phase beginPhase, Phase exitPhase) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.stages = stages;
        this.beginPhase = beginPhase;
        this.exitPhase = exitPhase;
        this.loadSound = loadSound;
        this.submitSound = submitSound;

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

    public Stage<?> nextStage() {
        count++;
        if (count >= stages.size()) {
            exitPhase();
            App.getInstance().playSound(submitSound);
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

        private Sound loadSound;

        private Sound submitSound;

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

        public Builder withLoadSound(Sound sound) {
            this.loadSound = sound;
            return this;
        }

        public Builder withSubmitSound(Sound sound) {
            this.submitSound = sound;
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
            return new StageMenu(name, parent, image, stages, loadSound, submitSound, beginPhase, exitPhase);
        }
    }
}
