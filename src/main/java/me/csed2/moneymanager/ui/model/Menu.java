package me.csed2.moneymanager.ui.model;

import lombok.Getter;
import me.csed2.moneymanager.sound.Sound;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.utils.ShiftedCircularArrayList;

import java.util.List;

@Getter
public class Menu implements UINode {

    protected final String name;

    protected final UINode parent;

    protected final String image;

    protected List<UINode> children;
    
    private Sound loadSound;
    private Sound submitSound;

    public Menu(String name, UINode parent, String image) {
        this.name = name;
        this.parent = parent;
        this.image = image;

        int variance = parent != null ? 2 : 1;

        children = new ShiftedCircularArrayList<>(variance);

        if (parent != null) {
            MenuList.addBackAction(this);
            parent.getChildren().add(this);
        }
        MenuList.addExitAction(this);
    }


    @Override
    public Sound getLoadSound() {
        return loadSound;
    }

    @Override
    public Sound getSubmitSound() {
        return submitSound;
    }

    public Menu withLoadSound(Sound sound) {
        this.loadSound = sound;
        return this;
    }

    public Menu withSubmitSound(Sound sound) {
        this.submitSound = sound;
        return this;
    }
}
