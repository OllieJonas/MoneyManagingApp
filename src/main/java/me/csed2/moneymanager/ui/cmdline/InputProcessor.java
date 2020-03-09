package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Option;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.utils.ConsoleUtils;

import java.util.List;

public class InputProcessor {

    public static void process(User user, String input) {
        try {

            int optionNo = Integer.parseInt(input); // Convert string to integer
            if (user.getCurrentMenu() != null) {
                Menu menu = user.getCurrentMenu();
                List<Option> options = menu.getOptions();

                if (optionNo <= 0 || optionNo > options.size()) {
                    System.out.println("Please enter a number between the given values!");

                } else {
                    Option option = options.get(optionNo - 1);

                    if (menu instanceof CMDMenu && option.isClearConsole()) {
                        ConsoleUtils.clearConsole();
                    }

                    System.out.print("\n");

                    option.execute(user);

                    System.out.print("\n");

                    if (option.isShowMenu()) {
                        menu.open();
                    }
                }
            } else {
                System.out.println("Fatal Error: Please try loading the program again! If the problem persists, please get in touch with an admin.");
                user.exit();
            }
        } catch (NumberFormatException throwable) {
            System.out.println("Please type in a number!");
        }
    }

}
