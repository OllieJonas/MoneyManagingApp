package me.csed2.moneymanager.ui.model;

import java.util.Deque;
import java.util.List;
import java.util.Queue;

public interface UINode {

    String getName();

    UINode getParent();

    List<UINode> getChildren();

    String getImage();
}
