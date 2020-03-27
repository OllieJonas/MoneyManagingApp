package me.csed2.moneymanager.rest.monzo.menu;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.commands.MonzoAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoCheckAuthCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoGetAccountsCommand;
import me.csed2.moneymanager.rest.monzo.commands.MonzoGetTransactionsCommand;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class MonzoMenu extends CMDMenu {

    public MonzoMenu(Menu parent) {
        super("Monzo Account", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Authenticate Monzo", user -> CommandDispatcher.dispatchSync(new MonzoAuthCommand()), false, false));
        addButton(new Button("List Accounts", user -> CommandDispatcher.dispatchSync(new MonzoGetAccountsCommand()), false, false));
        addButton(new Button("List Transactions", user -> CommandDispatcher.dispatchSync(new MonzoGetTransactionsCommand()), false, false));
        addButton(new Button("Update from Monzo", user -> System.out.println("Coming Soon TM"), true, true));
        addButton(new Button("Check Authentication", user -> System.out.println(CommandDispatcher.dispatchSync(new MonzoCheckAuthCommand())), false, false));
        addButton(new Button("Print Access Token", user -> System.out.println(MonzoHttpClient.getAccessToken()), false, false));
    }
}
