package me.csed2.moneymanager.subscriptions.menu.cmdline;

import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.AddSubscriptionMenu;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.ListSubscriptionsMenu;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.RemoveSubscriptionMenu;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.UpdateSubscriptionMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class SubscriptionMenu extends CMDMenu {

    public SubscriptionMenu(Menu parent) {
        super("Subscriptions", parent);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("List Subscriptions", user -> user.openMenu(new ListSubscriptionsMenu(this)), false, false));
        addButton(new Button("Add a Subscription", user -> user.openMenu(new AddSubscriptionMenu(this)), false, false));
        addButton(new Button("Remove a Subscription", user -> user.openMenu(new RemoveSubscriptionMenu(this)), false, false));
        addButton(new Button("Update a Subscription", user -> user.openMenu(new UpdateSubscriptionMenu(this)), false, false));
    }
}
