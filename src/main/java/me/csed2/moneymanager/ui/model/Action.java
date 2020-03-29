package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.main.App;

import java.util.Deque;
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
    }

    // Action isn't going to have children.
    @Override
    public Deque<UINode> getChildren() {
        return null;
    }

    public Object execute(App app) {
        if (funcAction != null) {
            return funcAction.apply(app);
        } else {
            consAction.accept(app);
            return null;
        }
    }
}
