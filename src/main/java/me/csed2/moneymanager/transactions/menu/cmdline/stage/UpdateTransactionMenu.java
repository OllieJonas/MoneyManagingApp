package me.csed2.moneymanager.transactions.menu.cmdline.stage;

import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateAmountMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateNameMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateNotesMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateVendorMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class UpdateTransactionMenu extends CMDMenu {

    public UpdateTransactionMenu(Menu parent) {
        super("Update Transaction", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Update Name for a Transaction", user -> user.openMenu(new UpdateNameMenu(this))));
        addButton(new Button("Update Amount for a Transaction", user -> user.openMenu(new UpdateAmountMenu(this))));
        addButton(new Button("Update Vendor for a Transaction", user -> user.openMenu(new UpdateVendorMenu(this))));
        addButton(new Button("Update Notes for a Transaction", user -> user.openMenu(new UpdateNotesMenu(this))));
    }
}
