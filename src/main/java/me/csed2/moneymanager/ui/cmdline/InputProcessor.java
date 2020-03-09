package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.utils.ConsoleUtils;

import java.util.List;

/**
 * This class will determine what to do based on user input from the console.
 */
public class InputProcessor {

    /**
     * Processes any information that they may type into the console.
     *
     * @param user The current instance of the user using this.
     * @param input Their input into the console.
     */
    public static void process(User user, String input) {
        try {

            int optionNo = Integer.parseInt(input); // Convert string to integer

            if (user.getCurrentMenu() != null) {

                Menu menu = user.getCurrentMenu();

                List<Button> buttons = menu.getButtons();

                if (optionNo <= 0 || optionNo > buttons.size()) { // Checking that the user has entered a valid number
                    System.out.println("Please enter a number between the given values!");

                } else {

                    Button button = buttons.get(optionNo - 1); // ArrayList will be different from printed value

                    if (menu instanceof CMDMenu && button.isClearConsole()) {
                        ConsoleUtils.clearConsole();
                    }

                    System.out.print("\n");

                    button.execute(user);

                    System.out.print("\n");

                    if (button.isShowMenu()) { // If this option means that you reprint the menu
                        user.getCurrentMenu().print();
                    }
                }

            } else {
                System.out.println("Fatal Error: Please try loading the program again! If the problem persists, please get in touch with a developer!");
                user.exit();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please type in a number!");
        }
    }

}
