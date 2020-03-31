package me.csed2.moneymanager.ui.model;

import java.util.List;

public interface UINode {

    String getName();

    UINode getParent();

    List<UINode> getChildren();

    String getImage();
}
