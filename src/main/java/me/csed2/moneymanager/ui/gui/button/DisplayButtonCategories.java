package me.csed2.moneymanager.ui.gui.button;

import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.categories.menu.cmdline.UpdateCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.stage.AddCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.stage.RemoveCategoryMenu;
import me.csed2.moneymanager.ui.Button;

import javax.swing.*;

public class DisplayButtonCategories extends DisplayButtonMenu {

    public DisplayButtonCategories(){
        super(300, 300, "Categories", MAIN);
    }

    @Override
    protected void addButtons() {
        addButton(new Button("List All Categories", user -> JOptionPane.showMessageDialog(null, CategoryRepository.getInstance().getCategoryReport())));

        addButton(new Button("Add a New Category", user -> user.openMenu(ADD_CATEGORY)));

        addButton(new Button("Remove a Category", user -> user.openMenu(REMOVE_CATEGORY)));

        addButton(new Button("Update a Category", user -> user.openMenu(new UpdateCategoryMenu(this))));

        addBackButton();
    }
}
