package me.csed2.moneymanager.ui.cmdline.menu;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.ui.Option;
import me.csed2.moneymanager.ui.cmdline.CMDMenu;
import me.csed2.moneymanager.utils.ConsoleUtils;

public class MainMenu extends CMDMenu {

    @Override
    public void addOptions() {
        addOption(new Option("foo", user -> System.out.println("bar")));

        addOption(new Option("List All Categories", user -> {
            for (Category category : CategoryRepository.getInstance().asList()) {

                ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP, ConsoleUtils.DEFAULT_BORDER, ConsoleUtils.DEFAULT_TIMES);

                System.out.println(category.toFormattedString());

                ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM, ConsoleUtils.DEFAULT_BORDER, ConsoleUtils.DEFAULT_TIMES);
            }
        }, true));
    }
}
