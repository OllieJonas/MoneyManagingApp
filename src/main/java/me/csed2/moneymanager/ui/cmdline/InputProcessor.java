package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;
import me.csed2.moneymanager.utils.ConsoleUtils;
import me.csed2.moneymanager.utils.StringReaderFactory;

import java.util.List;

/**
 * This class will determine what to do based on user input from the console.
 *
 * @author Ollie
 * @since 08/03/2020
 */
public class InputProcessor {

    /**
     * Processes any information that they may type into the console.
     *
     * @param user The current instance of the user using this.
     * @param input Their input into the console.
     */
    public static void process(User user, String input) {

            if (user.getCurrentMenu() != null) {
                Menu menu = user.getCurrentMenu();

                if (menu instanceof StageMenu) {
                    processStageMenu(user, input, (StageMenu) menu);
                } else {
                    processCMDMenu(user, input, (CMDMenu) menu);
                }

            } else {
            System.out.println("Fatal Error: Please try loading the program again! If the problem persists, please get in touch with a developer!");
            user.exit();
        }
    }

    private static void processStageMenu(User user, String input, StageMenu menu) {
        if (input.equalsIgnoreCase("exit")) {
            user.openMenu(menu.getPreviousMenu());

        } else {

            Stage<?> currentStage = menu.currentStage(); // Get the current ste[

            try {
                Object result = StringReaderFactory.parse(input, currentStage.getResultType()); // Convert string to object with correct type
                currentStage.setResult(result); // set result to this
                menu.nextStage(); // Load next step
            } catch (InvalidTypeException e) {
                System.out.println(e.getMessage());
                menu.currentStage().print();
            }
        }
    }

    private static void processCMDMenu(User user, String input, CMDMenu menu) {
        try {
            int optionNo = Integer.parseInt(input); // Convert string to integer
            List<Button> buttons = menu.getButtons();

            if (optionNo <= 0 || optionNo > buttons.size()) { // Checking that the user has entered a valid number
                System.out.println("Please enter a number between the given values!");

            } else {

                Button button = buttons.get(optionNo - 1); // ArrayList will be different from printed value

                if (button.isClearConsole()) {
                    ConsoleUtils.clearConsole();
                }

                if (button.isSurroundWithSpaces()) {
                    System.out.print("\n");
                }

                button.execute(user);

                if (button.isSurroundWithSpaces()) {
                    System.out.print("\n");
                }

                if (button.isShowMenu()) { // If this option means that you reprint the menu
                    user.getCurrentMenu().print();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please type in a number!");
        }
    }
}
