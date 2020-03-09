package me.csed2.moneymanager.ui.cmdline.menu;

import me.csed2.moneymanager.ui.cmdline.CMDMenu;
import me.csed2.moneymanager.ui.cmdline.option.Option;

public class MainMenu extends CMDMenu {

    public MainMenu() {

    }

    @Override
    public void addOptions() {
        addOption(new Option("foo").attachButton(user -> System.out.println("bar")));
    }
}
