package me.csed2.moneymanager.rest.monzo.menu;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.rest.monzo.commands.MonzoAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoListAccountsCommand;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class MonzoMenu extends CMDMenu {

    public MonzoMenu(Menu parent) {
        super("Monzo Account", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Authenticate Monzo", user -> CommandDispatcher.getInstance().dispatchSync(new MonzoAuthCommand())));
        addButton(new Button("List Accounts", user -> CommandDispatcher.getInstance().dispatchSync(new MonzoListAccountsCommand())));
        addButton(new Button("Update from Monzo", user -> System.out.println("Coming Soon TM"), true, true));
    }
}
