package me.csed2.moneymanager.ui;

import me.csed2.moneymanager.main.User;

import java.util.List;

public abstract class Menu {

    public Menu(Menu menu) {
        User.getInstance().setCurrentMenu(this);
        User.getInstance().setPreviousMenu(menu);
    }

    public abstract void open();

    public abstract List<Option> getOptions();
}
