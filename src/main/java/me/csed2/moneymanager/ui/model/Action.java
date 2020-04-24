package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.main.App;
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
