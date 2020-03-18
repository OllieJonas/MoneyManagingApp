package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.transactions.menu.cmdline.stage.AddTransactionMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.ListTransactionsMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.RemoveTransactionMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.UpdateTransactionMenu;
import me.csed2.moneymanager.ui.Button;

public class DisplayButtonTransactions extends DisplayButtonMenu {

    public DisplayButtonTransactions(){
        super(300, 300, "Transaction Menu", MAIN, true);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("List Transactions", user -> user.openMenu(LIST_TRANSACTIONS), false, false), "icons/button_search_0.png");
        addButton(new Button("Add a Transaction", user -> user.openMenu(ADD_TRANSACTION), false, false), "icons/button_add_0.png");
        addButton(new Button("Remove a Transaction", user -> user.openMenu(REMOVE_TRANSACTION), false, false), "icons/button_delete_0.png");
        addButton(new Button("Update a Transaction", user -> user.openMenu(UPDATE_TRANSACTION), false, false), "icons/button_update_0.png");
        addBackButton();
    }
}
