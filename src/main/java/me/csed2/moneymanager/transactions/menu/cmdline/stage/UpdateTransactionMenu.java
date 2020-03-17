package me.csed2.moneymanager.transactions.menu.cmdline.stage;

import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.*;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class UpdateTransactionMenu extends CMDMenu {

    public UpdateTransactionMenu(Menu parent) {
        super("Update Transaction", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Update Name for a Transaction", user -> user.openMenu(new UpdateNameMenu(this)), false));
        addButton(new Button("Update Amount for a Transaction", user -> user.openMenu(new UpdateAmountMenu(this)), false));
        addButton(new Button("Update Vendor for a Transaction", user -> user.openMenu(new UpdateVendorMenu(this)), false));
        addButton(new Button("Update Notes for a Transaction", user -> user.openMenu(new UpdateNotesMenu(this)), false));
    }
}
