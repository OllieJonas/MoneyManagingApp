package me.csed2.moneymanager.ui;

import lombok.Getter;
import me.csed2.moneymanager.main.User;

/**
 * This class contains the implementation of an option for the command line (eg: 3. Exit the Application).
 */
public class Button {

    /**
     * The action to be performed upon selecting this button.
     */
    private IAction action;

    /**
     * The text that is printed out when the menu prints out.
     */
    @Getter
    private String name;

    /**
     * Whether the user's current menu should be reprinted after completing the action. DEFAULT: true.
     */
    @Getter
    private boolean showMenu;

    /**
     * Whether the console should be cleared before executing the action. DEFAULT: false.
     */
    @Getter
    private final boolean clearConsole;

    /**
     * Default constructor for Button. Contains all fields.
     *
     * @param name The text to be printed out
     * @param action The action to be performed upon execution
     * @param showMenu Whether the menu should be shown after execution
     * @param clearConsole Whether the console should be cleared before execution
     */
    public Button(String name, IAction action, boolean showMenu, boolean clearConsole) {
        this.name = name;
        this.action = action;
        this.showMenu = showMenu;
        this.clearConsole = clearConsole;
    }

    /**
     * Constructor that calls the main constructor with values of ShowMenu: false, ClearConsole: true.
     *
     * @param name The text to be printed out
     * @param action The action to be performed upon execution
     */
    public Button(String name, IAction action) {
        this(name, action, false, true);
    }

    /**
     * Constructor that calls the main constructor with the value of ClearConsole being true.
     *
     * @param name The text to be printed out
     * @param action The action to be performed
     * @param showMenu Whether the menu should be shown after execution
     */
    public Button(String name, IAction action, boolean showMenu) {
        this(name, action, showMenu, true);
    }

    /**
     * Executes the action inside this button.
     *
     * @param user The user executing.
     */
    public void execute(User user) {
        action.execute(user);
    }


}
