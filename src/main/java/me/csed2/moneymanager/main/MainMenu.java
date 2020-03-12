package me.csed2.moneymanager.main;

import me.csed2.moneymanager.categories.menu.cmdline.CategoriesMenu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;
import me.csed2.moneymanager.ui.cmdline.stage.TestStageMenu;

/**
 * This class contains the implementation for the main menu.
 *
 * @author Ollie
 * @since 08/03/2020
 */
public class MainMenu extends CMDMenu {

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     */
    public MainMenu() {
        super("Main Menu");
    }

    @Override
    public void addButtons() {
        addButton(new Button("foo", user -> System.out.println("bar"), true, true, false));

        addButton(new Button("Categories", user -> user.openMenu(new CategoriesMenu(this))));

        addButton(new Button("Test Step Menu", user -> user.openMenu(new TestStageMenu(this)), false, false));
    }
}
