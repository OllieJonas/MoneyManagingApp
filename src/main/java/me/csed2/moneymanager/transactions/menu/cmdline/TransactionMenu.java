package me.csed2.moneymanager.transactions.menu.cmdline;

import me.csed2.moneymanager.transactions.menu.cmdline.stage.AddTransactionMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.ListTransactionsMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.RemoveTransactionMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.UpdateTransactionMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class TransactionMenu extends CMDMenu {

    public TransactionMenu(Menu parent) {
        super("Transactions", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("List Transactions", user -> user.openMenu(new ListTransactionsMenu(this)), false, false));
        addButton(new Button("Add a Transaction", user -> user.openMenu(new AddTransactionMenu(this)), false, false));
        addButton(new Button("Remove a Transaction", user -> user.openMenu(new RemoveTransactionMenu(this)), false, false));
        addButton(new Button("Update a Transaction", user -> user.openMenu(new UpdateTransactionMenu(this)), false, false));
    }
}
