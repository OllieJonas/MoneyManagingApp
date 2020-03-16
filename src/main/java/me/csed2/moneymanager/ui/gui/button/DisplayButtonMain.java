package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.categories.menu.cmdline.CategoriesMenu;
import me.csed2.moneymanager.transactions.menu.cmdline.TransactionMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.stage.TestStageMenu;
import me.csed2.moneymanager.ui.gui.DisplayMenu;

public class DisplayButtonMain extends DisplayButtonMenu {

    public DisplayButtonMain(){
        super(300,400, "Main Menu", null);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("Categories", user -> user.openMenu(DisplayMenu.CATEGORY)));
    }
}