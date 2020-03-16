package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.categories.menu.cmdline.stage.UpdateBudgetMenu;
import me.csed2.moneymanager.categories.menu.cmdline.stage.UpdateNameMenu;
import me.csed2.moneymanager.ui.Button;

public class DisplayButtonUpdateCategory extends DisplayButtonMenu {

    public DisplayButtonUpdateCategory(){
        super(300, 300, "Update a Category", CATEGORY);
    }

    @Override
    protected void addButtons() {

        addButton(new Button("Update Name", user -> user.openMenu(UPDATE_CATEGORY_NAME), false));

        addButton(new Button("Update Budget", user -> user.openMenu(UPDATE_CATEGORY_BUDGET), false));

        addBackButton();
    }
}
