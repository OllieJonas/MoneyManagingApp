package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.categories.menu.cmdline.UpdateCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.stage.AddCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.stage.RemoveCategoryMenu;
import me.csed2.moneymanager.ui.Button;

import javax.swing.*;

public class DisplayButtonCategories extends DisplayButtonMenu {

    public DisplayButtonCategories(){
        super(300, 300, "Categories", MAIN, true);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("List All Categories", user -> showMessage(CategoryCache.getInstance().getCategoryReport())), "icons/button_search_0.png");

        addButton(new Button("Add a New Category", user -> user.openMenu(ADD_CATEGORY)), "icons/button_add_0.png");

        addButton(new Button("Remove a Category", user -> user.openMenu(REMOVE_CATEGORY)), "icons/button_delete_0.png");

        addButton(new Button("Update a Category", user -> user.openMenu(UPDATE_CATEGORY)), "icons/button_update_0.png");

        addBackButton();
    }
}
