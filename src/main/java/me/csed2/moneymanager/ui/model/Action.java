package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.sound.Sound;
import me.csed2.moneymanager.utils.ClassUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


@Getter
public class Action implements UINode {

    private String name;
    private UINode parent;
    private String image;
    private Function<App, ?> funcAction;
    private Consumer<App> consAction;

    private Sound loadSound;
    private Sound submitSound;

    public Action(String name, Menu parent, String image, Function<App, ?> action) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.funcAction = action;

        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    public Action(String name, Menu parent, String image, Consumer<App> action) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.consAction = action;

        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    // Action isn't going to have children.
    @Override
    public List<UINode> getChildren() {
        return null;
    }

    @Override
    public Sound getLoadSound() {
        return loadSound;
    }

    @Override
    public Sound getSubmitSound() {
        return submitSound;
    }

    public Action withLoadSound(Sound sound) {
        this.loadSound = sound;
        return this;
    }

    public Action withSubmitSound(Sound sound) {
        this.submitSound = sound;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(App app) {
        if (funcAction != null) {
            return (T) funcAction.apply(app);
        } else {
            consAction.accept(app);
            return null;
        }
    }
}
