package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.main.App;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;


@Getter
public class Action implements UINode {

    private String name;
    private UINode parent;
    private Image image;
    private Consumer<App> action;

    public Action(String name, UINode parent, Image image, Consumer<App> action) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.action = action;
    }

    // Action isn't going to have children.
    @Override
    public List<UINode> getChildren() {
        return null;
    }

    public void execute(App app) {
        action.accept(app);
    }
}
