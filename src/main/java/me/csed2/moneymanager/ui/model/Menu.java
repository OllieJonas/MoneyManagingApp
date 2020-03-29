package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.ui.MenuList;

import java.util.*;

@Getter
public class Menu implements UINode {

    protected final String name;

    protected final Menu parent;

    protected final String image;

    protected List<UINode> children = new ArrayList<>();

    public Menu(String name, Menu parent, String image) {
        this.name = name;
        this.parent = parent;
        this.image = image;

        children.add(MenuList.exitAction(this));

        if (parent != null) {
            children.add(MenuList.backAction(this));
            parent.getChildren().add(this);
        }
    }
}
