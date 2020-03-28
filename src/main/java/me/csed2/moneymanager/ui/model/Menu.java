package me.csed2.moneymanager.ui.model;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Menu implements UINode {

    protected final String name;

    protected final UINode parent;

    protected final Image image;

    protected List<UINode> children = new ArrayList<>();

    public Menu(String name, UINode parent, Image image) {
        this.name = name;
        this.parent = parent;
        this.image = image;
    }

    public Menu build() {
        addChildren();
        return this;
    }

    protected abstract void addChildren();

    protected boolean addChild(UINode node) {
        return children.add(node);
    }
}
