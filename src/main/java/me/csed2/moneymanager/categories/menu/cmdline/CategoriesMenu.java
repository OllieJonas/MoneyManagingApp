package me.csed2.moneymanager.categories.menu.cmdline;

import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.categories.menu.cmdline.stage.AddCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.stage.RemoveCategoryMenu;
import me.csed2.moneymanager.main.MainMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

/**
 * This class contains the implementation of the categories
 */
public class CategoriesMenu extends CMDMenu {

    public CategoriesMenu(MainMenu parent) {
        super("Categories", parent);
    }

    @Override
    public void addButtons() {

        addButton(new Button("List All Categories", user -> CategoryRepository.getInstance().print(), true, true));

        addButton(new Button("Add a New Category", user -> user.openMenu(new AddCategoryMenu(this)), false));

        addButton(new Button("Remove a Category", user -> user.openMenu(new RemoveCategoryMenu(this)), false));

        addButton(new Button("Update a Category", user -> user.openMenu(new UpdateCategoryMenu(this))));

    }
}
