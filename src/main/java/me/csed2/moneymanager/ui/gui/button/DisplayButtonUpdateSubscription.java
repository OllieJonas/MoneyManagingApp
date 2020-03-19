package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.ui.Button;

public class DisplayButtonUpdateSubscription extends DisplayButtonMenu {

    public DisplayButtonUpdateSubscription(){
        super(300, 300, "Update Subscription", SUBSCRIPTION, false);
    }

    protected void beginPhase(){

    }

    @Override
    protected void addButtons() {
        addButton(new Button("Update Name for a Subscription", user -> user.openMenu(UPDATE_SUBSCRIPTION_NAME)));
        addButton(new Button("Update Amount for a Subscription", user -> user.openMenu(UPDATE_SUBSCRIPTION_AMOUNT)));
        addButton(new Button("Update Vendor for a Subscription", user -> user.openMenu(UPDATE_SUBSCRIPTION_VENDOR)));
        addButton(new Button("Update Notes for a Subscription", user -> user.openMenu(UPDATE_SUBSCRIPTION_NOTES)));
        addBackButton();
    }
}
