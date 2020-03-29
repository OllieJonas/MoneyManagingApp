package me.csed2.moneymanager.categories.menu;

import me.csed2.moneymanager.categories.menu.stage.UpdateBudgetMenu;
import me.csed2.moneymanager.categories.menu.stage.UpdateNameMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

public class UpdateCategoryMenu extends CMDMenu {

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     */
    public UpdateCategoryMenu(Menu previousMenu) {
        super("Update a Category", previousMenu);
    }

    @Override
    protected void addButtons() {

        addButton(new Button("Update Name", user -> user.openMenu(new UpdateNameMenu(this)), false));

        addButton(new Button("Update Budget", user -> user.openMenu(new UpdateBudgetMenu(this)), false));
    }
}
