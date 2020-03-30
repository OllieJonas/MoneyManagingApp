package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.utils.CircularArrayList;

import java.util.List;

@Getter
public class Menu implements UINode {

    protected final String name;

    protected final Menu parent;

    protected final String image;

    protected List<UINode> children;

    public Menu(String name, Menu parent, String image) {
        this.name = name;
        this.parent = parent;
        this.image = image;

        boolean hasParent = parent != null;
        children = new CircularArrayList<>(hasParent);

        if (hasParent) {
            MenuList.decorateBackAction(this);
            parent.getChildren().add(this);
        }
        MenuList.decorateExitAction(this);
    }
}
