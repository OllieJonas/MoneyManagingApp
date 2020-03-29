package me.csed2.moneymanager.ui.model;

import java.util.Deque;

public interface UINode {

    String getName();

    UINode getParent();

    Deque<UINode> getChildren();

    String getImage();
}
