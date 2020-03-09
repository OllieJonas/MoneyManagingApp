package me.csed2.moneymanager.ui;

import lombok.Getter;
import me.csed2.moneymanager.main.User;

public class Option {

    private IButton button;

    @Getter
    private String name;

    @Getter
    private boolean showMenu;

    @Getter
    private final boolean clearConsole;

    public Option(String name, IButton button, boolean showMenu, boolean clearConsole) {
        this.name = name;
        this.button = button;
        this.showMenu = showMenu;
        this.clearConsole = clearConsole;
    }

    public Option(String name, IButton button) {
        this(name, button, false, true);
    }

    public Option(String name, IButton button, boolean showMenu) {
        this(name, button, showMenu, true);
    }

    public void execute(User user) {
        button.execute(user);
    }


}
