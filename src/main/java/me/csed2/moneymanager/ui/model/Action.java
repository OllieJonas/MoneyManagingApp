package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


@Getter
public class Action implements UINode {

    private final String name;
    private final UINode parent;
    private final String image;
    private Command<?> action;

    public Action(String name, Menu parent, String image, Command<?> action) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.action = action;

        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    public Action(String name, Menu parent, String image, Consumer<App> action) {
        this.name = name;
        this.parent = parent;
        this.image = image;
        this.action = (Command<Object>) app -> {
            action.accept(app);
            return null;
        };
    }

    // Action isn't going to have children.
    @Override
    public List<UINode> getChildren() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(App app) {
        return (T) action.execute(app);
    }
}
