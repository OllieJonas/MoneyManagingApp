package me.csed2.moneymanager.ui.cmdline.menu;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;
import me.csed2.moneymanager.ui.Option;

public class MainMenu extends CMDMenu {

    public MainMenu(Menu previousMenu) {
        super(previousMenu);
    }

    @Override
    public void addOptions() {
        addOption(new Option("foo").attachButton(user -> System.out.println("bar")));
    }
}
