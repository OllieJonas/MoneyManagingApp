package me.csed2.moneymanager.rest.menu;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.rest.monzo.commands.MonzoAuthCommand;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.IAction;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class AuthBankMenu extends CMDMenu {

    public AuthBankMenu(Menu parent) {
        super("Auth Bank", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Authenticate Monzo", user -> CommandDispatcher.getInstance().dispatchSync(new MonzoAuthCommand())));
    }
}
