package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.utils.VarianceCircularArrayList;

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

        int variance = parent != null ? 2 : 1;

        children = new VarianceCircularArrayList<>(variance);

        if (parent != null) {
            MenuList.decorateBackAction(this);
            parent.getChildren().add(this);
        }
        MenuList.decorateExitAction(this);
    }
}
