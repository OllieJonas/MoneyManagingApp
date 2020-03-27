package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.main.App;
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
     * @param app The current instance of the user using this.
     * @param input Their input into the console.
     */
    static void process(App app, String input) {

            if (app.getCurrentMenu() != null) {
                Menu menu = app.getCurrentMenu();

                if (menu instanceof StageMenu) {
                    processStageMenu(app, input, (StageMenu) menu);
                } else {
                    processCMDMenu(app, input, (CMDMenu) menu);
                }

            } else {
            System.out.println("Fatal Error: Please try loading the program again! If the problem persists, please get in touch with a developer!");
            app.exit();
        }
    }

    /**
     * Processes anything the user inputs, if it's a stage menu
     *
     * @param app The user using this
     * @param input The input that they typed
     * @param menu The menu they're accessing
     */
    private static void processStageMenu(App app, String input, StageMenu menu) {
        if (input.equalsIgnoreCase("exit")) {
            app.openMenu(menu.getPreviousMenu());

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

    /**
     * Processes anything the user inputs, if it's a normal CMD menu
     *
     * @param app The user using this
     * @param input The input that they typed
     * @param menu The menu they're accessing
     */
    private static void processCMDMenu(App app, String input, CMDMenu menu) {
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

                button.execute(app);

                if (button.isSurroundWithSpaces()) {
                    System.out.print("\n");
                }

                if (button.isShowMenu()) { // If this option means that you reprint the menu
                    app.getCurrentMenu().print();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please type in a number!");
        }
    }
}
