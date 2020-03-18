package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateAmountMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateNameMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateNotesMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.stage.update.UpdateVendorMenu;
import me.csed2.moneymanager.ui.Button;

public class DisplayButtonUpdateTransaction extends DisplayButtonMenu {

    public DisplayButtonUpdateTransaction(){
        super(300, 300, "Update Transaction", TRANSACTION, false);
    }

    protected void beginPhase(){

    }

    @Override
    protected void addButtons() {
        addButton(new Button("Update Name for a Transaction", user -> user.openMenu(UPDATE_TRANSACTION_NAME)));
        addButton(new Button("Update Amount for a Transaction", user -> user.openMenu(UPDATE_TRANSACTION_AMOUNT)));
        addButton(new Button("Update Vendor for a Transaction", user -> user.openMenu(UPDATE_TRANSACTION_VENDOR)));
        addButton(new Button("Update Notes for a Transaction", user -> user.openMenu(UPDATE_TRANSACTION_NOTES)));
        addBackButton();
    }
}
