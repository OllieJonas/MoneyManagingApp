package me.csed2.moneymanager.ui.model;

import me.csed2.moneymanager.sound.Sound;

import java.util.List;

public interface UINode {

    String getName();

    UINode getParent();

    List<UINode> getChildren();

    String getImage();

    // Sound
    Sound getLoadSound();

    Sound getSubmitSound();
}
