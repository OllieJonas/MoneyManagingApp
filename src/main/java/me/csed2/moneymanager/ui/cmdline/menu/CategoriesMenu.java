package me.csed2.moneymanager.ui.cmdline.menu;

import me.csed2.moneymanager.ui.cmdline.CMDMenu;

/**
 * This class contains the implementation of the categories
 */
public class CategoriesMenu extends CMDMenu {

    private MainMenu parent;

    public CategoriesMenu(MainMenu parent) {
        this.parent = parent;
    }


    @Override
    public void addButtons() {

        addBackButton(parent, true);
    }
}
