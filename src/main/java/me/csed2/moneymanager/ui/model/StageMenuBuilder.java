package me.csed2.moneymanager.ui.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class StageMenuBuilder {

    @Getter
    protected final String name;

    @Getter
    protected Menu parent;

    @Getter
    protected String image;

    @Getter
    protected List<Stage<?>> stages;

    private StageMenu.Phase beginPhase;

    private StageMenu.Phase exitPhase;

    public StageMenuBuilder(String name) {
        this.name = name;
    }

    public StageMenuBuilder withParent(Menu parent) {
        this.parent = parent;
        return this;
    }

    public StageMenuBuilder withImage(String path) {
        this.image = path;
        return this;
    }

    public StageMenuBuilder withStages(Stage<?>... stages) {
        this.stages = Arrays.asList(stages);
        return this;
    }

    public StageMenuBuilder withBeginPhase(StageMenu.Phase beginPhase) {
        this.beginPhase = beginPhase;
        return this;
    }

    public StageMenuBuilder withExitPhase(StageMenu.Phase exitPhase) {
        this.exitPhase = exitPhase;
        return this;
    }

    public StageMenu build() {
        return new StageMenu(name, parent, image, stages, beginPhase, exitPhase);
    }
}
