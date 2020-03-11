package me.csed2.moneymanager.categories.menu.cmdline;

import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.categories.menu.cmdline.step.AddCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.step.RemoveCategoryMenu;
import me.csed2.moneymanager.categories.menu.cmdline.step.UpdateCategoryMenu;
import me.csed2.moneymanager.main.MainMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;

/**
 * This class contains the implementation of the categories
 */
public class CategoriesMenu extends CMDMenu {

    private MainMenu parent;

    public CategoriesMenu(MainMenu parent) {
        super("Categories");
        this.parent = parent;
    }


    @Override
    public void addButtons() {

        addButton(new Button("List All Categories", user -> CategoryRepository.getInstance().print(), true));

        addButton(new Button("Add a New Category", user -> user.openMenu(new AddCategoryMenu(this))));

        addButton(new Button("Remove a Category", user -> user.openMenu(new RemoveCategoryMenu(this))));

        addButton(new Button("Update a Category", user -> user.openMenu(new UpdateCategoryMenu(this))));

        addBackButton(parent, true);
    }
}
