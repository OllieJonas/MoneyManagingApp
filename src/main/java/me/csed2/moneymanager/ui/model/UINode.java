package me.csed2.moneymanager.ui.model;

import java.awt.*;
import java.util.List;

public interface UINode {

    String getName();

    UINode getParent();

    List<UINode> getChildren();

    Image getImage();
}
