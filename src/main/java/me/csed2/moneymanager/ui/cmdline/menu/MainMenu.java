package me.csed2.moneymanager.ui.cmdline.menu;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;
import me.csed2.moneymanager.utils.ConsoleUtils;

/**
 * This class contains the implementation for the main menu.
 */
public class MainMenu extends CMDMenu {

    @Override
    public void addButtons() {
        addButton(new Button("foo", user -> System.out.println("bar"), true));

        addButton(new Button("List All Categories", user -> {
            for (Category category : CategoryRepository.getInstance().asList()) {

                ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP, ConsoleUtils.DEFAULT_BORDER, ConsoleUtils.DEFAULT_TIMES);

                System.out.println(category.toFormattedString());

                ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM, ConsoleUtils.DEFAULT_BORDER, ConsoleUtils.DEFAULT_TIMES);
            }
        }, true));
    }
}
