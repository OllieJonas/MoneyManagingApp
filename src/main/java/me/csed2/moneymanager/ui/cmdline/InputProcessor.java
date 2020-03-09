package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Option;
import me.csed2.moneymanager.main.User;

import java.util.List;

public class InputProcessor {

    public static void process(User user, String input) {

        try {
            int optionNo = Integer.parseInt(input); // Convert string to integer
            Menu menu = user.getCurrentMenu();
            List<Option> options = menu.getOptions();

            if (optionNo <= 0 || optionNo > options.size()) {
                System.out.println("Please enter a number between the given values!");

            } else {
                Option option = options.get(optionNo - 1);
                option.execute(user);
            }

        } catch (NumberFormatException throwable) {
            System.out.println("Please type in a number!");
        }
    }

}
