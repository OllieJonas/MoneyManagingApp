package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.AddSubscriptionMenu;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.ListSubscriptionsMenu;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.RemoveSubscriptionMenu;
import me.csed2.moneymanager.subscriptions.menu.cmdline.stage.UpdateSubscriptionMenu;

import me.csed2.moneymanager.ui.Button;

public class DisplayButtonSubscriptions extends DisplayButtonMenu {

    public DisplayButtonSubscriptions(){
        super(300, 300, "Subscription Menu", MAIN, true);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("List Subscription", user -> user.openMenu(LIST_SUBSCRIPTIONS), false, false), "icons/button_search_0.png");
        addButton(new Button("Add a Subscription", user -> user.openMenu(ADD_SUBSCRIPTION), false, false), "icons/button_add_0.png");
        addButton(new Button("Remove a Subscription", user -> user.openMenu(REMOVE_SUBSCRIPTION), false, false), "icons/button_delete_0.png");
        addButton(new Button("Update a Subscription", user -> user.openMenu(UPDATE_SUBSCRIPTION), false, false), "icons/button_update_0.png");
        addBackButton();
    }
}
