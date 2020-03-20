package me.csed2.moneymanager.subscriptions.menu.cmdline.stage;

import me.csed2.moneymanager.subscriptions.SubscriptionArgType;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.update.*;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class UpdateSubscriptionMenu extends CMDMenu {

    public UpdateSubscriptionMenu(Menu parent) {
        super("Update Subscription", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Update Name for a Subscription", user -> user.openMenu(new UpdateNameMenu(this)), false));
        addButton(new Button("Update Amount for a Subscription", user -> user.openMenu(new UpdateAmountMenu(this)), false));
        addButton(new Button("Update Vendor for a Subscription", user -> user.openMenu(new UpdateVendorMenu(this)), false));
        addButton(new Button("Update Notes for a Subscription", user -> user.openMenu(new UpdateNotesMenu(this)), false));
    }
}
